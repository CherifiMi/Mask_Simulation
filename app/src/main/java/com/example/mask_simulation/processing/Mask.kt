
package com.example.mask_simulation.processing

import android.graphics.PointF
import android.util.Log
import com.example.mask_simulation.viewModel.MainViewModel
import com.google.mlkit.vision.face.Face
import processing.core.PApplet

class Mask(val viewModel: MainViewModel) : PApplet() {

    var Hpr = height/640
    var Wpr = width/480

    override fun setup() {
        background(20f)
        stroke(255f)
        strokeWeight(2f)
    }

    override fun draw() {

        background(20f, 20f)
        noFill()
        strokeWeight(4f)
        stroke(255)

        var faceData =  viewModel.face.value

        if (faceData != null) {

            var top= faceData.boundingBox.top.toFloat()
            var bottom= faceData.boundingBox.bottom.toFloat()
            var right= faceData.boundingBox.right.toFloat()
            var left= faceData.boundingBox.left.toFloat()

            rect(left, top, right, bottom)
        }




    }
}
