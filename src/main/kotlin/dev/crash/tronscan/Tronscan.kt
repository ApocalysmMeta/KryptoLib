package dev.crash.tronscan

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.net.URL

object Tronscan {
    private const val baseURL = "https://apilist.tronscan.org/api"

    fun getLatestBlock(): TronscanBlock {
        return jacksonObjectMapper().readValue(URL("$baseURL"))
    }

    //https://github.com/tronscan/tronscan-frontend/blob/dev2019/document/api.md
}