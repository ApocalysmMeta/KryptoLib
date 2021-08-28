package dev.crash.address

import dev.crash.crypto.*
import dev.crash.toUTF8ByteArray
import org.bouncycastle.math.ec.FixedPointCombMultiplier
import java.math.BigInteger
import java.security.PrivateKey
import java.security.PublicKey
import java.security.interfaces.ECPrivateKey
import java.security.interfaces.ECPublicKey
import java.security.spec.ECPoint
import java.util.*

fun getaddedChecksum(key: ByteArray, checksum: ByteArray) : ByteArray{
    val result = ByteArray(key.size + 4)
    for (i in key.indices) result[i] = key[i]
    for (i in 0..3) result[key.size + i] = checksum[i]
    return result
}

fun getWithVersion(key: ByteArray, version : Byte) : ByteArray{
    val byteArray = ByteArray(key.size + 1)
    byteArray[0] = version
    for (i in key.indices) byteArray[i + 1] = key[i]
    return byteArray
}

fun adjustTo64(s: String): String {
    return when (s.length) {
        62 -> "00$s"
        63 -> "0$s"
        64 -> s
        else -> throw IllegalArgumentException("not a valid key: $s")
    }
}

fun PublicKey.getPublicKeyBytes(): ByteArray{
    val point : ECPoint = (this as ECPublicKey).w
    val sx = adjustTo64(point.affineX.toString(16)).uppercase()
    val sy = adjustTo64(point.affineY.toString(16)).uppercase()
    return "04$sx$sy".toUTF8ByteArray()
}

fun PrivateKey.getPrivateKeyString(): String = adjustTo64((this as ECPrivateKey).s.toString(16))

fun ByteArray.checksum() = this.sha256().sha256()

fun privateKeyToWIF(privateKey: String, type: AddressType) : String{
    val versionByte: Byte = when(type){
        AddressType.BTC, AddressType.BCH -> 0x80.toByte()
        AddressType.DASH -> 0xcc.toByte()
        AddressType.DGB, AddressType.DOGE -> 0x9e.toByte()
        AddressType.LTC -> 0xb0.toByte()
        AddressType.RDD -> 0xbd.toByte()
        else -> 0x00.toByte()
    }
    val versionString = if(versionByte != 0x00.toByte())versionByte.base58() else ""
    val shaHash = "$versionString$privateKey".encodeToByteArray().sha256()
    return getaddedChecksum(shaHash, shaHash.checksum()).base58()
}

fun wifToPrivateKey(wifKey: String): String {
    val byteString = wifKey.base58()
    byteString.dropLast(4)
    byteString.drop(1)
    return byteString.base58()
}

fun getHashedPublicKey(address: String): ByteArray {
    val base58 = address.base58()
    val droppedChecksum = base58.dropLast(4)
    val droppedVersion = droppedChecksum.drop(1)
    return droppedVersion.toByteArray()
}

fun getUncompressedPublicKey(privateKey: String): ByteArray {
    val privKey = BigInteger(privateKey, 16)
    return publicKeyFromPrivate(privKey).toByteArray()
}

fun publicKeyFromPrivate(privKey: BigInteger): BigInteger {
    val point = publicPointFromPrivate(privKey)
    val encoded = point.getEncoded(false)
    return BigInteger(1, Arrays.copyOfRange(encoded, 0, encoded.size))
}

private fun publicPointFromPrivate(privKey2: BigInteger): org.bouncycastle.math.ec.ECPoint {
    var privKey = privKey2
    if (privKey.bitLength() > CURVE.n.bitLength()) {
        privKey = privKey.mod(CURVE.n)
    }
    return FixedPointCombMultiplier().multiply(CURVE.g, privKey)
}