package dev.crash.etherscan

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import dev.crash.get
import java.math.BigDecimal
import java.net.URL

class Bscscan(private val API_KEY: String) : EtherscanParent(baseURL, API_KEY) {
    fun getBEP20TokenTransfersByAddress(address: String, startBlock: Int = 0, endBlock: Int = 99999999, sort: String = "asc"): List<EtherscanTokenTransfer> {
        val response = URL("$baseURL?module=account&action=tokentx&address=$address&startblock=$startBlock&endblock=$endBlock&sort=$sort&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<List<EtherscanTokenTransfer>>>(response).result
    }

    fun getBEP20TokenTransfersByContract(contractAddress: String, startBlock: Int = 0, endBlock: Int = 99999999, sort: String = "asc"): List<EtherscanTokenTransfer> {
        val response = URL("$baseURL?module=account&action=tokentx&contractaddress=$contractAddress&startblock=$startBlock&endblock=$endBlock&sort=$sort&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<List<EtherscanTokenTransfer>>>(response).result
    }

    fun getBEP20TokenTransfers(address: String, contractAddress: String, startBlock: Int = 0, endBlock: Int = 99999999, sort: String = "asc"): List<EtherscanTokenTransfer> {
        val response = URL("$baseURL?module=account&action=tokentx&contractaddress=$contractAddress&address=$address&startblock=$startBlock&endblock=$endBlock&sort=$sort&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<List<EtherscanTokenTransfer>>>(response).result
    }

    fun getBlocksValidated(address: String, blockType: String = "blocks"): List<EtherscanBlock> {
        val response = URL("$baseURL?module=account&action=getminedblocks&address=$address&blocktype=$blockType&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<List<EtherscanBlock>>>(response).result
    }

    fun getBEP20TokenTotalSupply(contractAddress: String): Long {
        val response = URL("$baseURL?module=stats&action=getblocknobytime&contractaddress=$contractAddress&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<Long>>(response).result
    }

    fun getBEP20TokenBalance(address: String, contractAddress: String): Long {
        val response = URL("$baseURL?module=account&action=tokenbalance&contractaddress=$contractAddress&address=$address&tag=latest&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<Long>>(response).result
    }

    fun getTotalBNBSupply(): BigDecimal {
        val response = URL("$baseURL?module=stats&action=bnbsupply&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<String>>(response).result.toBigDecimal() / 1000000000000000000.toBigDecimal()
    }

    fun getLatestBNBPrice(): EtherscanPrice {
        val response = URL("$baseURL?module=stats&action=bnbprice&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<EtherscanPrice>>(response).result
    }

    fun getValidators(): List<BscscanValidator> {
        val response = URL("$baseURL?module=stats&action=validators&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<List<BscscanValidator>>>(response).result
    }

    companion object {
        private const val baseURL = "https://api.bscscan.com/api"
    }
}