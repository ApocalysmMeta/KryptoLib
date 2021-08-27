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

    fun getPair(pairContractAddress: String, chain: SushiChain = SushiChain.ETHEREUM): SushiSwapPair {
        return jacksonObjectMapper().
        readValue<List<SushiSwapPair>>(URL("$baseURL?action=get_pair&chainID=${chain.chainId}&pair=$pairContractAddress").get())[0]
    }

    fun getPairsByToken(tokenContractAddress: String, chain: SushiChain = SushiChain.ETHEREUM): List<SushiSwapPair> {
        return jacksonObjectMapper().readTree(URL("$baseURL?action=get_pairs_by_token&token=$tokenContractAddress&chainID=${chain.chainId}").get())
            .getObjectOfList(2)
    }

    fun getHistoricalLiquidity(pairContractAddress: String, from: Long, to: Long = System.currentTimeMillis(), chain: SushiChain = SushiChain.ETHEREUM)
    : List<SushiSwapHistoricalLiquidity> {
        return jacksonObjectMapper().readTree(URL("$baseURL?action=get_historical_liquidity&pair=$pairContractAddress" +
                "&from=$from&to=$to&chainID=${chain.chainId}").get()).getObjectOfList(2)
    }

    fun getHistoricalVolume(pairContractAddress: String, from: Long, to: Long = System.currentTimeMillis(), chain: SushiChain = SushiChain.ETHEREUM)
    : List<SushiSwapHistoricalVolume> {
        return jacksonObjectMapper().readTree(URL("$baseURL?action=get_historical_volume&pair=$pairContractAddress" +
                "&from=$from&to=$to&chainID=${chain.chainId}").get()).getObjectOfList(2)
    }

    fun getHistoricalTransactionCount(pairContractAddress: String, from: Long, to: Long = System.currentTimeMillis(), chain: SushiChain = SushiChain.ETHEREUM)
            : List<SushiSwapHistoricalTransactionCount> {
        return jacksonObjectMapper().readTree(URL("$baseURL?action=get_historical_transactions_count&pair=$pairContractAddress" +
                "&from=$from&to=$to&chainID=${chain.chainId}").get()).getObjectOfList(2)
    }

    fun getSushiAPY(from: Long, to: Long = System.currentTimeMillis()): List<SushiSwapAPY> {
        return jacksonObjectMapper().readTree(URL("$baseURL?action=get_xsushi_apy&from=$from&to=$to").get()).getObjectOfList(2)
    }

    fun getSushiRewards(address: String): List<SushiSwapUserReward> {
        return jacksonObjectMapper().readTree(URL("$baseURL?action=get_xsushi_rewards_by_user&address=$address").get()).getObjectOfList(2)
    }

    fun getTransactionsByPair(pairContractAddress: String, direction: String = "DESC", page: Int = 0, chain: SushiChain = SushiChain.ETHEREUM)
            : List<SushiSwapPairTransaction> {
        return jacksonObjectMapper().readTree(URL("$baseURL?action=get_transactions_by_pair&pair=$pairContractAddress&direction=$direction" +
                "&page=$page&chainID=${chain.chainId}").get()).getObjectOfList(2)
    }

    fun getTransactionsByUser(address: String, direction: String = "DESC", page: Int = 0, chain: SushiChain = SushiChain.ETHEREUM)
            : List<SushiSwapUserTransaction> {
        return jacksonObjectMapper().readTree(URL("$baseURL?action=get_transactions_by_user&address=$address&direction=$direction" +
                "&page=$page&chainID=${chain.chainId}").get()).getObjectOfList(2)
    }

    fun getBentoInfo(): List<SushiSwapBentoInfo> {
        return jacksonObjectMapper().readTree(URL("$baseURL?action=get_bento_info").get()).getObjectOfList(2)
    }

    fun getBentoTransactionByAddress(address: String, direction: String = "DESC", page: Int = 0, chain: SushiChain): List<SushiSwapBentoAddressTransaction> {
        return jacksonObjectMapper().readTree(URL("$baseURL?action=get_bento_transactions_by_address&address=$address&direction=$direction" +
                "&page=$page&chainID=${chain.chainId}").get()).getObjectOfList(2)
    }

    fun getBentoTransactionByToken(tokenContractAddress: String, direction: String = "DESC", page: Int = 0, chain: SushiChain)
    : List<SushiSwapBentoAddressTransaction> {
        return jacksonObjectMapper().readTree(URL("$baseURL?action=get_bento_transactions_by_token&token=$tokenContractAddress&direction=$direction" +
                "&page=$page&chainID=${chain.chainId}").get()).getObjectOfList(2)
    }
}