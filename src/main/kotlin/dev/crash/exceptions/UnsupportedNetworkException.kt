package dev.crash.exceptions

import dev.crash.eth.ETHNetwork

class UnsupportedNetworkException(message: String) : Throwable(message) {
    constructor(): this("Unsupported ETH Network")
    constructor(ethNetwork: ETHNetwork): this("Unsupported network for this action: ${ethNetwork.name}!")
    constructor(chainId: Int): this("Unsupported chainId for this action: $chainId")
}