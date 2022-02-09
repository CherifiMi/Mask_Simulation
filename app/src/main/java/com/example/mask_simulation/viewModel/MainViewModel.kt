package com.example.mask_simulation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.mlkit.vision.face.Face

class MainViewModel: ViewModel() {


    private val mutableFace = MutableLiveData<Face>()
    val face: LiveData<Face> get()= mutableFace

    fun addFace(face: Face){
        mutableFace.value = face
    }



}