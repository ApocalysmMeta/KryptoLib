package dev.crash.reddsight

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import dev.crash.get
import dev.crash.post
import dev.crash.toInt
import java.math.BigInteger
import java.net.URL

object Reddsight {
    private val baseURL = "https://live.reddcoin.com/api"

    fun getBlock(blockHash: String): ReddsightBlock {
        val response = URL("$baseURL/block/$blockHash").get()
        return jacksonObjectMapper().readValue(response)
    }

    fun getTransaction(txid: String): ReddsightTransaction {
        val response = URL("$baseURL/tx/$txid").get()
        return jacksonObjectMapper().readValue(response)
    }

    fun getAddressData(address: String, noTxList: Boolean = false, noCache: Boolean = false): ReddsightAddress {
        val response = URL("$baseURL/addr/$address?noTxList?noTxList=${noTxList.toInt()}&noCache=${noCache.toInt()}").get()
        return jacksonObjectMapper().readValue(response)
    }

    fun getBalance(address: String): BigInteger {
        return URL("$baseURL/addr/$address/balance").get().toBigInteger()
    }

    fun getTotalReceived(address: String): BigInteger {
        return URL("$baseURL/addr/$address/totalReceived").get().toBigInteger()
    }

    fun getTotalSent(address: String): BigInteger {
        return URL("$baseURL/addr/$address/totalSent").get().toBigInteger()
    }

    fun getUnconfirmedBalance(address: String): BigInteger {
        return URL("$baseURL/addr/$address/unconfirmedBalance").get().toBigInteger()
    }

    fun getUTXOs(address: String, noCache: Boolean = false): List<ReddsightUTXO> {
        val respone = URL("$baseURL/addr/$address/utxo?noCache=${noCache.toInt()}").get()
        return jacksonObjectMapper().readValue(respone)
    }

    fun getTransactionsByBlock(blockHash: String): List<ReddsightTransaction> {
        val response = URL("$baseURL/txs/?block=$blockHash").get()
        return jacksonObjectMapper().readValue<ReddsightPageResponse<ReddsightTransaction>>(response).txs
    }

    fun getTransactionsByAddress(address: String): List<ReddsightTransaction> {
        val response = URL("$baseURL/txs/?address=$address").get()
        return jacksonObjectMapper().readValue<ReddsightPageResponse<ReddsightTransaction>>(response).txs
    }

    fun pushTx(rawTx: String): String {
        val response = URL("$baseURL/tx/send").post(hashMapOf("rawtx" to rawTx))
        return jacksonObjectMapper().readTree(response)["txid"].asText()
    }

    fun syncStatus(): ReddsightSyncStatus {
        val response = URL("$baseURL/sync").get()
        return jacksonObjectMapper().readValue(response)
    }
}