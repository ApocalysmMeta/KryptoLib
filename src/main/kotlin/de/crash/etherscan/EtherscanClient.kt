package de.crash.etherscan

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import de.crash.get
import de.crash.joinToNoSpaceString
import java.net.URL

class EtherscanClient(private val API_KEY: String) {
    private val baseURL = "https://api.etherscan.io/api"

    fun accountBalance(address: String): Long {
        val response = URL("$baseURL?module=account&action=balance&address=$address&tag=latest&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<Long>>(response).result
    }

    fun accountBalances(addresses: List<String>): HashMap<String, Long> {
        val response = URL("$baseURL?module=account&action=balancemulti&address=${addresses.joinToNoSpaceString()}&tag=latest&apikey=$API_KEY").get()
        val result = hashMapOf<String, Long>()
        jacksonObjectMapper().readValue<EtherscanResponse<List<EtherscanAddressBalance>>>(response).result.forEach {
            result[it.account] = it.balance.toLong()
        }
        return result
    }

    fun getNormalTransactions(address: String, startBlock: Int = 0, endBlock: Int = 99999999, sort: String = "asc"): List<EtherscanTransaction> {
        val response = URL("$baseURL?module=account&action=txlist&address=$address&startblock=$startBlock&endblock=$endBlock&sort=$sort&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<List<EtherscanTransaction>>>(response).result
    }

    fun getInternalTransactions(address: String, startBlock: Int = 0, endBlock: Int = 99999999, sort: String = "asc"): List<EtherscanInternalTransaction> {
        val response = URL("$baseURL?module=account&action=txlistinternal&address=$address&startblock=$startBlock&endblock=$endBlock&sort=$sort&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<List<EtherscanInternalTransaction>>>(response).result
    }

    fun getInternalTransactionsByTxHash(txHash: String): List<EtherscanInternalTransaction> {
        val response = URL("$baseURL?module=account&action=txlistinternal&txhash=$txHash&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<List<EtherscanInternalTransaction>>>(response).result
    }

    fun getInternalTransactionsByBlockRange(startBlock: Int, endBlock: Int, sort: String = "asc"): List<EtherscanInternalTransaction> {
        val response = URL("$baseURL?module=account&action=txlistinternal&startblock=$startBlock&endblock=$endBlock&sort=$sort&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<List<EtherscanInternalTransaction>>>(response).result
    }

    fun getERC20TokenTransfersByAddress(address: String, startBlock: Int = 0, endBlock: Int = 99999999, sort: String = "asc"): List<EtherscanTokenTransfer> {
        val response = URL("$baseURL?module=account&action=tokentx&address=$address&startblock=$startBlock&endblock=$endBlock&sort=$sort&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<List<EtherscanTokenTransfer>>>(response).result
    }

    fun getERC20TokenTransfersByContract(contractAddress: String, startBlock: Int = 0, endBlock: Int = 99999999, sort: String = "asc"): List<EtherscanTokenTransfer> {
        val response = URL("$baseURL?module=account&action=tokentx&contractaddress=$contractAddress&startblock=$startBlock&endblock=$endBlock&sort=$sort&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<List<EtherscanTokenTransfer>>>(response).result
    }

    fun getERC20TokenTransfers(address: String, contractAddress: String, startBlock: Int = 0, endBlock: Int = 99999999, sort: String = "asc"): List<EtherscanTokenTransfer> {
        val response = URL("$baseURL?module=account&action=tokentx&contractaddress=$contractAddress&address=$address&startblock=$startBlock&endblock=$endBlock&sort=$sort&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<List<EtherscanTokenTransfer>>>(response).result
    }

    fun getERC721TokenTransfersByAddress(address: String, startBlock: Int = 0, endBlock: Int = 99999999, sort: String = "asc"): List<EtherscanTokenTransfer> {
        val response = URL("$baseURL?module=account&action=tokennfttx&address=$address&startblock=$startBlock&endblock=$endBlock&sort=$sort&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<List<EtherscanTokenTransfer>>>(response).result
    }

    fun getERC721TokenTransfersByContract(contractAddress: String, startBlock: Int = 0, endBlock: Int = 99999999, sort: String = "asc"): List<EtherscanTokenTransfer> {
        val response = URL("$baseURL?module=account&action=tokennfttx&contractaddress=$contractAddress&startblock=$startBlock&endblock=$endBlock&sort=$sort&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<List<EtherscanTokenTransfer>>>(response).result
    }

    fun getERC721TokenTransfers(address: String, contractAddress: String, startBlock: Int = 0, endBlock: Int = 99999999, sort: String = "asc"): List<EtherscanTokenTransfer> {
        val response = URL("$baseURL?module=account&action=tokennfttx&contractaddress=$contractAddress&address=$address&startblock=$startBlock&endblock=$endBlock&sort=$sort&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<List<EtherscanTokenTransfer>>>(response).result
    }

    fun getBlocksMined(address: String, blockType: String = "blocks"): List<EtherscanBlock> {
        val response = URL("$baseURL?module=account&action=getminedblocks&address=$address&blocktype=$blockType&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<List<EtherscanBlock>>>(response).result
    }

    fun txReceiptStatus(txHash: String): Int {
        val response = URL("$baseURL?module=transaction&action=gettxreceiptstatus&txhash=$txHash&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<Int>>(response).result
    }

    fun getBlockReward(blockNumber: Int): EtherscanBlockReward {
        val response = URL("$baseURL?module=block&action=getblockreward&blockno=$blockNumber&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<EtherscanBlockReward>>(response).result
    }

    fun getBlockCountdown(blockNumber: Int): EtherscanBlockCountdown {
        val response = URL("$baseURL?module=block&action=getblockcountdown&blockno=$blockNumber&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<EtherscanBlockCountdown>>(response).result
    }

    fun getBlockByTimestamp(timeStamp: Long, closest: String = "before"): Int {
        val response = URL("$baseURL?module=block&action=getblocknobytime&timestamp=$timeStamp&closest=$closest&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<Int>>(response).result
    }

    fun getERC20TokenTotalSupply(contractAddress: String): Long {
        val response = URL("$baseURL?module=stats&action=getblocknobytime&contractaddress=$contractAddress&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<Long>>(response).result
    }

    fun getERC20TokenBalance(address: String, contractAddress: String): Long {
        val response = URL("$baseURL?module=account&action=tokenbalance&contractaddress=$contractAddress&address=$address&tag=latest&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<Long>>(response).result
    }

    fun getEstimatedConfirmationTime(gasPrice: Long): Long {
        val response = URL("$baseURL?module=gastracker&action=gasestimate&gasprice=$gasPrice&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<Long>>(response).result
    }

    fun getGasOracle(): EtherscanGasOracle {
        val response = URL("$baseURL?module=gastracker&action=gasoracle&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<EtherscanGasOracle>>(response).result
    }

    fun getTotalEtherSupply(): Long {
        val response = URL("$baseURL?module=stats&action=ethsupply&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<Long>>(response).result / 1000000000000000000L
    }

    fun getLatestEtherPrice(): EtherscanPrice {
        val response = URL("$baseURL?module=stats&action=ethprice&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<EtherscanPrice>>(response).result
    }

    fun getEthereumNodeSize(startDate: String, endDate: String, clientType: String = "geth", syncMode: String = "default"): List<EtherscanNodeSize> {
        val response = URL("$baseURL?module=stats&action=chainsize&startdate=$startDate&enddate=$endDate&clienttype=$clientType&syncMode=$syncMode&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<List<EtherscanNodeSize>>>(response).result
    }

    fun getTotalNodeCount(): EtherscanNodeCount {
        val response = URL("$baseURL?module=stats&action=nodecount&apikey=$API_KEY").get()
        return jacksonObjectMapper().readValue<EtherscanResponse<EtherscanNodeCount>>(response).result
    }
}