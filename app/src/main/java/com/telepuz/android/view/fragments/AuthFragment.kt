package com.telepuz.android.view.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.telepuz.android.R
import com.telepuz.android.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment: Fragment(R.layout.fragment_auth) {

    private val viewModel: AuthViewModel by viewModels()

}