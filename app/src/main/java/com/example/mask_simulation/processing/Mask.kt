
package com.example.mask_simulation.processing

import com.example.mask_simulation.ui.CanvasFragment
import com.google.mlkit.vision.face.Face
import processing.core.PApplet

class Mask: PApplet() {

    lateinit var faceData: Face

    override fun setup() {
        background(20f)
        stroke(255f)
        strokeWeight(2f)
    }

    override fun draw() {

        /*var top= faceData.boundingBox.top.toFloat()
        var bottom= faceData.boundingBox.bottom.toFloat()
        var right= faceData.boundingBox.right.toFloat()
        var left= faceData.boundingBox.left.toFloat()

        rect(left, top, right-left, bottom-top)*/


    }
}
