<h1 align="center">Real time face tracking applied to a 2D virtual Avatar</h1>

<p align="center">  
 This app uses Face detection to get the of the face landmarks coordinates from the live CameraX feed.
 Then it sends the data to the Rive State Machine functions to control the value of the eyes opening, the mouth smiling and the head tilt 
 
 The Animation Iam using is a simple proof of concept. Any animation can be used here such as a Vtuber avatar.
</p>
</br>

<img width="1147" alt="virtual avatar" src="https://user-images.githubusercontent.com/98290339/159192469-82562d97-c1f6-4a86-abb3-1bb7eafd40d1.png">


## Tech stack & Open-source libraries
 - build in android studio. kotlin
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [CameraX](https://developer.android.com/training/camerax)
- [Processing](https://android.processing.org)
- [Face Detection](https://developers.google.com/ml-kit/vision/face-detection)


## The Data we Get from the Face detection Library 
![image 1](https://user-images.githubusercontent.com/98290339/153493447-c812167d-b6da-4051-9b4d-f5f0a8a236e9.png)


## What I learned
- using [Face detection](https://developers.google.com/ml-kit/vision/face-detection)
- drawing polygons in processing
- using [Rive's State Machines](https://help.rive.app/editor/state-machine)
- using [Rive](https://help.rive.app) in jetpack


## Architecture
![image](https://user-images.githubusercontent.com/98290339/152096381-2a8898d3-c351-4032-979d-ebc836e46332.png)


