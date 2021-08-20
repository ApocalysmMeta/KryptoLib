package dev.crash.address

enum class AddressType(val versionID: Int){
    BTC(1), ETH(1), BNB(1), TRX(1), LTC(0), BCH(0), DASH(0),
    DOGE(0), DGB(0), RDD(0)
}

fun AddressType.genAddress(): Address = AddressGen.genAddress(this)