package dev.crash.blockbook

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import dev.crash.getChildObj
import dev.crash.websocket.Websocket

abstract class BlockBookWebsocket(url: String) : Websocket(url) {
    private val exeOnBlock = mutableListOf<(BlockBookWebsocketBlock) -> Unit>()
    private val exeOnTransaction = mutableListOf<(String) -> Unit>()
    private val exeOnFiatRate = mutableListOf<(Map<String, Double>) -> Unit>()

    init {
        connect()
        onMessage { message ->
            val obj = jacksonObjectMapper().readTree(message)
            if(obj["data"].has("subscribed")) {
                println(message)
                return@onMessage
            }
            when(obj["id"].asText()){
                "1" -> {
                    val txObj = obj.getChildObj<BlockBookWebsocketBlock>("data")
                    exeOnBlock.forEach {
                        it.invoke(txObj)
                    }
                }
                "2", "4" -> {
                    exeOnTransaction.forEach {
                        it.invoke(obj["data"].asText())
                    }
                }
                "3" -> {
                    val map = obj.getChildObj<BlockBookWebsocketRates>("data").rates
                    exeOnFiatRate.forEach {
                        it.invoke(map)
                    }
                }
            }
        }
    }
    fun subscribeToNewBlocks() = sendSafe(jacksonObjectMapper().writeValueAsString(BlockBookWebsocketRequest("1", "subscribeNewBlock")))

    fun onNewBlock(f: (BlockBookWebsocketBlock) -> Unit) = exeOnBlock.add(f)

    fun subscribeToNewTransactions() = sendSafe(jacksonObjectMapper().writeValueAsString(BlockBookWebsocketRequest("2", "subscribeNewTransaction")))

    fun onNewTransaction(f: (String) -> Unit) = exeOnTransaction.add(f)

    fun subscribeToFiatRates() = sendSafe(jacksonObjectMapper().writeValueAsString(BlockBookWebsocketRequest("3", "subscribeFiatRates")))

    fun onFiatRates(f: (Map<String, Double>) -> Unit) = exeOnFiatRate.add(f)

    fun subscribeToAddresses(addresses: List<String>) =
        sendSafe(jacksonObjectMapper().writeValueAsString(BlockBookWebsocketRequest("4", "subscribeAddresses", addresses)))
}