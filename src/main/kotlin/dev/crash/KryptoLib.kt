package dev.crash

import dev.crash.eth.ETHHandler
import dev.crash.eth.ETHNetwork
import dev.crash.eth.handlers.*
import dev.crash.etherscan.*

object KryptoLib {
    var DEFAULT_ETHERSCAN = Etherscan("")
    var DEFAULT_ETHERSCAN_GOERLI = EtherscanGoerli("")
    var DEFAULT_ETHERSCAN_KOVAN = EtherscanKovan("")
    var DEFAULT_ETHERSCAN_RINKEBY = EtherscanRinkeby("")
    var DEFAULT_ETHERSCAN_ROPSTEN = EtherscanRopsten("")
    var DEFAULT_BSCSCAN = Bscscan("")

    val ethHandlers = hashMapOf(ETHNetwork.MAINNET.chainId to ETHMainnetHandler(), ETHNetwork.SMART_CHAIN.chainId to ETHSmartChainHandler(),
        ETHNetwork.GOERLI.chainId to ETHGoerliHandler(), ETHNetwork.KOVAN.chainId to ETHKovanHandler(), ETHNetwork.RINKEBY.chainId to ETHRinkebyHandler(),
        ETHNetwork.ROPSTEN.chainId to ETHRopstenHandler())

    fun registerETHHandler(ethHandler: ETHHandler) {
        ethHandlers[ethHandler.chainId] = ethHandler
    }

    fun setEtherscanAPIKey(apiKey: String) {
        DEFAULT_ETHERSCAN = Etherscan(apiKey)
        DEFAULT_ETHERSCAN_GOERLI = EtherscanGoerli(apiKey)
        DEFAULT_ETHERSCAN_KOVAN = EtherscanKovan(apiKey)
        DEFAULT_ETHERSCAN_RINKEBY = EtherscanRinkeby(apiKey)
        DEFAULT_ETHERSCAN_ROPSTEN = EtherscanRopsten(apiKey)
    }

    fun setBscscanAPIKey(apiKey: String) {
        DEFAULT_BSCSCAN = Bscscan(apiKey)
    }
}