package dev.crash.tx

import dev.crash.address.getUncompressedPublicKey
import dev.crash.crypto.CURVE
import dev.crash.crypto.CURVE_PARAMS
import org.bouncycastle.asn1.x9.X9IntegerConverter
import org.bouncycastle.crypto.digests.SHA256Digest
import org.bouncycastle.crypto.params.ECDomainParameters
import org.bouncycastle.crypto.params.ECPrivateKeyParameters
import org.bouncycastle.crypto.signers.ECDSASigner
import org.bouncycastle.crypto.signers.HMacDSAKCalculator
import org.bouncycastle.math.ec.ECAlgorithms
import org.bouncycastle.math.ec.ECPoint
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

fun ByteArray.signWithECDSAsecp256k1BTC(privateKey: String): ByteArray {
    val sig = signWithECDSAsecp256k1(privateKey)
    val result = mutableListOf<Byte>()
    result.addAll(sig.r.toByteArray().toList())
    result.addAll(sig.s.toByteArray().toList())
    return result.toByteArray()
}

fun ByteArray.signWithECDSAsecp256k1(privateKey: String): ECDSASignature = ethSign(this, privateKey)

enum class BTCHashCodeType(val code: Int) {SIGHASH_ALL(1)}

val HALF_CURVE_ORDER: BigInteger = CURVE_PARAMS.n.shiftRight(1)

class ECDSASignature(val r: BigInteger, val s: BigInteger) {
    var v: Byte = -1

    fun toCanonicalised(): ECDSASignature {
        return if (s > HALF_CURVE_ORDER) {
            ECDSASignature(r, CURVE.n.subtract(s))
        } else {
            this
        }
    }
}

fun ethSign(messageHash: ByteArray, privKey: String): ECDSASignature {
    val sig: ECDSASignature = doSign(messageHash, BigInteger(privKey, 16))
    var recId = -1
    val thisKey: ByteArray = getUncompressedPublicKey(privKey)
    for (i in 0..3) {
        val k: ByteArray? = recoverPubBytesFromSignature(i, sig, messageHash)
        if (k != null && k.contentEquals(thisKey)) {
            recId = i
            break
        }
    }
    if (recId == -1) {
        throw RuntimeException(
            "Could not construct a recoverable key" +
                    ". This should never happen."
        )
    }
    sig.v = recId.toByte()
    return sig
}

fun doSign(input: ByteArray, privKey: BigInteger): ECDSASignature {
    if (input.size != 32) {
        throw IllegalArgumentException(
            "Expected 32 byte input to " +
                    "ECDSA signature, not " + input.size
        )
    }
    val signer = ECDSASigner(HMacDSAKCalculator(SHA256Digest()))
    val domain = ECDomainParameters(CURVE_PARAMS.curve, CURVE_PARAMS.g, CURVE_PARAMS.n)
    val ecPrivateKeyParams = ECPrivateKeyParameters(privKey, domain)
    signer.init(true, ecPrivateKeyParams)
    val components = signer.generateSignature(input)
    return ECDSASignature(components[0], components[1]).toCanonicalised()
}

fun recoverPubBytesFromSignature(
    recId: Int,
    sig: ECDSASignature, messageHash: ByteArray
): ByteArray? {
    check(recId >= 0) { "recId must be positive" }
    check(sig.r.signum() >= 0) { "r must be positive" }
    check(sig.s.signum() >= 0) { "s must be positive" }
    val n = CURVE.n
    val i = BigInteger.valueOf(recId.toLong() / 2)
    val x = sig.r.add(i.multiply(n))
    val R: ECPoint = decompressKey(x, recId and 1 == 1)
    if (!R.multiply(n).isInfinity) {
        return null
    }
    val e = BigInteger(1, messageHash)
    val eInv = BigInteger.ZERO.subtract(e).mod(n)
    val rInv = sig.r.modInverse(n)
    val srInv = rInv.multiply(sig.s).mod(n)
    val eInvrInv = rInv.multiply(eInv).mod(n)
    val q = ECAlgorithms.sumOfTwoMultiplies(CURVE.g, eInvrInv, R, srInv)
    return q.getEncoded( /* compressed */false)
}

private fun decompressKey(xBN: BigInteger, yBit: Boolean): ECPoint {
    val x9 = X9IntegerConverter()
    val compEnc = x9.integerToBytes(
        xBN, 1 + x9.getByteLength(CURVE.curve)
    )
    compEnc[0] = (if (yBit) 0x03 else 0x02).toByte()
    return CURVE.curve.decodePoint(compEnc)
}
