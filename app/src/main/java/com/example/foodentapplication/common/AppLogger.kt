package com.example.foodentapplication.common

import android.util.Log
import com.example.foodentapplication.presentation.navigation.App

object AppLogger {


     const val APP_TAG = "Sejal"

    fun showFlow(normalFlow:String,message:String){
        Log.d(APP_TAG, "🖥️ normalFlowActivity [$normalFlow] → $message")
    }


    fun ui(screen:String,message:String){
        Log.d(APP_TAG, "🖥️ UI [$screen] → $message")
    }

    fun viewModel(vm:String,message:String){
        Log.d(APP_TAG, "📦 VM [$vm] → $message")
    }

    fun repo(repo:String,message:String){
        Log.d(APP_TAG, "🗄️ REPO [$repo] → $message")
    }

    fun api(source:String,message:String){
        Log.d(APP_TAG, "🌐 API [$source] → $message")
    }

    fun error(source :String,message:String,throwable: Throwable?=null){
        Log.e(APP_TAG, "❌ ERROR [$source] → $message", throwable)
    }





}