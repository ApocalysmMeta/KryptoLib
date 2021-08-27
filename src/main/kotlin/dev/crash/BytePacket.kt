package dev.crash

import dev.crash.exceptions.CouldNotReadValueOfTypeException
import dev.crash.tx.BTCOPCODE
import java.nio.charset.Charset
import kotlin.experimental.and

class BytePacket() {
    constructor(bytes: ByteArray): this() {
        this.bytes = bytes
    }

    private val byteBuffer = mutableListOf<Byte>()
    var bytes: ByteArray = ByteArray(0)
    var readPos = 0

    //region Write Functions
    fun write(value: Byte) {
        byteBuffer.add(value)
    }

    fun write(value: BTCOPCODE) {
        write(value.code)
    }

    fun writeETHByte(value: Byte) {
        write((value + (Byte.MIN_VALUE*-1)).toByte())
    }

    fun writeArrayWithSize(value: ByteArray) {
        writeETHByte(value.size.toByte())
        write(value)
    }

    fun writeETHNumber(value: Byte) {
        writeETHByte(1)
        write(value)
    }

    fun writeETHNumber(value: Short) {
        if(value <= Byte.MAX_VALUE){
            writeETHNumber(value.toByte())
        }else {
            writeETHByte(2)
            write(value)
        }
    }

    fun writeETHNumber(value: Int) {
        if(value <= Short.MAX_VALUE){
            writeETHNumber(value.toShort())
        }else {
            writeETHByte(4)
            write(value)
        }
    }

    fun writeETHNumber(value: Long) {
        if(value <= Int.MAX_VALUE){
            writeETHNumber(value.toInt())
        }else {
            writeETHByte(8)
            write(value)
        }
    }

    fun write(value: ByteArray) {
        byteBuffer.addAll(value.asList())
    }

    fun write(value: Short) {
        byteBuffer.addAll(value.toByteArray().asList())
    }

    fun writeAsVarInt(value: Int) {
        byteBuffer.addAll(value.toByteArrayAsVarInt())
    }

    fun writeAsVarLong(value: Long) {
        byteBuffer.addAll(value.toByteArrayAsVarLong())
    }

    fun writeAsCompactUInt(value: Int) {
        if(value <= 252){
            write(value.toByte())
        }
        else if(value <= 0xffff){
            write(0xfd)
            write(value.toShort())
        }else if(value <= 0xffffffff){
            write(0xfe)
            write(value)
        }else {
            write(0xff)
            write(value.toLong())
        }
    }

    fun write(value: Int) {
        byteBuffer.addAll(value.toByteArray())
    }

    fun write(value: Long) {
        byteBuffer.addAll(value.toByteArray())
    }

    fun write(value: Float) {
        writeAsVarInt(value.toBits())
    }

    fun write(value: Boolean) {
        write(if(value)1.toByte()else 0.toByte())
    }

    fun write(value: String) {
        val bytes = value.toByteArray().asList()
        writeAsVarInt(bytes.size)
        byteBuffer.addAll(bytes)
    }
    //endregion

    //region Read Functions
    fun readByte(): Byte {
        if(bytes.size > readPos) {
            val value = bytes[readPos]
            readPos++
            return value
        }else {
            throw CouldNotReadValueOfTypeException("Byte")
        }
    }

    fun readBytes(length: Int): ByteArray {
        if(bytes.size > readPos){
            val value = bytes.copyOfRange(readPos, readPos + length)
            readPos += length
            return value
        }else {
            throw CouldNotReadValueOfTypeException("ByteArray")
        }
    }

    fun readShort(): Short {
        if(bytes.size > readPos){
            val value = (0xff and bytes[readPos].toInt() shl 8 or (0xff and bytes[readPos + 1].toInt()) shl 0).toShort()
            readPos += 2
            return value
        }else {
            throw CouldNotReadValueOfTypeException("Short")
        }
    }

    fun readVarInt(): Int {
        var numRead = 0
        var result = 0
        var read: Byte
        do {
            read = readByte()
            val value: Int = (read and 127).toInt()
            result = result or (value shl 7 * numRead)
            numRead++
            if (numRead > 5) {
                throw RuntimeException("VarInt is too big")
            }
        } while (read and 128.toByte() != 0.toByte())
        return result
    }

    fun readVarLong(): Long {
        var numRead = 0
        var result: Long = 0
        var read: Byte
        do {
            read = readByte()
            val value = (read and 127).toLong()
            result = result or (value shl 7 * numRead)
            numRead++
            if (numRead > 10) {
                throw RuntimeException("VarLong is too big")
            }
        } while (read and 128.toByte() != 0.toByte())
        return result
    }

    fun readFloat(): Float {
        if(bytes.size > readPos) {
            return Float.fromBits(readVarInt())
        }else {
            throw CouldNotReadValueOfTypeException("Float")
        }
    }

    fun readBoolean(): Boolean {
        if(bytes.size > readPos) {
            val value = bytes[readPos].toInt() != 0
            readPos++
            return value
        }else {
            throw CouldNotReadValueOfTypeException("Boolean")
        }
    }

    fun readString(): String {
        if(bytes.size > readPos) {
            val length = readVarInt()
            val value = String(bytes, readPos, length, Charset.defaultCharset())
            readPos += length
            return value
        }else {
            throw CouldNotReadValueOfTypeException("String")
        }
    }

    fun readInt(): Int {
        if(bytes.size > readPos) {
            val result: Int = 0xff and bytes[readPos].toInt() shl 56 or (0xff and bytes[readPos + 1].toInt() shl 48
                    ) or (0xff and bytes[readPos + 2].toInt() shl 40) or (0xff and bytes[readPos + 3].toInt() shl 32)
            readPos += 4
            return result
        }else {
            throw CouldNotReadValueOfTypeException("3ByteInt")
        }
    }

    fun readLong(): Long {
        if(bytes.size > readPos) {
            val result : Long = (0xff and bytes[readPos].toInt()).toLong() shl 56 or ((0xff and bytes[readPos + 1].toInt()).toLong() shl 48
                    ) or ((0xff and bytes[readPos + 2].toInt()).toLong() shl 40
                    ) or ((0xff and bytes[readPos + 3].toInt()).toLong() shl 32
                    ) or ((0xff and bytes[readPos + 4].toInt()).toLong() shl 24
                    ) or ((0xff and bytes[readPos + 5].toInt()).toLong() shl 16
                    ) or ((0xff and bytes[readPos + 6].toInt()).toLong() shl 8) or ((0xff and bytes[readPos + 7].toInt()).toLong() shl 0)
            readPos += 8
            return result
        }else {
            throw CouldNotReadValueOfTypeException("3ByteInt")
        }
    }
    //endregion

    fun getByteArray(): ByteArray = byteBuffer.toByteArray()

    fun copy(): BytePacket{
        val newPacket = BytePacket(this.bytes)
        newPacket.readPos = this.readPos
        newPacket.byteBuffer.addAll(this.byteBuffer)
        return newPacket
    }
}