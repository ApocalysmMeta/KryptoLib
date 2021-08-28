package dev.crash.address

abstract class Address(val privateKey: String, val address: String, val type: AddressType) {
    fun getWIFKey(): String = privateKeyToWIF(privateKey, type)

    /*fun getBalance(): Double {
        return when(type) {
            AddressType.ETH -> KryptoLib.DEFAULT_ETHERSCAN.getAccountBalance(address).toDouble() / 10.0.pow(18)
            AddressType.BTC -> BTCBlockBook.getAddress(address).balance.toLong() / 10.0.pow(8)
            AddressType.DGB -> DigiExplorer.getAddress(address).balance.toLong() / 10.0.pow(8)
            AddressType.DOGE -> Dogechain.getAddressBalance(address).toDouble()
            AddressType.RDD -> Reddsight.getBalance(address).toDouble()
            AddressType.TRX -> Tronscan.getAccount(address).balance / 10.0.pow(6)
            AddressType.BCH -> BCHBlockBook.getAddress(address).balance.toLong() / 10.0.pow(8)
            AddressType.LTC -> LTCBlockBook.getAddress(address).balance.toLong() / 10.0.pow(8)
            AddressType.DASH -> DASHBlockBook.getAddress(address).balance.toLong() / 10.0.pow(8)
        }
    }*/
}
