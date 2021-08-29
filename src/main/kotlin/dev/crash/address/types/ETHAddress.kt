package dev.crash.address.types

import dev.crash.KryptoLib
import dev.crash.address.Address
import dev.crash.address.AddressType
import dev.crash.eth.ETHNetwork
import dev.crash.tx.raw.RawEthTransaction

class ETHAddress(privateKey: String, address: String) : Address(privateKey, address, AddressType.ETH) {
    fun getBalance(ethNetwork: ETHNetwork = ETHNetwork.MAINNET): Double = getBalance(ethNetwork.chainId)

    fun getBalance(chainId: Int): Double {
        return if(KryptoLib.ethHandlers.containsKey(chainId)){
            KryptoLib.ethHandlers[chainId]!!.getBalance(address)
        }else {
            throw UnsupportedOperationException("Can't get the balance of address $address on network $chainId, did you forgot to register an ETHHandler?")
        }
    }

    fun getTokenBalance(ethNetwork: ETHNetwork, tokenContract: String, decimals: Int = 18): Double = getTokenBalance(ethNetwork.chainId, tokenContract, decimals)

    fun getTokenBalance(chainId: Int, tokenContract: String, decimals: Int = 18): Double {
        return if(KryptoLib.ethHandlers.containsKey(chainId)){
            KryptoLib.ethHandlers[chainId]!!.getERC20TokenBalance(address, tokenContract, decimals)
        }else {
            throw UnsupportedOperationException("Can't get the balance of address $address on network $chainId, did you forgot to register an ETHHandler?")
        }
    }

    fun sendEther(ethNetwork: ETHNetwork, to: String, amount: Long): String = sendEther(ethNetwork.chainId, to, amount)

    fun sendEther(chainId: Int, to: String, amount: Long): String {
        return if(KryptoLib.ethHandlers.containsKey(chainId)){
            RawEthTransaction(this, to, amount, chainId).sign().submit()
        }else {
            throw UnsupportedOperationException("Can't send ether on network $chainId, did you forgot to register an ETHHandler?")
        }
    }
}