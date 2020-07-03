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

        telepuzWebSocketService.listener.webSocketStateCallback = {
            when(it){
                WebSocketState.CONNECTED -> {
                    Log.d("state", "connected")
                }
                WebSocketState.FAILURE ->{
                    Log.d("state", "FAILURE")
                }
                WebSocketState.CLOSED ->{
                    Log.d("state", "CLOSED")
                }
            }
        }

        telepuzWebSocketService.connect()
    }
}