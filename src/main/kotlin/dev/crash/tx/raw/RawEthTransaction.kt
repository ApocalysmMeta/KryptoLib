package dev.crash.tx.raw

import dev.crash.*
import dev.crash.address.Address
import dev.crash.crypto.asHexByteArray
import dev.crash.crypto.toHexString
import dev.crash.eth.ETHNetwork
import dev.crash.tx.signed.SignedEthTransaction

class RawEthTransaction(val from: Address, val to: String, val amount: Long, val chainId: Int) {
    constructor(from: Address, to: String, amount: Long, ethNetwork: ETHNetwork) : this(from, to, amount, ethNetwork.chainId)

    val bytes: ByteArray
    val hex: String

    init {
        val packet = BytePacket()
        packet.writeETHByte(0) //Nonce

        val gasPriceBytes = 4000000032.toString(16).asHexByteArray() //Gas Price
        packet.writeETHByte(gasPriceBytes.size.toByte())
        packet.write(gasPriceBytes)

        val gasLimitBytes = 31500.toString(16).asHexByteArray() //Gas limit
        packet.writeETHByte(gasLimitBytes.size.toByte())
        packet.write(gasLimitBytes)

        packet.writeArrayWithSize(to.removePrefix("0x").asHexByteArray()) //To address

        val amountBytes = amount.toString(16).asHexByteArray() //Value
        packet.writeETHByte(amountBytes.size.toByte())
        packet.write(amountBytes)

        packet.writeETHByte(0) //Data

        bytes = packet.getByteArray()
        hex = bytes.toHexString()
    }

    fun sign(): SignedEthTransaction = SignedEthTransaction(this)
}