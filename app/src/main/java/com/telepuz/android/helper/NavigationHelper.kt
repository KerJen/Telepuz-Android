package com.telepuz.android.helper

import androidx.fragment.app.FragmentTransaction
import com.telepuz.android.R

fun FragmentTransaction.setPopAnimation(): FragmentTransaction {
    return setCustomAnimations(
        R.animator.animator_enter,
        R.animator.animator_exit,
        R.animator.animator_enter,
        R.animator.animator_exit
    )
}