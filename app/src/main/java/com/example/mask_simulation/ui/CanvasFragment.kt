package com.example.mask_simulation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.mask_simulation.R
import com.example.mask_simulation.processing.Mask
import com.example.mask_simulation.viewModel.MainViewModel
import processing.android.CompatUtils
import processing.android.PFragment


class CanvasFragment : Fragment() {

    lateinit var sketch: Mask

    //............values
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_canvas, container, false)

        view.id = CompatUtils.getUniqueViewId()


        sketch = Mask(mainViewModel)
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