package com.telepuz.android

import android.app.Application
import com.telepuz.android.network.TelepuzWebSocketService
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ApplicationLoader : Application() {

    @Inject
    lateinit var telepuzWebSocketService: TelepuzWebSocketService
}