package com.telepuz.android.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.telepuz.android.R
import com.telepuz.android.model.Message

class ChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var messages = mutableListOf<Message>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> YourMessageHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_your_message, parent, false
                )
            )
            else -> AnotherMessageHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_another_message, parent, false
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].yours) 0 else 1
    }

    override fun getItemCount() = messages.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]

        when (holder.itemViewType) {
            0 -> {
                val yourMessageHolder = holder as YourMessageHolder
                yourMessageHolder.text.text = messages[position].text
            }
            else -> {
                val anotherMessageHolder = holder as AnotherMessageHolder

                //TODO: Add avatar init

                if (position > 0 && messages[position - 1].userId != messages[position].userId) {
                    anotherMessageHolder.nickname.visibility = View.GONE
                }

                anotherMessageHolder.nickname.text = message.user.nickname
                anotherMessageHolder.text.text = message.text
            }
        }
    }

    class YourMessageHolder(item: View) : RecyclerView.ViewHolder(item) {
        var text: TextView = item.findViewById(R.id.yourMessageText)
    }

    class AnotherMessageHolder(item: View) : RecyclerView.ViewHolder(item) {
        var nickname: TextView = item.findViewById(R.id.anotherMessageNickname)
        var text: TextView = item.findViewById(R.id.anotherMessageText)
    }
}