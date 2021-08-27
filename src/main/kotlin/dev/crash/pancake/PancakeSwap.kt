package dev.crash.pancake

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import dev.crash.get
import java.net.URL

object PancakeSwap {
    private const val baseUrl = "https://api.pancakeswap.info/api/v2"

    fun getTokens(): PancakeSwapTokensResponse {
        val response = URL("$baseUrl/tokens").get()
        return jacksonObjectMapper().readValue(response)
    }

    fun getTokenData(contract: String): PancakeSwapTokenResponse {
        val response = URL("$baseUrl/tokens/$contract").get()
        return jacksonObjectMapper().readValue(response)
    }
}