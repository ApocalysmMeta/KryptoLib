package dev.crash.venly

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import dev.crash.post
import java.net.URL

class VenlyAPI constructor(val applicationId: String) {

    fun createContract(name: String, description: String, chain: VenlyChain, symbol: String, imageUrl: String, externalUrl: String, media: List<VenlyMediaObj>):
            VenlyContractInfo = createContract(VenlyCreateContractBody(name, description, chain.name, symbol, imageUrl, externalUrl, media))

    fun createContract(requestBody: VenlyCreateContractBody): VenlyContractInfo {
        return jacksonObjectMapper().readValue(URL("https://api-business.arkane.network/api/apps/$applicationId/contracts")
            .post(jacksonObjectMapper().writeValueAsString(requestBody), true))
    }

    fun retrieveContract(contractId: Int): VenlyContractInfo {
        return jacksonObjectMapper().readValue(URL("https://api-business.arkane.network/api/apps/$applicationId/contracts/$contractId"))
    }

    fun retrieveAllContracts(): List<VenlyContractInfo> {
        return jacksonObjectMapper().readValue(URL("https://api-business.arkane.network/api/apps/$applicationId/contracts"))
    }
}