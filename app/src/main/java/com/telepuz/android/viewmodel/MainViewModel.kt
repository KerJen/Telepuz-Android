package com.telepuz.android.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.telepuz.android.model.Message
import com.telepuz.android.model.Results
import com.telepuz.android.model.User
import com.telepuz.android.model.repository.MainRepository

class MainViewModel @ViewModelInject constructor(private val repo: MainRepository) : ViewModel() {

    val usersLiveData = MutableLiveData<ArrayList<User>>()
    val userRemovedLiveData = MutableLiveData<String>()
    val userNewLiveData = MutableLiveData<User>()
    val newMessageLiveData = MutableLiveData<Message>()

    fun sendMessage(message: String) {
        repo.sendMessage(message) {
            val messageObject = Message(it.messageId, "", message, true, null)
            newMessageLiveData.postValue(messageObject)
        }
    }

    fun listenNewMessage() {
        repo.listenNewMessage {
            newMessageLiveData.postValue(it.message.apply {
                user = repo.users[it.message.userId]
            })
        }
    }

    fun listenUserRemoved() {
        repo.listenUserRemoved {
            userRemovedLiveData.postValue(it.userId)
        }
    }

    fun listenNewUser() {
        repo.listenNewUser {
            userNewLiveData.postValue(it.user)
        }
    }

    fun getAllUsers() {
        repo.getAllUsers {
            if (it.result == Results.OK.value) {
                usersLiveData.postValue(it.users)
            }
        }
    }
}