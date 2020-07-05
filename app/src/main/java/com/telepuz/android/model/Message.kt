package com.telepuz.android.model

import com.fasterxml.jackson.annotation.JsonIgnore

data class Message(
    val id: String,
    val userId: String,
    val text: String,
    @JsonIgnore val yours: Boolean,
    val user: User
)