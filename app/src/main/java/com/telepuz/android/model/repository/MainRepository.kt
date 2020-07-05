package com.telepuz.android.model.repository

import com.telepuz.android.model.dto.UsersResponseDTO
import com.telepuz.android.network.TelepuzWebSocketService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val service: TelepuzWebSocketService
) {

    fun getAllUsers(callback: (UsersResponseDTO) -> (Unit)) {
        service.once("users.get", UsersResponseDTO::class.java, callback)
        service.request("users.get")
    }
}