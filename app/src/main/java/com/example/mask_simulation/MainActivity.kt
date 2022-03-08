package com.example.mask_simulation

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mask_simulation.viewModel.MainViewModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    companion object {
         const val TAG = "CameraXBasic"
         const val REQUEST_CODE_PERMISSIONS = 10
         val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }

    //----------values
    private var imageCapture: ImageCapture? = null
    private lateinit var cameraExecutor: ExecutorService
    lateinit var image: InputImage
    private lateinit var cam: PreviewView
    private val mainViewModel: MainViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cam = findViewById(R.id.cam)


        // Request camera permissions
        if (allPermissionsGranted()) {
            startCamera()
        } else
        {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }


        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            this, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({

            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(cam.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder()
                .build()



            val imageAnalyzer = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
                .also {
                    it.setAnalyzer(cameraExecutor, LuminosityAnalyzer())
                }

            /* ! Select back camera as a default
            ! val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA*/

            // Select front camera as a default
            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture, imageAnalyzer)

            } catch(exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(this))
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(this,
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }


    inner class LuminosityAnalyzer : ImageAnalysis.Analyzer{


        @SuppressLint("UnsafeOptInUsageError")
        override fun analyze(imageProxy: ImageProxy) {

            val mediaImage = imageProxy.image
            if (mediaImage != null) {
                image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

                // ? Real-time contour detection
                val realTimeOpts = FaceDetectorOptions.Builder()
                    .setContourMode(FaceDetectorOptions.CONTOUR_MODE_NONE)
                    .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
                    .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
                    .build()

                // ? Get an instance of FaceDetector
                val detector = FaceDetection.getClient(realTimeOpts)



                // ? Process the image
                val result = detector.process(image)
                    .addOnSuccessListener { faces ->

                        // Task completed successfully
                        for (face in faces){
                            mainViewModel.addFace(face)
                        }

                        imageProxy.close()

                    }
                    .addOnFailureListener { e ->
                        // Task failed with an exception
                        Log.d("hillo", e.toString())
                        imageProxy.close()
                    }
            }


        }

    }
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
    }
}