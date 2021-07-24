package dev.crash.tx

fun buildP2PKHScriptPupKey(pubKey: ByteArray): ByteArray {
    val list = mutableListOf<Byte>()
    list.addAll(listOf(0x76.toByte(), 0xa9.toByte(), 0x14.toByte()))
    list.addAll(pubKey.asList())
    list.addAll(listOf(0x88.toByte(), 0xac.toByte()))
    return list.toByteArray()
}