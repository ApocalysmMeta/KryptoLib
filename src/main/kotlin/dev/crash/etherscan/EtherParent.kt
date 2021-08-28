package dev.crash.etherscan

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import dev.crash.get
import java.math.BigDecimal
import java.net.URL

abstract class EtherParent(private val baseURL: String, private val API_KEY: String): EtherscanParent(baseURL, API_KEY) {
    fun getERC20TokenTransfersByAddress(address: String, startBlock: Int = 0, endBlock: Int = 99999999, sort: String = "asc"): List<EtherscanTokenTransfer> {
        val response = URL("${baseURL}?module=account&action=tokentx&address=$address&startblock=$startBlock&endblock=$endBlock&sort=$sort&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<List<EtherscanTokenTransfer>>>(response).result
    }

    fun getERC20TokenTransfersByContract(contractAddress: String, startBlock: Int = 0, endBlock: Int = 99999999, sort: String = "asc"): List<EtherscanTokenTransfer> {
        val response = URL("${baseURL}?module=account&action=tokentx&contractaddress=$contractAddress&startblock=$startBlock&endblock=$endBlock&sort=$sort&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<List<EtherscanTokenTransfer>>>(response).result
    }

    fun getERC20TokenTransfers(address: String, contractAddress: String, startBlock: Int = 0, endBlock: Int = 99999999, sort: String = "asc"): List<EtherscanTokenTransfer> {
        val response = URL("${baseURL}?module=account&action=tokentx&contractaddress=$contractAddress&address=$address&startblock=$startBlock&endblock=$endBlock&sort=$sort&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<List<EtherscanTokenTransfer>>>(response).result
    }

    fun getERC20TokenTotalSupply(contractAddress: String): Long {
        val response = URL("${baseURL}?module=stats&action=getblocknobytime&contractaddress=$contractAddress&apikey=${API_KEY}").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<Long>>(response).result
    }

    fun getERC20TokenBalance(address: String, contractAddress: String): BigDecimal {
        val response = URL("${baseURL}?module=account&action=tokenbalance&contractaddress=$contractAddress&address=$address&tag=latest&apikey=${API_KEY}").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<BigDecimal>>(response).result
    }

    fun getGasOracle(): EtherscanGasOracle {
        val response = URL("${baseURL}?module=gastracker&action=gasoracle&apikey=${API_KEY}").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<EtherscanGasOracle>>(response).result
    }

    fun getTotalEtherSupply(): BigDecimal {
        val response = URL("${baseURL}?module=stats&action=ethsupply&apikey=${API_KEY}").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<String>>(response).result.toBigDecimal() / 1000000000000000000.toBigDecimal()
    }

    fun getLatestEtherPrice(): EtherscanPrice {
        val response = URL("${baseURL}?module=stats&action=ethprice&apikey=${API_KEY}").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<EtherscanPrice>>(response).result
    }

    fun getEthereumNodeSize(startDate: String, endDate: String, clientType: String = "geth", syncMode: String = "default"): List<EtherscanNodeSize> {
        val response = URL("${baseURL}?module=stats&action=chainsize&startdate=$startDate&enddate=$endDate&clienttype=$clientType&syncMode=$syncMode&apikey=${API_KEY}").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<List<EtherscanNodeSize>>>(response).result
    }
}