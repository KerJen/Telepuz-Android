package com.telepuz.android.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.telepuz.android.R
import com.telepuz.android.view.adapters.ChatAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_chat.*

@AndroidEntryPoint
class ChatFragment : Fragment(R.layout.fragment_chat) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ChatAdapter()

        messagesList.adapter = adapter
    }
}