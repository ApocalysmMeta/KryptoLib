package dev.crash.tronscan

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import dev.crash.get
import java.net.URL

object Tronscan {
    private const val baseURL = "https://apilist.tronscan.org/api"

    fun getLatestBlock(): TronscanLatestBlock {
        return jacksonObjectMapper().readValue(URL("$baseURL/block/latest").get())
    }

    fun getAccountList(limit: Int = 20, start: Int = 0, sort: String = "-balance"): TronscanAddressList {
        return jacksonObjectMapper().readValue(URL("$baseURL/account/list?sort=$sort&limit=$limit&start=$start").get())
    }

    fun getAccount(address: String): TronscanAccount {
        return jacksonObjectMapper().readValue(URL("$baseURL/account?address=$address").get())
    }

    fun getBlocks(startTimestamp: Long, endTimestamp: Long = System.currentTimeMillis(), sort: String = "-number", limit: Int = 20, start: Int = 20,
                  count: Boolean = true): List<TronscanBlock> {
        return jacksonObjectMapper().readValue<TronscanResponse<List<TronscanBlock>>>(URL
            ("$baseURL/block?sort=$sort&limit=$limit&count=$count&start=$start&start_timestamp=$startTimestamp&end_timestamp=$endTimestamp").get()).data
    }

    fun getBlocks(producer: String, sort: String = "-number", limit: Int = 20, start: Int = 20, count: Boolean = true): List<TronscanBlock> {
        return jacksonObjectMapper().readValue<TronscanResponse<List<TronscanBlock>>>(URL
            ("$baseURL/block?sort=$sort&limit=$limit&count=$count&start=$start&producer=$producer").get()).data
    }

    fun getBlock(blockNumber: Int): TronscanBlock {
        return jacksonObjectMapper().readValue<TronscanResponse<List<TronscanBlock>>>(URL("$baseURL/block?number=$blockNumber").get()).data.first()
    }

    fun getTransactions(startTimestamp: Long, endTimestamp: Long = System.currentTimeMillis(), sort: String = "-timestamp", limit: Int = 20, start: Int = 20,
                        count: Boolean = true): TronscanTransactionResponse {
        return jacksonObjectMapper().readValue(URL
            ("$baseURL/transaction?sort=$sort&limit=$limit&count=$count&start=$start&start_timestamp=$startTimestamp&end_timestamp=$endTimestamp").get())
    }

    fun getTransactionsOfAddress(address: String, sort: String = "-timestamp", limit: Int = 20, start: Int = 20, count: Boolean = true)
    : TronscanTransactionResponse {
        return jacksonObjectMapper().readValue(URL
            ("$baseURL/transaction?sort=$sort&limit=$limit&count=$count&start=$start&address=$address").get())
    }

    fun getTransactionsOfContract(contractAddress: String, sort: String = "-timestamp", limit: Int = 20, start: Int = 20, count: Boolean = true)
    : TronscanTransactionResponse {
        return jacksonObjectMapper().readValue(URL("$baseURL/transaction?sort=$sort&limit=$limit&count=$count&start=$start&contract=$contractAddress").get())
    }

    fun getTransaction(hash: String): TronscanTransaction {
        return jacksonObjectMapper().readValue(URL("$baseURL/transaction-info?hash=$hash"))
    }

    fun getTransfers(startTimestamp: Long, endTimestamp: Long = System.currentTimeMillis(), sort: String = "-timestamp", limit: Int = 20, start: Int = 20,
                     count: Boolean = true): TronscanTransferResponse {
        return jacksonObjectMapper().readValue(URL
            ("$baseURL/transfer?sort=$sort&limit=$limit&count=$count&start=$start&start_timestamp=$startTimestamp&end_timestamp=$endTimestamp").get())
    }

    fun getTransfers(address: String, sort: String = "-timestamp", limit: Int = 20, start: Int = 20, count: Boolean = true, token: String = "_")
    : TronscanTransferResponse {
        return jacksonObjectMapper().readValue(URL("$baseURL/transfer?sort=$sort&limit=$limit&count=$count&start=$start&address=$address&token=$token").get())
    }

    fun getNodes(): TronscanNodeResponse {
        return jacksonObjectMapper().readValue(URL("$baseURL/nodemap").get())
    }

    fun getDonators(): List<JsonNode> {
        return jacksonObjectMapper().readValue(URL("$baseURL/listdonators").get())
    }

    fun getFunds(): TronscanFunds {
        return jacksonObjectMapper().readValue(URL("$baseURL/funds").get())
    }

    //https://github.com/tronscan/tronscan-frontend/blob/dev2019/document/api.md
}