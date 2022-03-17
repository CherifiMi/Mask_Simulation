package com.example.mask_simulation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.startup.AppInitializer
import app.rive.runtime.kotlin.RiveAnimationView
import app.rive.runtime.kotlin.RiveInitializer
import com.example.mask_simulation.R
import com.example.mask_simulation.viewModel.MainViewModel
import com.google.mlkit.vision.face.Face


class RiveFragment : Fragment() {

    //............values
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppInitializer.getInstance(requireContext())
            .initializeComponent(RiveInitializer::class.java)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rive, container, false)
        val girl = view.findViewById<RiveAnimationView>(R.id.girl)
        val p1 = view.findViewById<ProgressBar>(R.id.p1)
        val p2 = view.findViewById<ProgressBar>(R.id.p2)
        val p3 = view.findViewById<ProgressBar>(R.id.p3)
        val p4 = view.findViewById<ProgressBar>(R.id.p4)
        val face =  mainViewModel.face


        face.observe(requireActivity(), {
            animate(it, girl, p1, p2, p3, p4)
        })

        return view
    }

    private fun animate(
        face: Face,
        girl: RiveAnimationView,
        p1: ProgressBar,
        p2: ProgressBar,
        p3: ProgressBar,
        p4: ProgressBar
    ) {

        //----------------------right eye
        var Reye= face.rightEyeOpenProbability?.times(100f)
        if (Reye != null) {
            p1.progress = Reye.toInt()
            girl.setNumberState("StateR", "R", Reye)
        }

        //----------------------left eye
        var Leye= face.leftEyeOpenProbability?.times(100f)
        if (Leye != null) {
            p2.progress = Leye.toInt()
            girl.setNumberState("StateL", "L", Leye)
        }

        //----------------------smile
        var Smile= face.smilingProbability?.times(100f)
        if (Smile != null) {
            p3.progress = Smile.toInt()
            girl.setNumberState("StateS", "S", Smile)
        }

        //----------------------head
        var Head= face.headEulerAngleZ
        if (Head != null) {
            p4.progress = Head.toInt()*6
            girl.setNumberState("StateH", "H", Head)
        }
    }
}