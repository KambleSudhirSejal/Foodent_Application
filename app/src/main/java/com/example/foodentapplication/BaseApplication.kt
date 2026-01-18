package com.example.foodentapplication

import android.app.Application
import com.example.foodentapplication.common.AppLogger
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)

        AppLogger.showFlow(
            "BaseApplication","Firebase initilized successfully"
        )
    }
}