package com.example.mask_simulation.processing

import processing.core.PApplet

class Mask: PApplet() {
    override fun setup() {
        background(0f)
    }

    override fun draw() {
        stroke(255f)
        strokeWeight(2f)
        line(100f,100f,700f,700f)
    }
}