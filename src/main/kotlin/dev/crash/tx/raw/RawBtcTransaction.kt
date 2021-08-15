package dev.crash.tx.raw

import dev.crash.BytePacket
import dev.crash.ByteSwapper
import dev.crash.address.Address
import dev.crash.address.getHashedPublicKey
import dev.crash.address.getUncompressedPublicKey
import dev.crash.asHexByteArray
import dev.crash.toHexString
import dev.crash.tx.signed.SignedTransaction
import dev.crash.tx.buildP2PKHScriptPupKey
import dev.crash.tx.signed.SignedBtcTransaction


class RawBtcTransaction(val from: Address, val to: String, val amount: Long) : RawTransaction(from.type) {
    constructor(from: Address, to: String, amount: Int): this(from, to, amount.toLong())

    init {
        // According to https://learnmeabitcoin.com/technical/transaction-data
        val packet = BytePacket()
        packet.write(ByteSwapper.swap(1)) // Version

        //Input
        packet.writeAsVarInt(1)
        packet.write(ByteSwapper.swap("fe0196ddd86d6d3cb3ca75b471271e1126c8616c5374e51fe2291752773bb03a".asHexByteArray()))
        packet.write(ByteSwapper.swap(1))
        val scriptSig = mutableListOf<Byte>()
        //TODO: Add signature
        scriptSig.addAll(getUncompressedPublicKey(from.privateKey).asList())
        packet.writeAsVarInt(scriptSig.size)
        packet.write(scriptSig.toByteArray())
        packet.write(ByteSwapper.swap(0xffffffff.toInt()))

        //Output
        packet.writeAsVarInt(1)
        packet.write(ByteSwapper.swap(amount))
        val scriptPubKey = buildP2PKHScriptPupKey(getHashedPublicKey(to))
        packet.writeAsVarInt(scriptPubKey.size)
        packet.write(scriptPubKey)

        packet.write(ByteSwapper.swap(0)) // Locktime
        println(packet.getByteArray().toHexString())
    }

    override fun sign(): SignedBtcTransaction = SignedBtcTransaction(this)
}