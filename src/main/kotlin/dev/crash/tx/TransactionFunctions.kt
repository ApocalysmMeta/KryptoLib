package dev.crash.tx

import org.bouncycastle.crypto.params.ECDomainParameters
import org.bouncycastle.crypto.params.ECPrivateKeyParameters
import org.bouncycastle.crypto.params.ParametersWithRandom
import org.bouncycastle.crypto.signers.ECDSASigner
import org.bouncycastle.jce.ECNamedCurveTable
import java.math.BigInteger

fun buildP2PKHScriptPupKey(pubKey: ByteArray): ByteArray {
    val list = mutableListOf<Byte>()
    list.addAll(listOf(BTCOPCODE.OP_DUP.code, BTCOPCODE.OP_HASH160.code))
    list.add(pubKey.size.toByte())
    list.addAll(pubKey.asList())
    list.addAll(listOf(BTCOPCODE.OP_EQUALVERIFY.code, BTCOPCODE.OP_CHECKSIG.code))
    return list.toByteArray()
}

data class BTCTxInput(val txHash: String, val vout: Int, val script: String)

data class BTCTxOutput(val value: Long, val scriptPubKey: ByteArray)

fun ByteArray.signWithECDSAsecp256k1(privateKey: String): ByteArray = signWithECDSAsecp256k1(privateKey, this)

fun ByteArray.ethSignWithECDSAsecp256k1(privateKey: String): List<BigInteger> = ethSignWithECDSAsecp256k1(privateKey, this)

fun getECDSAsecp256k1Signer(privateKey: String): ECDSASigner {
    val spec = ECNamedCurveTable.getParameterSpec("SECP256K1")
    val signer = ECDSASigner()
    val domain = ECDomainParameters(spec.curve, spec.g, spec.n)
    val ecPrivateKeyParams = ECPrivateKeyParameters(BigInteger(privateKey, 16), domain)
    val params = ParametersWithRandom(ecPrivateKeyParams)
    signer.init(true, params)
    return signer
}

fun signWithECDSAsecp256k1(privateKey: String, bytes: ByteArray): ByteArray {
    val result = mutableListOf<Byte>()
    getECDSAsecp256k1Signer(privateKey).generateSignature(bytes).forEach {
        result.addAll(it.toByteArray().asList())
    }
    return result.toByteArray()
}

fun ethSignWithECDSAsecp256k1(privateKey: String, bytes: ByteArray): List<BigInteger> {
    return getECDSAsecp256k1Signer(privateKey).generateSignature(bytes).toList()
}

enum class BTCHashCodeType(val code: Int) {SIGHASH_ALL(1)}