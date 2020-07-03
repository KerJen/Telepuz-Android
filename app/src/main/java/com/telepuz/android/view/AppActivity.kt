package com.telepuz.android.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.telepuz.android.R
import com.telepuz.android.view.fragments.AuthFragment
import com.telepuz.android.view.fragments.ChatFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppActivity : AppCompatActivity(R.layout.activity_app) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openAuthFragment()
    }

    fun openAuthFragment() {
        supportFragmentManager.commit {
            replace(R.id.fragmentContainer, AuthFragment())
        }
    }

    fun openChatFragment() {
        supportFragmentManager.commit {
            replace(R.id.fragmentContainer, ChatFragment())
        }
    }
}