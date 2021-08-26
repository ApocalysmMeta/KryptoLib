package dev.crash.reddsight

import java.math.BigInteger

data class ReddsightBlock (
    val hash: String,
    val confirmations: Long,
    val size: Long,
    val height: Long,
    val version: Long,
    val merkleroot: String,
    val tx: List<String>,
    val time: Long,
    val nonce: Long,
    val bits: String,
    val difficulty: Double,
    val chainwork: String,
    val previousblockhash: String,
    val flags: String,
    val moneysupply: Double,
    val mint: Double,
    val entropybit: Long,
    val modifier: String,
    val modifierchecksum: String,
    val signature: String,
    val reward: Long,
    val isMainChain: Boolean
)

data class ReddsightTransaction (
    val txid: String,
    val version: Long,
    val locktime: Long,
    val vin: List<ReddsightVin>,
    val vout: List<ReddsightVout>,
    val blockhash: String,
    val confirmations: Long,
    val time: Long,
    val blocktime: Long,
    val isCoinStake: Boolean,
    val valueOut: Double,
    val size: Long,
    val valueIn: Double,
    val fees: Double
)

data class ReddsightVin (
    val txid: String,
    val vout: Long,
    val scriptSig: ReddsightScriptSig,
    val sequence: Long,
    val n: Long,
    val addr: String,
    val valueSat: Long,
    val value: Double,
    val doubleSpentTxID: Any? = null
)

data class ReddsightScriptSig (
    val asm: String
)

data class ReddsightVout (
    val value: String,
    val n: Long,
    val scriptPubKey: ReddsightScriptPubKey
)

data class ReddsightScriptPubKey (
    val asm: String,
    val reqSigs: Long,
    val type: String,
    val addresses: List<String>
)

data class ReddsightAddress (
    val addrStr: String,
    val balance: Double,
    val balanceSat: BigInteger,
    val totalReceived: Double,
    val totalReceivedSat: BigInteger,
    val totalSent: Double,
    val totalSentSat: BigInteger,
    val unconfirmedBalance: Double,
    val unconfirmedBalanceSat: BigInteger,
    val unconfirmedTxApperances: Long,
    val txApperances: Long,
    val transactions: List<String>
)

data class ReddsightUTXO (
    val address: String,
    val txid: String,
    val vout: Long,
    val ts: Long,
    val scriptPubKey: String,
    val amount: Double,
    val confirmations: Long?,
    val confirmationsFromCache: Boolean?
)

internal data class ReddsightPageResponse<T> (
    val pagesTotal: Long,
    val txs: List<T>
)

data class ReddsightSyncStatus (
    val status: String,
    val blockChainHeight: Long,
    val syncPercentage: String,
    val height: Long,
    val error: Any? = null,
    val type: String,
    val startTs: Long,
    val endTs: Long
)