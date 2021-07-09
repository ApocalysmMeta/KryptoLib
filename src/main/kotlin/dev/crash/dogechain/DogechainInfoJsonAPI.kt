package dev.crash.dogechain

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import dev.crash.get
import java.math.BigDecimal
import java.net.URL

object DogechainInfoJsonAPI {
    private const val baseUrl = "https://dogechain.info/api/v1"

    fun getAddressBalance(address: String): BigDecimal {
        val response = URL("$baseUrl/address/balance/$address").get()
        return jacksonObjectMapper().readTree(response)["balance"].decimalValue()
    }

    fun getAddressAmountReceived(address: String): BigDecimal {
        val response = URL("$baseUrl/address/received/$address").get()
        return jacksonObjectMapper().readTree(response)["received"].decimalValue()
    }

    fun getAddressAmountSent(address: String): BigDecimal {
        val response = URL("$baseUrl/address/sent/$address").get()
        return jacksonObjectMapper().readTree(response)["sent"].decimalValue()
    }

    fun getAddressUnspentOutputs(address: String): List<DogechainInfoUnspentOutput> {
        val response = URL("$baseUrl/unspent/$address").get()
        return jacksonObjectMapper().readValue(jacksonObjectMapper().readTree(response)["unspent_outputs"].traverse())
    }

    fun getTransaction(address: String): DogechainInfoTransaction {
        val response = URL("$baseUrl/transaction/$address").get()
        return jacksonObjectMapper().readValue(jacksonObjectMapper().readTree(response)["transaction"].traverse())
    }

    fun getBlock(hash: String): DogechainInfoBlock {
        val response = URL("$baseUrl/block/$hash").get()
        return jacksonObjectMapper().readValue(jacksonObjectMapper().readTree(response)["block"].traverse())
    }

    fun getBlock(blockNumber: Int): DogechainInfoBlock = getBlock(blockNumber.toString())
}