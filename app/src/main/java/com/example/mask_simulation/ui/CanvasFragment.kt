package com.example.mask_simulation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mask_simulation.R
import com.example.mask_simulation.processing.Mask
import processing.android.CompatUtils
import processing.android.PFragment


class CanvasFragment : Fragment() {

    lateinit var sketch: Mask

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_canvas, container, false)

        view.id = CompatUtils.getUniqueViewId()


        sketch = Mask()
        val fragment = PFragment(sketch)
        fragment.setView(view, requireActivity())

        return view
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        sketch.onRequestPermissionsResult(
            requestCode, permissions, grantResults
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        sketch.pause()
    }


}