package com.telepuz.android.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.telepuz.android.model.repository.AuthRepository

class AuthViewModel @ViewModelInject constructor(private val repo: AuthRepository) : ViewModel() {

    private val nicknameRegex = Regex("^[A-zА-яЁё0-9\\\\s]{1,30}\$")

    val nicknameResponseLiveData = MutableLiveData<Pair<Boolean, String>>()
    val nicknameRegexLiveData = MutableLiveData<Boolean>()

    fun sendNickname(nickname: String) {
        repo.sendNickname(nickname) {
            //TODO: исправить
            nicknameResponseLiveData.postValue((it.result == 0) to it.userId)
        }
    }

    fun checkNickname(nickname: String) {
        if (nicknameRegex.matches(nickname)) {
            nicknameRegexLiveData.postValue(true)
        } else {
            nicknameRegexLiveData.postValue(false)
        }
    }
}