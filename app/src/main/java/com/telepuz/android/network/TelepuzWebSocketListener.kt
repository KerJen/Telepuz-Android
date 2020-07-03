package com.telepuz.android.network

import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class TelepuzWebSocketListener : WebSocketListener() {

    var webSocketStateCallback: ((WebSocketState)->(Unit))? = null
    var webSocketMessageCallback: ((ByteArray)->(Unit))? = null


    override fun onOpen(webSocket: WebSocket, response: Response) {
        webSocketStateCallback?.invoke(WebSocketState.CONNECTED)
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        webSocketStateCallback?.invoke(WebSocketState.CLOSED)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        webSocketStateCallback?.invoke(WebSocketState.FAILURE)
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        webSocketMessageCallback?.invoke(bytes.toByteArray())
    }
}