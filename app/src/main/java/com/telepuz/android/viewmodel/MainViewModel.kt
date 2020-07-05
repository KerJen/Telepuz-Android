package com.telepuz.android.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telepuz.android.model.Results
import com.telepuz.android.model.User
import com.telepuz.android.model.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(private val repo: MainRepository) : ViewModel() {

    val usersLiveData = MutableLiveData<ArrayList<User>>()

    fun getAllUsers() = viewModelScope.launch(Dispatchers.IO) {
        repo.getAllUsers {
            if (it.result == Results.OK.value) {
                usersLiveData.postValue(it.users)
            }
        }
    }
}