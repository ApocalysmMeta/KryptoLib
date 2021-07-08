package dev.crash.address

enum class AddressType{ BTC, ETH, BNB, TRX, LTC, BCH, DASH, DOGE, DGB, RDD }

fun AddressType.genAddress(): Address = AddressGen.genAddress(this)