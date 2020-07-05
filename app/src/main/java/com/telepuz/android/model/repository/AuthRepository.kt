package com.telepuz.android.model.repository

import com.telepuz.android.model.dto.NicknameRequestDTO
import com.telepuz.android.model.dto.NicknameResponseDTO
import com.telepuz.android.network.TelepuzWebSocketService
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val service: TelepuzWebSocketService
) {

    fun sendNickname(nickname: String, callback: (NicknameResponseDTO) -> (Unit) ) {
        service.once("users.create", NicknameResponseDTO::class.java, callback)
        service.request("users.create", NicknameRequestDTO(nickname))
    }
}