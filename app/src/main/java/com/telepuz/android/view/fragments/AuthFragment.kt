package com.telepuz.android.view.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.telepuz.android.R
import com.telepuz.android.view.AppActivity
import com.telepuz.android.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_auth.*

@AndroidEntryPoint
class AuthFragment : Fragment(R.layout.fragment_auth) {

    private val viewModel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.nicknameResponseLiveData.observe(viewLifecycleOwner) {
            if (it.first) {
                (activity as AppActivity).openChatFragment()
            } else {
                setStateEnterButton()
            }
        }

        viewModel.nicknameRegexLiveData.observe(viewLifecycleOwner) {
            enterButton.isEnabled = it
        }

        nicknameInput.addTextChangedListener {
            viewModel.checkNickname(nicknameInput.text.toString())
        }

        enterButton.setOnClickListener {
            viewModel.sendNickname(nicknameInput.text.toString())
        }

        linkText.setOnClickListener {
            setStateLoadingButton()
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://teleg.run/kerjen")))
        }
    }

    private fun setStateEnterButton() {
        enterButton.isEnabled = true
        enterButton.setText(R.string.auth_enter_button_text)
    }

    private fun setStateLoadingButton() {
        enterButton.isEnabled = false
        enterButton.setText(R.string.auth_loading_button_text)
    }
}