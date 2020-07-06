package com.telepuz.android.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.telepuz.android.R
import com.telepuz.android.view.adapters.ChatAdapter
import com.telepuz.android.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_chat.*

@AndroidEntryPoint
class ChatFragment : Fragment(R.layout.fragment_chat) {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var messagesAdapter: ChatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        messagesAdapter = ChatAdapter()
        messagesList.adapter = messagesAdapter
        messagesList.layoutManager = LinearLayoutManager(context).apply {
            stackFromEnd = true
        }
        messagesList.itemAnimator = null

        viewModel.newMessageLiveData.observe(viewLifecycleOwner) {
            if (it.yours) messageInput.setText("")
            messagesAdapter.messages.add(it)
            val position = messagesAdapter.messages.size
            messagesAdapter.notifyItemInserted(position)
            messagesList.scrollToPosition(position - 1)
        }

        sendButton.setOnClickListener {
            viewModel.sendMessage(messageInput.text.toString())
        }
    }
}