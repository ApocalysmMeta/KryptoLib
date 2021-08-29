package dev.crash.address.types

import dev.crash.KryptoLib
import dev.crash.address.Address
import dev.crash.address.AddressType
import dev.crash.eth.ETHNetwork
import dev.crash.tx.raw.RawEthTransaction

class ETHAddress(privateKey: String, address: String) : Address(privateKey, address, AddressType.ETH) {
    fun getBalance(ethNetwork: ETHNetwork = ETHNetwork.MAINNET): Double = getBalance(ethNetwork.chainId)

    fun getBalance(chainId: Int): Double = KryptoLib.getETHHandler(chainId).getBalance(address)

    fun getTokenBalance(ethNetwork: ETHNetwork, tokenContract: String, decimals: Int = 18): Double = getTokenBalance(ethNetwork.chainId, tokenContract, decimals)

    fun getTokenBalance(chainId: Int, tokenContract: String, decimals: Int = 18): Double {
        return KryptoLib.getETHHandler(chainId).getERC20TokenBalance(address, tokenContract, decimals)
    }

    fun sendEther(ethNetwork: ETHNetwork, to: String, amount: Long): String = sendEther(ethNetwork.chainId, to, amount)

    fun sendEther(chainId: Int, to: String, amount: Long): String {
        KryptoLib.getETHHandler(chainId)
        return RawEthTransaction(this, to, amount, chainId).sign().submit()
    }

    fun sendToken(ethNetwork: ETHNetwork, to: String, amount: Long, tokenContract: String): String = sendToken(ethNetwork.chainId, to, amount, tokenContract)

    fun sendToken(chainId: Int, to: String, amount: Long, tokenContract: String): String {
        KryptoLib.getETHHandler(chainId)
        return ""
    }
}