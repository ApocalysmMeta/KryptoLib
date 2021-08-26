package dev.crash.blockbook

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import dev.crash.get
import dev.crash.post
import dev.crash.toObjString
import java.net.URL

abstract class BlockbookAPI(private val baseURL: String) {

    fun getStatus(): BlockBookAPIStatus {
        return jacksonObjectMapper().readValue(URL(baseURL.removeSuffix("/v2")).get())
    }

    fun getBlockHash(blockHeight: Int): String {
        return jacksonObjectMapper().readTree(URL("$baseURL/block-index/$blockHeight").get())["blockHash"].asText()
    }

    fun getTransaction(txid: String): BlockBookTransaction {
        return jacksonObjectMapper().readValue(URL("$baseURL/tx/$txid").get())
    }

    fun getAddress(address: String): BlockBookAddress {
        return jacksonObjectMapper().readValue(URL("$baseURL/address/$address").get())
    }

    fun getUTXOs(address: String, confirmed: Boolean = true): List<BlockBookUTXO> {
        return jacksonObjectMapper().readValue(URL("$baseURL/utxo/$address?confirmed=$confirmed").get())
    }

    fun getBlock(blockHeight: Int): BlockBookBlock = getBlock(blockHeight.toString())

    fun getBlock(blockHash: String): BlockBookBlock {
        return jacksonObjectMapper().readValue(URL("$baseURL/block/$blockHash"))
    }

    fun sendRawTransaction(hex: String): String {
        return jacksonObjectMapper().readTree(URL("$baseURL/sendtx").post(hashMapOf("hex" to hex)))["result"].asText()
    }

    fun getTickersList(timestamp: Long = System.currentTimeMillis()): List<String> {
        return jacksonObjectMapper().readValue(jacksonObjectMapper().readTree(URL("$baseURL/tickers-list?timestamp=$timestamp")
            .get())["available_currencies"].toObjString())
    }

    fun getTickers(timestamp: Long = System.currentTimeMillis()): Map<String, Double> {
        return jacksonObjectMapper().readValue(jacksonObjectMapper().readTree(URL("$baseURL/tickers?timestamp=$timestamp")
            .get())["rates"].toObjString())
    }

    fun getBalanceHistory(address: String, from: Long = System.currentTimeMillis()-1000*60*60*24, to: Long = System.currentTimeMillis())
    : List<BlockBookBalanceHistory> {
        return jacksonObjectMapper().readValue(URL("$baseURL/balancehistory/$address?from=$from&to=$to"))
    }
}