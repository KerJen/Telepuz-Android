package com.telepuz.android.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.telepuz.android.R
import com.telepuz.android.view.adapters.MainViewPagerAdapter
import com.telepuz.android.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listenNewUser()
        viewModel.listenUserRemoved()
        viewModel.listenNewMessage()
        viewModel.getAllUsers()

        viewPager.adapter = MainViewPagerAdapter(this)
        requireView().viewPager!!.setCurrentItem(1, false)

        TabLayoutMediator(tabs, viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> {
                        tab.text = "Телепузы"
                    }
                    1 -> {
                        tab.text = "Чат-рум"
                    }
                }
            }).attach()
    }
}