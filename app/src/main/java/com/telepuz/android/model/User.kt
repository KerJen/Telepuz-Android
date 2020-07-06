package com.telepuz.android.model

import android.graphics.Color
import com.telepuz.android.helper.avatarColors
import kotlin.math.abs

data class User(
    val id: String,
    val nickname: String,
    val status: String
) {
    fun getColor() =
        Color.parseColor(avatarColors[abs(nickname.hashCode()) % avatarColors.size])

    fun getFirstLetter() = nickname[0].toUpperCase().toString()
}