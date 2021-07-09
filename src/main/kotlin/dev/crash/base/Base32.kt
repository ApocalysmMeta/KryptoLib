package dev.crash.base

import kotlin.experimental.and
import kotlin.experimental.or

object Base32 {
    private const val base32Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567"

    private val base32Lookup = byteArrayOf(
        26, 27, 28, 29, 30, 31, -1,
        -1, -1, -1, -1, -1, -1, -1,
        -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
        15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,
        -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
        15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25
    )

    private const val errorCanonicalLength = "non canonical Base32 string length"
    private const val errorCanonicalEnd = "non canonical bits at end of Base32 string"
    private const val errorInvalidChar = "invalid character in Base32 string"

    fun decode(base32: String): ByteArray {
        when (base32.length % 8) {
            1, 3, 6 -> throw IllegalArgumentException(errorCanonicalLength)
        }
        val bytes = ByteArray(base32.length * 5 / 8)
        var offset = 0
        var i = 0
        var lookup: Int
        var nextByte: Byte
        var digit: Byte
        while (i < base32.length) {
            lookup = base32[i++] - '2'
            require(!(lookup < 0 || lookup >= base32Lookup.size)) { errorInvalidChar }
            digit = base32Lookup[lookup]
            require(digit.toInt() != -1) { errorInvalidChar }
            nextByte = (digit.toInt() shl 3).toByte()
            lookup = base32[i++] - '2'
            require(!(lookup < 0 || lookup >= base32Lookup.size)) { errorInvalidChar }
            digit = base32Lookup[lookup]
            require(digit.toInt() != -1) { errorInvalidChar }
            bytes[offset++] = (nextByte or ((digit.toInt() shr 2).toByte()))
            nextByte = (((digit and 3).toInt() shl 6).toByte())
            if (i >= base32.length) {
                require(nextByte == 0.toByte()) { errorCanonicalEnd }
                break
            }
            lookup = base32[i++] - '2'
            require(!(lookup < 0 || lookup >= base32Lookup.size)) { errorInvalidChar }
            digit = base32Lookup[lookup]
            require(digit.toInt() != -1) { errorInvalidChar }
            nextByte = nextByte or (digit.toInt() shl 1).toByte()
            lookup = base32[i++] - '2'
            require(!(lookup < 0 || lookup >= base32Lookup.size)) { errorInvalidChar }
            digit = base32Lookup[lookup]
            require(digit.toInt() != -1) { errorInvalidChar }
            bytes[offset++] = (nextByte or ((digit.toInt() shr 4).toByte()))
            nextByte = (((digit and 15).toInt() shl 4).toByte())
            if (i >= base32.length) {
                require(nextByte == 0.toByte()) { errorCanonicalEnd }
                break
            }
            lookup = base32[i++] - '2'
            require(!(lookup < 0 || lookup >= base32Lookup.size)) { errorInvalidChar }
            digit = base32Lookup[lookup]
            require(digit.toInt() != -1) { errorInvalidChar }
            bytes[offset++] = (nextByte or ((digit.toInt() shr 1).toByte()))
            nextByte = (((digit and 1).toInt() shl 7).toByte())
            if (i >= base32.length) {
                require(nextByte == 0.toByte()) { errorCanonicalEnd }
                break
            }
            lookup = base32[i++] - '2'
            require(!(lookup < 0 || lookup >= base32Lookup.size)) { errorInvalidChar }
            digit = base32Lookup[lookup]
            require(digit.toInt() != -1) { errorInvalidChar }
            nextByte = nextByte or (digit.toInt() shl 2).toByte()
            lookup = base32[i++] - '2'
            require(!(lookup < 0 || lookup >= base32Lookup.size)) { errorInvalidChar }
            digit = base32Lookup[lookup]
            require(digit.toInt() != -1) { errorInvalidChar }
            bytes[offset++] = (nextByte or ((digit.toInt() shr 3).toByte()))
            nextByte = (((digit and 7).toInt() shl 5).toByte())
            if (i >= base32.length) {
                require(nextByte == 0.toByte()) { errorCanonicalEnd }
                break
            }
            lookup = base32[i++] - '2'
            require(!(lookup < 0 || lookup >= base32Lookup.size)) { errorInvalidChar }
            digit = base32Lookup[lookup]
            require(digit.toInt() != -1) { errorInvalidChar }
            bytes[offset++] = (nextByte or digit)
        }
        return bytes
    }

    fun encode(bytes: ByteArray): String {
        val base32 = StringBuffer((bytes.size * 8 + 4) / 5)
        var currByte: Int
        var digit: Int
        var i = 0
        while (i < bytes.size) {
            currByte = bytes[i++].toInt() and 255
            base32.append(base32Chars[currByte shr 3])
            digit = currByte and 7 shl 2
            if (i >= bytes.size) {
                base32.append(base32Chars[digit])
                break
            }
            currByte = bytes[i++].toInt() and 255
            base32.append(base32Chars[digit or (currByte shr 6)])
            base32.append(base32Chars[currByte shr 1 and 31])
            digit = currByte and 1 shl 4
            if (i >= bytes.size) {
                base32.append(base32Chars[digit])
                break
            }
            currByte = bytes[i++].toInt() and 255
            base32.append(base32Chars[digit or (currByte shr 4)])
            digit = currByte and 15 shl 1
            if (i >= bytes.size) {
                base32.append(base32Chars[digit])
                break
            }
            currByte = bytes[i++].toInt() and 255
            base32.append(base32Chars[digit or (currByte shr 7)])
            base32.append(base32Chars[currByte shr 2 and 31])
            digit = currByte and 3 shl 3
            if (i >= bytes.size) {
                base32.append(base32Chars[digit])
                break
            }
            currByte = bytes[i++].toInt() and 255
            base32.append(base32Chars[digit or (currByte shr 5)])
            base32.append(base32Chars[currByte and 31])
        }
        return base32.toString()
    }
}