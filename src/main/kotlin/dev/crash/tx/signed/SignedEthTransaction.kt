package dev.crash.tx.signed

import dev.crash.BytePacket
import dev.crash.KryptoLib
import dev.crash.address.Address
import dev.crash.address.keccak256
import dev.crash.toHexString
import dev.crash.tx.ethSignWithECDSAsecp256k1
import dev.crash.tx.raw.RawEthTransaction
import java.math.BigInteger

class SignedEthTransaction internal constructor(rawTx: RawEthTransaction) {

    val rawTxBytes: ByteArray = rawTx.bytes
    val from: Address = rawTx.from
    val to: String = rawTx.to.removePrefix("0x")
    val amount: Long = rawTx.amount
    val chainId: Int = rawTx.chainId
    val hex: String

    init {
        val keccak = rawTxBytes.keccak256()
        val rsList = keccak.ethSignWithECDSAsecp256k1(from.privateKey)
        val r = rsList[0]
        val s = rsList[1]
        val recId = r.mod(BigInteger.TWO).toInt() + chainId*2 + 35
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
        packet.write(recId.toByte())
        packet.writeArrayWithSize(rBytes)
        packet.writeArrayWithSize(sBytes)
        val bytes = packet.getByteArray()
        val finalPacket = BytePacket()
        finalPacket.write(248.toByte())
        finalPacket.write(bytes.size.toByte())
        finalPacket.write(bytes)
        hex = finalPacket.getByteArray().toHexString()
        println(hex)
    }

    fun submit(): String {
        return if(KryptoLib.ethHandlers.containsKey(chainId)){
            KryptoLib.ethHandlers[chainId]!!.sendRawTransaction(hex)
        }else {
            throw UnsupportedOperationException("Can't send ether on network $chainId, did you forgot to register an ETHHandler?")
        }
    }
}