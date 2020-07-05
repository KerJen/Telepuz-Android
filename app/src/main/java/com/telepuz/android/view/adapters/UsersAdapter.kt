package com.telepuz.android.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.telepuz.android.R
import com.telepuz.android.model.User

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UserHolder>() {
    var users = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_user, parent, false
            )
        )
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val user = users[position]
        //TODO: Add avatar init
        holder.nickname.text = user.nickname
        holder.status.text = user.status
    }

    class UserHolder(item: View) : RecyclerView.ViewHolder(item) {
        val nickname: TextView = item.findViewById(R.id.userNickname)
        val status: TextView = item.findViewById(R.id.userStatus)
    }
}
