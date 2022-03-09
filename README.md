<h1 align="center">Face Lanmarks Detecter That Animates Virtual Avatar</h1>

<p align="center">  
 This app uses Face detection to get the of the face landmarks coordinates.
 then the Landmarks coordinates, the head orientation and the eyes and mouth open probability is applied on a costume made 2d character animated using Rive 
</p>
</br>

<img width="1147" alt="virtual avatar" src="https://user-images.githubusercontent.com/98290339/157391663-02ae31e9-dbe0-4b89-94e7-59fcb77d99b2.png">

## Tech stack & Open-source libraries
 - build in android studio. kotlin
- [CameraX](https://developer.android.com/training/camerax)
- [Processing](https://android.processing.org)
- [Face Detection](https://developers.google.com/ml-kit/vision/face-detection)
- [Rive](https://rive.app/)
- [Material Ui](https://material.io/)

## What I learned
- Using [Face detection](https://developers.google.com/ml-kit/vision/face-detection)
- Drawing polygons in processing
- Making state machines in rive to control the flow of the unites animation state
- Updating the rive view on the change of the Face data 
- observing LiveData
 
## Final app

## Data from the Face detection Class 

![image](https://user-images.githubusercontent.com/98290339/152496260-f0f223a4-5723-4ca2-85fe-e57969e5426d.png)

## Architecture
Foody is based on the MVVM architecture and the Repository pattern.
![image](https://user-images.githubusercontent.com/98290339/152096381-2a8898d3-c351-4032-979d-ebc836e46332.png)

