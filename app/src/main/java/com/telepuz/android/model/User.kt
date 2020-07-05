package com.telepuz.android.model

import com.telepuz.android.helper.avatarColors
import com.telepuz.android.helper.byteToInt
import com.telepuz.android.helper.hashBytes

data class User(
    val id: String,
    val nickname: String,
    val status: String
) {
    fun getAvatarBackground() =
        avatarColors[byteToInt(hashBytes(nickname, "SHA-256")) % avatarColors.size]

    fun getFirstLetter() = nickname[0]
}