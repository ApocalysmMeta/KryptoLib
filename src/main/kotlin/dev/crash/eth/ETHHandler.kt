package dev.crash.eth

abstract class ETHHandler(val chainId: Int) {
    abstract fun getBalance(address: String): Double

    abstract fun getERC20TokenBalance(address: String, tokenContract: String, decimals: Int): Double

    abstract fun sendRawTransaction(hex: String): String
}