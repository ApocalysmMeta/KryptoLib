package dev.crash.websocket

import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI


abstract class Websocket(val url: String) : WebSocketClient(URI(url)) {

    private val exeOnOpen = mutableListOf<() -> Unit>()
    private val exeOnMessage = mutableListOf<(String) -> Unit>()
    private val exeOnClose = mutableListOf<(Int) -> Unit>()
    private val exeOnException = mutableListOf<(Exception) -> Unit>()

    override fun onOpen(handshakedata: ServerHandshake) {
        println("Connection to $url established!")
        exeOnOpen.forEach {
            it.invoke()
        }
    }

    fun onOpen(f: () -> Unit) = if(!isOpen) exeOnOpen.add(f) else null

    override fun onClose(code: Int, reason: String, remote: Boolean) {
        exeOnClose.forEach {
            it.invoke(code)
        }
    }

    fun onClose(f: (Int) -> Unit) {
        if(isOpen) exeOnClose.add(f) else onOpen {
            onClose(f)
        }
    }

    override fun onMessage(message: String) {
        exeOnMessage.forEach {
            it.invoke(message)
        }
    }

    fun onMessage(f: (String) -> Unit) {
        if(isOpen) exeOnMessage.add(f) else onOpen {
            onMessage(f)
        }
    }

    override fun onError(ex: Exception) {
        System.err.println("an error occurred:$ex")
    }

    fun onError(f: (Exception) -> Unit) = exeOnException.add(f)

    fun sendSafe(text: String) {
        if(isOpen){
            send(text)
        }else {
            onOpen {
                sendSafe(text)
            }
        }
    }
}