package com.telepuz.android.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.telepuz.android.R
import com.telepuz.android.view.adapters.UsersAdapter
import com.telepuz.android.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_users.*

@AndroidEntryPoint
class UsersFragment : Fragment(R.layout.fragment_users) {

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usersAdapter = UsersAdapter()

        usersList.adapter = usersAdapter
        usersList.layoutManager = LinearLayoutManager(context)

        viewModel.usersLiveData.observe(viewLifecycleOwner) {
            usersAdapter.users = it
            usersAdapter.notifyDataSetChanged()
        }

        viewModel.getAllUsers()
    }
}