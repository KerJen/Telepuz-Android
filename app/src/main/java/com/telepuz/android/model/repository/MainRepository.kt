package com.telepuz.android.model.repository

import com.telepuz.android.model.User
import com.telepuz.android.model.dto.*
import com.telepuz.android.network.TelepuzWebSocketService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val service: TelepuzWebSocketService
) {

    val users = HashMap<String, User>()

    fun sendMessage(message: String, callback: (SendMessageResponseDTO) -> Unit) {
        service.once("messages.create", SendMessageResponseDTO::class.java, callback)
        service.request("messages.create", SendMessageRequestDTO(message))
    }

    fun listenNewMessage(callback: (MessageNewUpdateDTO) -> Unit) {
        service.on("messages.created", MessageNewUpdateDTO::class.java, callback)
    }

    fun listenNewUser(callback: (UserNewUpdateDTO) -> Unit) {
        service.on("users.created", UserNewUpdateDTO::class.java) {
            users[it.user.id] = it.user
            callback(it)
        }
    }

    fun listenUserRemoved(callback: (UserRemovedUpdateDTO) -> Unit) {
        service.on("users.removed", UserRemovedUpdateDTO::class.java) {
            users.remove(it.userId)
            callback(it)
        }
    }

    fun getAllUsers(callback: (UsersResponseDTO) -> (Unit)) {
        service.once("users.get", UsersResponseDTO::class.java) {
            it.users.forEach { user ->
                users[user.id] = user
            }
            callback(it)
        }
        service.request("users.get")
    }
}