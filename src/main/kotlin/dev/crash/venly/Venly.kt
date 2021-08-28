package dev.crash.venly

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import dev.crash.get
import dev.crash.post
import dev.crash.put
import java.net.URL

class Venly constructor(private val applicationId: String, private val client_secret: String) {
    private var bearerToken: String = ""
    private var refreshToken: String = ""
    private var sessionState: String = ""

    init {
        authenticate()
    }

    fun createContract(name: String, description: String, chain: VenlyChain, symbol: String, imageUrl: String, externalUrl: String, media: List<VenlyMediaObj> = listOf()):
            VenlyNFTContractInfo = createContract(VenlyCreateContractBody(name, description, chain.name, symbol, imageUrl, externalUrl, media))

    fun createContract(requestBody: VenlyCreateContractBody): VenlyNFTContractInfo {
        return jacksonObjectMapper().readValue(URL("https://api-business.arkane.network/api/apps/$applicationId/contracts")
            .post(jacksonObjectMapper().writeValueAsString(requestBody)))
    }

    fun getContract(contractId: Int): VenlyNFTContractInfo {
        return jacksonObjectMapper().readValue(URL("https://api-business.arkane.network/api/apps/$applicationId/contracts/$contractId").get())
    }

    fun getAllContracts(): List<VenlyNFTContractInfo> {
        return jacksonObjectMapper().readValue(URL("http://api-business.arkane.network/api/apps/$applicationId/contracts").get())
    }

    fun createNFTTemplate(contractId: Int, name: String, description: String, imageUrl: String, externalUrl: String, maxSupply: Long,
                          backgroundColor: String = "#FFFFFF", fungible: Boolean = false, burnable: Boolean = false,
                          animationURLs: List<VenlyMediaObj> = listOf(), attributes: List<VenlyNFTAttribute> = listOf()): VenlyNFTTemplate
    = createNFTTemplate(VenlyCreateNFTTemplateBody(name, description, imageUrl, externalUrl, backgroundColor, fungible, maxSupply, burnable, animationURLs, attributes), contractId)

    fun createNFTTemplate(requestBody: VenlyCreateNFTTemplateBody, contractId: Int): VenlyNFTTemplate {
        return jacksonObjectMapper().readValue(URL("https://api-business.arkane.network/api/apps/$applicationId/contracts/$contractId/token-types")
            .post(jacksonObjectMapper().writeValueAsString(requestBody)))
    }

    fun getNFTTemplate(contractId: Int, tokenTypeId: Int): VenlyNFTTemplate {
        return jacksonObjectMapper().readValue(URL("https://api-business.arkane.network/api/apps/$applicationId/contracts/$contractId/token-types/$tokenTypeId").get())
    }

    fun getAllNFTTemplates(contractId: Int): VenlyNFTTemplate {
        return jacksonObjectMapper().readValue(URL("https://api-business.arkane.network/api/apps/$applicationId/contracts/$contractId/token-types").get())
    }

    fun getNFTMetadata(contractId: Int, tokenTypeId: Int): VenlyNFTMetadata {
        return jacksonObjectMapper().readValue(URL("https://api-business.arkane.network/api/apps/$applicationId/contracts/$contractId/token-types/$tokenTypeId/metadata").get())
    }

    fun updateNFTMetadata(contractId: Int, tokenTypeId: Int, metadata: VenlyUpdateNFTMetadataBody): VenlyNFTMetadata {
        return jacksonObjectMapper().readValue(URL("https://api-business.arkane.network/api/apps/$applicationId/contracts/$contractId/token-types/$tokenTypeId/metadata")
            .put(jacksonObjectMapper().writeValueAsString(metadata)))
    }

    fun mintNFT(contractId: Int, requestBody: VenlyNFTMintBody): List<VenlyNFTMint> {
        return jacksonObjectMapper().readValue(URL("https://api-business.arkane.network/api/apps/$applicationId/contracts/$contractId/tokens/non-fungible/")
            .post(jacksonObjectMapper().writeValueAsString(requestBody)))
    }

    fun mintNFT(contractId: Int, tokenTypeId: Int, destinations: List<String>): List<VenlyNFTMint> = mintNFT(contractId, VenlyNFTMintBody(tokenTypeId, destinations))

    fun mintFungibleNFT(contractId: Int, tokenTypeId: Int, requestBody: VenlyNFTMintFungibleBody): List<VenlyNFTMint> {
        return jacksonObjectMapper().readValue(URL("https://api-business.arkane.network/api/apps/$applicationId/contracts/$contractId/tokens/fungible/$tokenTypeId")
            .post(jacksonObjectMapper().writeValueAsString(requestBody)))
    }

    fun mintFungibleNFT(contractId: Int, tokenTypeId: Int, targets: HashMap<String, Long>): List<VenlyNFTMint>
    = mintFungibleNFT(contractId, tokenTypeId, VenlyNFTMintFungibleBody(targets.keys.toList(), targets.values.toList()))

    fun getNFTsByTemplate(contractId: Int, tokenTypeId: Int): List<VenlyNFTByTemplate> {
        return jacksonObjectMapper().readValue(URL("https://api-business.arkane.network/api/apps/$applicationId/contracts/$contractId/token-types/$tokenTypeId/tokens").get())
    }

    fun getNFTsByAddress(chain: VenlyChain, address: String): List<VenlyNFTBalance> {
        return jacksonObjectMapper().readValue(URL("https://${chain.name.lowercase()}-azrael.arkane.network/$address/tokens").get())
    }

    fun getNFTContract(chain: VenlyChain, contractAddress: String): VenlyTokenContractInfo {
        return jacksonObjectMapper().readValue(URL("https://${chain.name.lowercase()}-azrael.arkane.network/contracts/$contractAddress").get())
    }

    fun getNFTInfo(chain: VenlyChain, contractAddress: String, tokenId: Int): VenlyNFTInfo {
        return jacksonObjectMapper().readValue(URL("https://${chain.name.lowercase()}-azrael.arkane.network/contracts/$contractAddress/tokens/$tokenId"))
    }

    fun authenticate(){
        val response = URL("https://login-staging.arkane.network/auth/realms/Arkane/protocol/openid-connect/token").post(
            hashMapOf("grant_type" to "client_credentials", "client_id" to "Testaccount-capsule", "client_secret" to client_secret), hashMapOf(), false)
        val obj = jacksonObjectMapper().readTree(response)
        bearerToken = obj["access_token"].asText()
        refreshToken = obj["refresh_token"].asText()
        sessionState = obj["session_state"].asText()
    }

    private fun URL.post(requestString: String): String {
        authenticate()
        return post(requestString, hashMapOf("Authorization" to "Bearer $bearerToken"))
    }

    private fun URL.put(requestString: String): String {
        authenticate()
        return put(requestString, hashMapOf("Authorization" to "Bearer $bearerToken"))
    }

    private fun URL.get(): String {
        authenticate()
        return get(hashMapOf("Authorization" to "Bearer $bearerToken"))
    }
}