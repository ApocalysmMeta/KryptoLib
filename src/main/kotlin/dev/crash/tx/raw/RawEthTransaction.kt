package dev.crash.tx.raw

import dev.crash.BytePacket
import dev.crash.address.Address
import dev.crash.tx.signed.SignedEthTransaction
import dev.crash.tx.signed.SignedTransaction

class RawEthTransaction(val from: Address, val to: String, val amount: Long) : RawTransaction(from.type) {
    constructor(from: Address, to: String, amount: Int): this(from, to, amount.toLong())

    init {
        val packet = BytePacket()
    }

    override fun sign(): SignedTransaction = SignedEthTransaction(this)
}