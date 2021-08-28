package dev.crash.tx.raw

import dev.crash.BytePacket
import dev.crash.address.Address
import dev.crash.asHexByteArray
import dev.crash.eth.ETHNetwork
import dev.crash.tx.signed.SignedEthTransaction

class RawEthTransaction(val from: Address, val to: String, val amount: Long, val chainId: Int) {
    constructor(from: Address, to: String, amount: Long, ethNetwork: ETHNetwork): this(from, to, amount, ethNetwork.chainId)

    val bytes: ByteArray

    init {
        val packet = BytePacket()
        packet.writeETHByte(0) //Nonce
        packet.writeArrayWithSize("09BCA5A000".asHexByteArray()) //Gas Price
        packet.writeETHNumber(21000) //Gas Limit
        packet.writeArrayWithSize(to.removePrefix("0x").asHexByteArray()) //To address
        packet.writeETHNumber(amount) //Value
        packet.writeETHByte(0) //Data
        bytes = packet.getByteArray()
    }

    fun sign(): SignedEthTransaction = SignedEthTransaction(this)
}

//41819676672
//41000000000