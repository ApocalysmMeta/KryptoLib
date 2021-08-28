package dev.crash.eth.handlers

import dev.crash.KryptoLib
import dev.crash.eth.ETHHandler
import dev.crash.eth.ETHNetwork
import kotlin.math.pow

class ETHRinkebyHandler : ETHHandler(ETHNetwork.RINKEBY.chainId) {
    override fun getBalance(address: String): Double {
        return KryptoLib.DEFAULT_ETHERSCAN_RINKEBY.getAccountBalance(address).toDouble() / 10.0.pow(18)
    }

    override fun getERC20TokenBalance(address: String, tokenContract: String, decimals: Int): Double {
        return KryptoLib.DEFAULT_ETHERSCAN_RINKEBY.getERC20TokenBalance(address, tokenContract).toDouble() / 10.0.pow(decimals)
    }

    override fun sendRawTransaction(hex: String): String {
        return KryptoLib.DEFAULT_ETHERSCAN_RINKEBY.sendRawTransaction(hex)
    }
}