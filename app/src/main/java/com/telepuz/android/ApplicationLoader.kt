package com.telepuz.android

import android.app.Application
import android.util.Log
import com.telepuz.android.network.TelepuzWebSocketService
import com.telepuz.android.network.WebSocketState
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ApplicationLoader : Application() {

    @Inject
    lateinit var telepuzWebSocketService: TelepuzWebSocketService

    override fun onCreate() {
        super.onCreate()


    }
}