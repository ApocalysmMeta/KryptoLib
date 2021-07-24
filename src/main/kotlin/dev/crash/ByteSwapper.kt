package dev.crash

import java.nio.ByteBuffer
import java.nio.ByteOrder

object ByteSwapper {
    fun swap(value: Int): Int {
        val b1 = value shr 0 and 0xff
        val b2 = value shr 8 and 0xff
        val b3 = value shr 16 and 0xff
        val b4 = value shr 24 and 0xff
        return b1 shl 24 or (b2 shl 16) or (b3 shl 8) or (b4 shl 0)
    }

    fun swap(value: Long): Long {
        val b1 = value shr 0 and 0xff
        val b2 = value shr 8 and 0xff
        val b3 = value shr 16 and 0xff
        val b4 = value shr 24 and 0xff
        val b5 = value shr 32 and 0xff
        val b6 = value shr 40 and 0xff
        val b7 = value shr 48 and 0xff
        val b8 = value shr 56 and 0xff
        return b1 shl 56 or (b2 shl 48) or (b3 shl 40) or (b4 shl 32) or (
                b5 shl 24) or (b6 shl 16) or (b7 shl 8) or (b8 shl 0)
    }

    fun swap(value: Float): Float {
        var intValue = java.lang.Float.floatToIntBits(value)
        intValue = swap(intValue)
        return java.lang.Float.intBitsToFloat(intValue)
    }

    fun swap(value: Double): Double {
        var longValue = java.lang.Double.doubleToLongBits(value)
        longValue = swap(longValue)
        return java.lang.Double.longBitsToDouble(longValue)
    }

    fun swap(value: ByteArray): ByteArray {
        val bb = ByteBuffer.wrap(value)
        bb.order(ByteOrder.LITTLE_ENDIAN)
        return bb.array()
    }
}
