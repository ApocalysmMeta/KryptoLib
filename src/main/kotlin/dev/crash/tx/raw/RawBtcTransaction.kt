package dev.crash.tx.raw

import dev.crash.BytePacket
import dev.crash.ByteSwapper
import dev.crash.address.Address
import dev.crash.address.getHashedPublicKey
import dev.crash.crypto.asHexByteArray
import dev.crash.crypto.toHexString
import dev.crash.tx.BTCHashCodeType
import dev.crash.tx.BTCTxInput
import dev.crash.tx.BTCTxOutput
import dev.crash.tx.buildP2PKHScriptPupKey
import dev.crash.tx.signed.SignedBtcTransaction


class RawBtcTransaction(val from: Address, val targets: HashMap<String, Long>, val lockTime: Int = 0) {

    val inputs: List<BTCTxInput>
    val outputs: List<BTCTxOutput>
    val bytes: ByteArray

    init {
        val inputs = mutableListOf<BTCTxInput>()
        //TODO: Get UTXO
        inputs.add(BTCTxInput("fe0196ddd86d6d3cb3ca75b471271e1126c8616c5374e51fe2291752773bb03a", 1, "76a91478757c3f1b4c3e5719fe573a86ff86c0fa9a1dfa88ac"))
        this.inputs = inputs.toList()

        val outputs = mutableListOf<BTCTxOutput>()
        targets.forEach {
            outputs.add(BTCTxOutput(it.value, buildP2PKHScriptPupKey(getHashedPublicKey(it.key))))
        }
        this.outputs = outputs

        val prePacket = BytePacket()
        prePacket.write(ByteSwapper.swap(1)) // Version
        //Inputs
        prePacket.writeAsCompactUInt(inputs.size)
        inputs.forEach {
            prePacket.write(ByteSwapper.swap(it.txHash.asHexByteArray()))
            prePacket.write(ByteSwapper.swap(it.vout))
            val scriptBytes = it.script.asHexByteArray()
            prePacket.writeAsCompactUInt(scriptBytes.size)
            prePacket.write(scriptBytes)
            prePacket.write("ffffffff".asHexByteArray())
        }
        //End of Inputs
        //Outputs
        prePacket.writeAsCompactUInt(outputs.size)
        outputs.forEach {
            prePacket.write(ByteSwapper.swap(it.value))
            prePacket.writeAsCompactUInt(it.scriptPubKey.size)
            prePacket.write(it.scriptPubKey)
        }
        //End of Outputs
        prePacket.write(ByteSwapper.swap(lockTime)) // Locktime
        prePacket.write(ByteSwapper.swap(BTCHashCodeType.SIGHASH_ALL.code))
        bytes = prePacket.getByteArray()
        println(bytes.toHexString())
    }

    fun sign(): SignedBtcTransaction = SignedBtcTransaction(this)
}