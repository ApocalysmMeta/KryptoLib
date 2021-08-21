package dev.crash.tx.signed

import dev.crash.*
import dev.crash.address.Address
import dev.crash.address.getUncompressedPublicKey
import dev.crash.address.sha256
import dev.crash.asHexByteArray
import dev.crash.tx.BTCTxInput
import dev.crash.tx.BTCTxOutput
import dev.crash.tx.raw.RawBtcTransaction
import dev.crash.tx.signWithECDSAsecp256k1

class SignedBtcTransaction internal constructor(rawTx: RawBtcTransaction) {

    val inputs: List<BTCTxInput> = rawTx.inputs
    val outputs: List<BTCTxOutput> = rawTx.outputs
    val from: Address = rawTx.from
    val targets: HashMap<String, Long> = rawTx.targets
    val lockTime: Int = rawTx.lockTime
    val rawTxBytes: ByteArray = rawTx.bytes
    val bytes: ByteArray
    val hex: String

    init {
        //Script Sig
        val signed = rawTxBytes.sha256().sha256().signWithECDSAsecp256k1(from.privateKey)
        val scriptSig = BytePacket()
        scriptSig.writeAsCompactUInt(signed.size+1)
        scriptSig.write(signed)
        scriptSig.write(0x01.toByte())
        val uncompressedPublicKey = getUncompressedPublicKey(from.privateKey)
        scriptSig.writeAsCompactUInt(uncompressedPublicKey.size)
        scriptSig.write(uncompressedPublicKey)
        val scriptSigBytes = scriptSig.getByteArray()

        //Actual Transaction
        val packet = BytePacket()
        packet.write(ByteSwapper.swap(from.type.versionID)) // Version
        //Inputs
        packet.writeAsCompactUInt(inputs.size)
        inputs.forEach {
            packet.write(ByteSwapper.swap(it.txHash.asHexByteArray()))
            packet.write(ByteSwapper.swap(it.vout))
            packet.writeAsCompactUInt(scriptSigBytes.size)
            packet.write(scriptSigBytes)
            packet.write("ffffffff".asHexByteArray())
        }
        //End of Inputs
        //Outputs
        packet.writeAsCompactUInt(outputs.size)
        outputs.forEach {
            packet.write(ByteSwapper.swap(it.value))
            packet.writeAsCompactUInt(it.scriptPubKey.size)
            packet.write(it.scriptPubKey)
        }
        //End of Outputs
        packet.write(ByteSwapper.swap(lockTime)) // Locktime
        bytes = packet.getByteArray()
        hex = bytes.toHexString()
        println(hex)
    }

    fun submit(): Boolean {
        return true
    }

    override fun toString(): String {
        return hex
    }
}