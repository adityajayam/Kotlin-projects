package com.aj.visiontextrecognition

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var cameraPreview: PreviewView? = null
    private lateinit var takePhoto: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize();
        if (checkAllPermissionGranted()) {
            //TODO call camera
            CameraUtil.startCamera(this, this, camera_preview.createSurfaceProvider())
        } else {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_CAMERA_PERMISSION
            )
        }
    }

    private fun initialize() {
        cameraPreview = findViewById(R.id.camera_preview)
        takePhoto = findViewById(R.id.take_photo)
        takePhoto.setOnClickListener { Log.e(TAG, "button click") }
    }

    private fun checkAllPermissionGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            this,
            it
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_CAMERA_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //TODO start camera
                    CameraUtil.startCamera(this, this, camera_preview.createSurfaceProvider())
                } else {
                    Toast.makeText(
                        this,
                        "Permissions not granted.",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val REQUEST_CODE_CAMERA_PERMISSION = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }
}