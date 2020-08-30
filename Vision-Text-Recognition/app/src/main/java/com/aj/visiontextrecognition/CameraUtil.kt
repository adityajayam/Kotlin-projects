package com.aj.visiontextrecognition

import android.content.Context
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import java.lang.Exception

class CameraUtil {
    companion object {
         fun startCamera(context: Context, lifecycleOwner: LifecycleOwner ,cameraPreview: Preview.SurfaceProvider) {
            val processCameraProvider = ProcessCameraProvider.getInstance(context)
            processCameraProvider.addListener(Runnable {
                val cameraProvider: ProcessCameraProvider = processCameraProvider.get()
                val preview =
                    Preview.Builder().build()
                preview.setSurfaceProvider(cameraPreview)
/*
            val preview1 = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(camera_preview.createSurfaceProvider())
                }
            preview 1 and preview both are same , also is used to eliminate preview.setSurfaceProvider(camera_preview.createSurfaceProvider())
            When you see also in the code, you can read it as “and also do the following with the object.”
*/
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, preview)
                } catch (e: Exception) {
                    Log.e("", e.localizedMessage)
                }
            }, ContextCompat.getMainExecutor(context))
        }
    }
}