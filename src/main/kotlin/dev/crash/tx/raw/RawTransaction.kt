package dev.crash.tx.raw

import dev.crash.address.AddressType
import dev.crash.tx.signed.SignedTransaction

abstract class RawTransaction internal constructor(addressType: AddressType){
    abstract fun sign(): SignedTransaction
}