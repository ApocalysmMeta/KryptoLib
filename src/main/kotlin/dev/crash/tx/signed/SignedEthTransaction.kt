package dev.crash.tx.signed

import dev.crash.tx.ETHNetwork
import dev.crash.tx.raw.RawEthTransaction

class SignedEthTransaction internal constructor(rawTx: RawEthTransaction): SignedTransaction() {
    fun submitToNetwork(ethNetwork: ETHNetwork): Boolean {
        return true
    }
}