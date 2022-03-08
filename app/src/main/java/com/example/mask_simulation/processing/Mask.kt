
package com.example.mask_simulation.processing

import android.util.Log
import com.example.mask_simulation.viewModel.MainViewModel
import com.google.mlkit.vision.face.Face
import processing.core.PApplet
import kotlin.math.absoluteValue
import kotlin.math.ulp

class Mask(val viewModel: MainViewModel) : PApplet() {

    override fun setup() {

        background(0f)
        noFill()
        strokeWeight(4f)
        stroke(255)

    }

    override fun draw() {
        scale(1.5f)

        background(0f,50f)

        var face =  viewModel.face.value


        if (face != null) {

            conter(face)
        }

        background(0f,0f,0f,20f)

    }

    private fun conter( face: Face) {

        for(i in 1 .. 15){

            var faceZ = face.headEulerAngleZ
            var eyeR = face.rightEyeOpenProbability
            var eyeL = face.leftEyeOpenProbability

            Log.d("HILLOFACEY", "Z::${faceZ} R::${eyeR} L::${eyeL}")



            /*var eyePs = face.getContour(i)?.points
            for (eye in eyePs!!){
                point(eye.x, eye.y)
            }*/
        }


    }
}
