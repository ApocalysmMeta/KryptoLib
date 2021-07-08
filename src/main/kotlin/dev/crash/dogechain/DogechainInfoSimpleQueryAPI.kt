package dev.crash.dogechain

import dev.crash.get
import java.net.URL

object DogechainInfoSimpleQueryAPI {
    private const val baseUrl = "https://dogechain.info/chain/Dogecoin/q/"

    data class DecodedAddress(val versionPrefix: String, val hashEncoded: String)

    fun getAddressBalance(address: String): Double = URL("$baseUrl/addressbalance/$address").get().toDouble()

    fun addressToHash(address: String): String = URL("$baseUrl/addresstohash/$address").get()

    fun checkAddress(address: String): Boolean = URL("$baseUrl/checkaddress/$address").get() == "1E"

    fun decodeAddress(address: String): DecodedAddress {
        val response = URL("$baseUrl/decode_address/$address").get().split(":")
        return DecodedAddress(response[0], response[1])
    }

    fun getBlockCount(): Int = URL("$baseUrl/getblockcount").get().toInt()

    fun getDifficulty(): Double = URL("$baseUrl/getdifficulty").get().toDouble()

    fun getReceivedByAddress(address: String): Double = URL("$baseUrl/getreceivedbyaddress/$address").get().toDouble()

    fun getSentByAddress(address: String): Double = URL("$baseUrl/getsentbyaddress/$address").get().toDouble()

    fun getTotalBc(): Double = URL("$baseUrl/totalbc").get().toDouble()
}