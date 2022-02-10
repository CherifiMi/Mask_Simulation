
package com.example.mask_simulation.processing

import com.example.mask_simulation.viewModel.MainViewModel
import com.google.mlkit.vision.face.Face
import processing.core.PApplet

class Mask(val viewModel: MainViewModel) : PApplet() {

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
        scale(2f)
        rotateX(PI)

        var face =  viewModel.face.value


        if (face != null) {

            conter(face)


        }




    }

    private fun conter( face: Face) {

        for(i in 1 .. 15){
            var eyePs = face.getContour(i)?.points
            for (eye in eyePs!!){
                point(eye.x, eye.y)
            }
        }


    }
}
