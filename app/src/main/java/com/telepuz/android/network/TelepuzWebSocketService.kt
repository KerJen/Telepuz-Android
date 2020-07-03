package com.telepuz.android.network

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okio.ByteString.Companion.toByteString
import org.msgpack.jackson.dataformat.MessagePackFactory

class TelepuzWebSocketService {

    val listener = TelepuzWebSocketListener()
    private val okHttpClient = OkHttpClient()
    private lateinit var webSocketClient: WebSocket
    private val listenersPool = HashMap<String, ApiCallback<*>>()
    private val objectMapper = ObjectMapper(MessagePackFactory())
        .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
        .registerKotlinModule()

    fun connect() {
        val request: Request = Request.Builder().url("ws://35.228.119.156:5000").build()
        webSocketClient = okHttpClient.newWebSocket(request, listener)
        listenResponses()
    }

    private fun listenResponses() {
        listener.webSocketMessageCallback = {
            val methodName = objectMapper.readValue<String>(it)
            val methodBytesLength = methodName.toByteArray().size

            @Suppress("UNCHECKED_CAST")
            val emitter = listenersPool[methodName]?.observableEmitter as (Any) -> (Unit)
            val dataClass = listenersPool[methodName]?.dataClass

            val tree = objectMapper.readTree(it.copyOfRange(methodBytesLength+1, it.size))
            val response = objectMapper.treeToValue(tree, dataClass)

            emitter.invoke(response!!)
        }
    }

    fun <T> request(method: String, data: T) {
        val serializedMethodName = objectMapper.writeValueAsBytes(method)
        val serializedData = objectMapper.writeValueAsBytes(data)
        val serialized = serializedMethodName + serializedData

        webSocketClient.send(serialized.toByteString())
    }

    fun <T> on(method: String, dataClass: Class<T>, callback: (T) -> Unit) {
        listenersPool[method] = ApiCallback(callback, dataClass)
    }

    fun <T> once(method: String, dataClass: Class<T>, callback: (T) -> Unit) {
        listenersPool[method] = ApiCallback({
            listenersPool.remove(method)
            callback(it)
        }, dataClass)
    }
}