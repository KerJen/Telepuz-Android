package com.telepuz.android.helper

import java.security.MessageDigest

fun hashBytes(input: String, algorithm: String): ByteArray {
    return MessageDigest
        .getInstance(algorithm)
        .digest(input.toByteArray())
}

fun byteToInt(bytes: ByteArray): Int {
    var result = 0
    var shift = 0
    for (byte in bytes) {
        result = result or (byte.toInt() shl shift)
        shift += 8
    }
    return result
}