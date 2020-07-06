package com.telepuz.android.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.telepuz.android.R
import com.telepuz.android.helper.setPopAnimation
import com.telepuz.android.network.TelepuzWebSocketService
import com.telepuz.android.network.WebSocketState.CONNECTED
import com.telepuz.android.view.fragments.AuthFragment
import com.telepuz.android.view.fragments.MainFragment
import com.telepuz.android.view.fragments.NoInternetFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AppActivity : AppCompatActivity(R.layout.activity_app) {

    @Inject
    lateinit var telepuzWebSocketService: TelepuzWebSocketService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        telepuzWebSocketService.listener.webSocketStateCallback.add {
            when (it) {
                CONNECTED -> {
                    openAuthFragment()
                }
                else -> {
                    if (supportFragmentManager.findFragmentById(R.id.fragmentContainer) !is NoInternetFragment)
                        openNoInternetFragment()
                }
            }
        }
        telepuzWebSocketService.connect()
    }

    private fun openNoInternetFragment() {
        supportFragmentManager.commit {
            setPopAnimation()
            replace(R.id.fragmentContainer, NoInternetFragment())
        }
    }

    fun openAuthFragment() {
        supportFragmentManager.commit {
            setPopAnimation()
            replace(R.id.fragmentContainer, AuthFragment())
        }
    }

    fun openChatFragment() {
        supportFragmentManager.commit {
            setPopAnimation()
            replace(R.id.fragmentContainer, MainFragment())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        telepuzWebSocketService.listener.webSocketStateCallback.clear()
    }
}