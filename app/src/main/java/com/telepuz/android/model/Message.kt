package com.telepuz.android.model

import com.fasterxml.jackson.annotation.JsonIgnore

data class Message(
    var id: String,
    val userId: String,
    val text: String,
    @JsonIgnore val yours: Boolean,
    @JsonIgnore var user: User?
)