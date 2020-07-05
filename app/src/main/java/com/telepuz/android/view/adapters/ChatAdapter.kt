package com.telepuz.android.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.telepuz.android.R
import com.telepuz.android.model.Message
import com.telepuz.android.view.custom.BackgroundAvatarView

class ChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var messages = ArrayList<Message>()

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

    override fun getItemViewType(position: Int) = if (messages[position].yours) 0 else 1

    override fun getItemCount() = messages.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        val user = messages[position].user

        when (holder.itemViewType) {
            0 -> {
                val yourMessageHolder = holder as YourMessageHolder
                yourMessageHolder.text.text = messages[position].text
            }
            else -> {
                val anotherMessageHolder = holder as AnotherMessageHolder

                if (position > 0 && messages[position - 1].userId != messages[position].userId) {
                    anotherMessageHolder.nickname.visibility = View.GONE
                } else {
                    anotherMessageHolder.avatar.setAvatar(
                        user.getFirstLetter(),
                        user.getAvatarBackground()
                    )
                    anotherMessageHolder.nickname.text = message.user.nickname
                }

                anotherMessageHolder.text.text = message.text
            }
        }
    }

    class YourMessageHolder(item: View) : RecyclerView.ViewHolder(item) {
        var text: TextView = item.findViewById(R.id.yourMessageText)
    }

    class AnotherMessageHolder(item: View) : RecyclerView.ViewHolder(item) {
        var avatar: BackgroundAvatarView = item.findViewById(R.id.anotherMessageAvatar)
        var nickname: TextView = item.findViewById(R.id.anotherMessageNickname)
        var text: TextView = item.findViewById(R.id.anotherMessageText)
    }
}