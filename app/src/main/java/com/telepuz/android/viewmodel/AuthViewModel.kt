package com.telepuz.android.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.telepuz.android.model.AuthRepository

class AuthViewModel @ViewModelInject constructor(repo: AuthRepository): ViewModel() {

}