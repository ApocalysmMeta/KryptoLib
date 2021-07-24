package dev.crash.tronscan

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import dev.crash.get
import java.net.URL

object TronscanAPI {
    private const val baseUrl = "https://apilist.tronscan.org/api"

    fun getSystemStatus(): TronscanSystemStatus {
        val response = URL("$baseUrl/system/status").get()
        return jacksonObjectMapper().readValue(response)
    }

    fun getLatestBlock(): TronscanBlock {
        val response = URL("$baseUrl/block/latest").get()
        return jacksonObjectMapper().readValue(response)
    }

    fun listAccounts(limit: Int = 20, start: Int = 0, sort: String = "-balance"): TronscanAccountListObj {
        val response = URL("$baseUrl/account/list?sort=$sort&limit=$limit&start=$start").get()
        return jacksonObjectMapper().readValue(response)
    }

    fun getAccount(address: String): TronscanAccount {
        val response = URL("$baseUrl/account?address=$address").get()
        return jacksonObjectMapper().readValue(response)
    }
}