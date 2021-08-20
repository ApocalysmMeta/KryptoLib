package dev.crash.tx.signed

import dev.crash.*
import dev.crash.address.getUncompressedPublicKey
import dev.crash.address.sha256
import dev.crash.asHexByteArray
import dev.crash.tx.raw.RawBtcTransaction
import dev.crash.tx.signWithECDSAsecp256k1

class SignedBtcTransaction internal constructor(private val rawTx: RawBtcTransaction): SignedTransaction() {

    init {
        //Script Sig
        val doubleSha256 = rawTx.bytes.sha256().sha256()
        val signed = doubleSha256.signWithECDSAsecp256k1(rawTx.from.privateKey)
        val scriptSig = BytePacket()
        scriptSig.writeAsCompactUInt(signed.size+1)
        scriptSig.write(signed)
        scriptSig.write(0x01.toByte())
        val uncompressedPublicKey = getUncompressedPublicKey(rawTx.from.privateKey)
        scriptSig.writeAsCompactUInt(uncompressedPublicKey.size)
        scriptSig.write(uncompressedPublicKey)
        val scriptSigBytes = scriptSig.getByteArray()

        //Actual Transaction
        val packet = BytePacket()
        packet.write(ByteSwapper.swap(rawTx.from.type.versionID)) // Version
        //Inputs
        packet.writeAsCompactUInt(rawTx.inputs.size)
        rawTx.inputs.forEach {
            packet.write(ByteSwapper.swap(it.txHash.asHexByteArray()))
            packet.write(ByteSwapper.swap(it.vout))
            packet.writeAsCompactUInt(scriptSigBytes.size)
            packet.write(scriptSigBytes)
            packet.write("ffffffff".asHexByteArray())
        }
        //End of Inputs
        //Outputs
        packet.writeAsCompactUInt(rawTx.outputs.size)
        rawTx.outputs.forEach {
            packet.write(ByteSwapper.swap(it.value))
            packet.writeAsCompactUInt(it.scriptPubKey.size)
            packet.write(it.scriptPubKey)
        }
        //End of Outputs
        packet.write(ByteSwapper.swap(rawTx.lockTime)) // Locktime
        val bytes = packet.getByteArray()
        println(bytes.toHexString())
    }

    fun submit(): Boolean {
        return true
    }
}