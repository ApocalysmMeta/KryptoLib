package dev.crash.dogechain

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import dev.crash.getChildObj
import dev.crash.websocket.Websocket

class DogechainWebsocket : Websocket("wss://ws.dogechain.info/inv") {
    private val exeOnTransaction = mutableListOf<(DogechainWebsocketTransaction) -> Unit>()
    private val exeOnBlock = mutableListOf<(DogechainWebsocketBlock) -> Unit>()
    private val exeOnAddressTransaction = hashMapOf<String, MutableList<(DogechainWebsocketTransaction) -> Unit>>()

    init {
        connect()
        onMessage { message ->
            val root = jacksonObjectMapper().readTree(message)
            when(root["op"].asText()){
                "utx" -> {
                    val obj = root.getChildObj<DogechainWebsocketTransaction>("x")
                    exeOnTransaction.forEach {
                        it.invoke(obj)
                    }
                    obj.inputs.forEach { input ->
                        exeOnAddressTransaction[input.prev_out.addr]?.forEach {
                            it.invoke(obj)
                        }
                    }
                }
                "block" -> {
                    val obj = root.getChildObj<DogechainWebsocketBlock>("x")
                    exeOnBlock.forEach {
                        it.invoke(obj)
                    }
                }
            }
        }
    }

    fun subscribeToUnconfirmedTransactions() = sendSafe("{\"op\":\"unconfirmed_sub\"}")

    fun subscribeToAddress(address: String) = sendSafe("{\"op\":\"addr_sub\", \"addr\":\"$address\"}")

    fun subscribeToNewBlocks() = sendSafe("{\"op\":\"blocks_sub\"}")

    fun getLastBlock() = sendSafe("{\"op\":\"ping_block\"}")

    fun onTransaction(f: (DogechainWebsocketTransaction) -> Unit) = exeOnTransaction.add(f)

    fun onTransaction(address: String, f: (DogechainWebsocketTransaction) -> Unit) {
        if(!exeOnAddressTransaction.containsKey(address)){
            exeOnAddressTransaction[address] = mutableListOf()
        }
        exeOnAddressTransaction[address]!!.add(f)
    }

    fun onBlock(f: (DogechainWebsocketBlock) -> Unit) = exeOnBlock.add(f)
}