package dev.crash.sushi

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import dev.crash.get
import dev.crash.getObjectOfList
import java.net.URL

object SushiSwap {
    private const val baseURL: String = "https://api2.sushipro.io"

    fun getPairs(chain: SushiChain = SushiChain.ETHEREUM): List<SushiSwapPair> {
        return jacksonObjectMapper().readTree(URL("$baseURL?action=all_pairs&chainID=${chain.chainId}").get()).getObjectOfList(2)
    }

    fun getTokens(chain: SushiChain = SushiChain.ETHEREUM): List<SushiSwapToken> {
        return jacksonObjectMapper().readTree(URL("$baseURL?action=all_tokens&chainID=${chain.chainId}").get()).getObjectOfList(2)
    }
    //https://docs.sushipro.io/sushiswap/get_pair
    fun getPair(pairContractAddress: String, chain: SushiChain): SushiSwapPair {
        return jacksonObjectMapper().readValue<List<SushiSwapPair>>(URL("$baseURL?action=get_pair&chainID=${chain.chainId}&pair=$pairContractAddress").get())[0]
    }
}