package dev.crash.tx.signed

import dev.crash.BytePacket
import dev.crash.address.Address
import dev.crash.address.keccak256
import dev.crash.toHexString
import dev.crash.tx.ETHNetwork
import dev.crash.tx.ethSignWithECDSAsecp256k1
import dev.crash.tx.raw.RawEthTransaction

class SignedEthTransaction internal constructor(rawTx: RawEthTransaction) {

    val rawTxBytes: ByteArray = rawTx.bytes
    val from: Address = rawTx.from
    val to: String = rawTx.to.removePrefix("0x")
    val amount: Long = rawTx.amount
    val ethNetwork: ETHNetwork = rawTx.ethNetwork

    init {
        val rsList = rawTxBytes.keccak256().ethSignWithECDSAsecp256k1(from.privateKey)
        val r = rsList[0]
        val s = rsList[1]
        val v = r.mod(2.toBigInteger()).toInt() + ethNetwork.chainId*2 + 35
        var rBytes = r.toByteArray()
        var sBytes = s.toByteArray()
        while(rBytes.size > 32){
            rBytes = rBytes.drop(1).toByteArray()
        }
        while(sBytes.size > 32){
            sBytes = sBytes.drop(1).toByteArray()
        }
        val packet = BytePacket()
        packet.write(rawTxBytes)
        packet.write(v.toByte())
        packet.writeArrayWithSize(rBytes)
        packet.writeArrayWithSize(sBytes)
        val bytes = packet.getByteArray()
        val finalPacket = BytePacket()
        finalPacket.write(248.toByte())
        finalPacket.write(bytes.size.toByte())
        finalPacket.write(bytes)
        println(finalPacket.getByteArray().toHexString())
    }

    //f86780862d79883d200082520894f4c18bad04610fdbb7f1303f1979e78f5c062466827a69802ba0848629b22d0ee22d835c8a51eba26f3891bfa83ab2a85fda506181272c8c1086a0330b0bca6b9220c5d5b5202f44d0a032a7c94cb6292000e1de58cd15d29692ba

    fun submit(): Boolean {
        return true
    }
}