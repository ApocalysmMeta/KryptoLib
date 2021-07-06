package de.crash.pancake

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import de.crash.get
import java.net.URL

object PancakeSwapV1API {
    private const val baseUrl = "https://api.pancakeswap.info/api"

    fun getTokens(): PancakeSwapTokensResponse {
        val response = URL("$baseUrl/tokens").get()
        return jacksonObjectMapper().readValue(response)
    }

    fun getTokenData(contract: String): PancakeSwapTokenResponse {
        val response = URL("$baseUrl/tokens/$contract").get()
        return jacksonObjectMapper().readValue(response)
    }
}