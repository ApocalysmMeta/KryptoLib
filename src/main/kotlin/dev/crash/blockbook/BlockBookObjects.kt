package dev.crash.blockbook

import dev.crash.EMPTY

data class BlockBookWebsocketRequest(
    val id: String,
    val method: String,
    val params: Any = EMPTY()
)

data class BlockBookWebsocketBlock(
    val height: Int,
    val hash: String
)

data class BlockBookWebsocketRates(
    val rates: Map<String, Double>
)

data class BlockBookAPIStatus (
    val blockbook: BlockBookAPIStatusBlockBook,
    val backend: BlockBookAPIStatusBackend
)

data class BlockBookAPIStatusBackend (
    val chain: String,
    val blocks: Long,
    val headers: Long,
    val bestBlockHash: String,
    val difficulty: String?,
    val sizeOnDisk: Long,
    val version: String,
    val subversion: String,
    val protocolVersion: String,
    val timeOffset: Long,
    val warnings: String?
)

data class BlockBookAPIStatusBlockBook (
    val coin: String,
    val host: String,
    val version: String,
    val gitCommit: String,
    val buildTime: String,
    val syncMode: Boolean,
    val initialSync: Boolean,
    val inSync: Boolean,
    val bestHeight: Long,
    val lastBlockTime: String,
    val inSyncMempool: Boolean,
    val lastMempoolTime: String,
    val mempoolSize: Long,
    val decimals: Long,
    val dbSize: Long,
    val about: String
)

data class BlockBookTransaction (
    val txid: String,
    val version: Long,
    val vin: List<BlockBookTransactionVin>,
    val vout: List<BlockBookTransactionVout>,
    val blockHash: String,
    val blockHeight: Long,
    val confirmations: Long,
    val blockTime: Long,
    val value: String,
    val valueIn: String,
    val fees: String,
    val hex: String
)

data class BlockBookTransactionVin (
    val txid: String,
    val vout: Long,
    val sequence: Long,
    val n: Long,
    val addresses: List<String>,
    val isAddress: Boolean,
    val value: String,
    val hex: String
)

data class BlockBookTransactionVout (
    val value: String,
    val n: Long,
    val hex: String,
    val addresses: List<String>,
    val isAddress: Boolean
)

data class BlockBookAddress (
    val page: Long,
    val totalPages: Long,
    val itemsOnPage: Long,
    val address: String,
    val balance: String,
    val totalReceived: String,
    val totalSent: String,
    val unconfirmedBalance: String,
    val unconfirmedTxs: Long,
    val txs: Long,
    val txids: List<String>
)

data class BlockBookUTXO (
    val txid: String,
    val vout: Long,
    val value: String,
    val confirmations: Long,
    val lockTime: Long?,
    val height: Long?,
    val coinbase: Boolean?
)

data class BlockBookBlock (
    val page: Long,
    val totalPages: Long,
    val itemsOnPage: Long,
    val hash: String,
    val previousBlockHash: String,
    val nextBlockHash: String,
    val height: Long,
    val confirmations: Long,
    val size: Long,
    val time: Long,
    val version: Long,
    val merkleRoot: String,
    val nonce: String,
    val bits: String,
    val difficulty: String,
    val txCount: Long,
    val txs: List<BlockBookBlockTx>
)

data class BlockBookBlockTx (
    val txid: String,
    val vin: List<BlockBookBlockVin>,
    val vout: List<BlockBookBlockVin>,
    val blockHash: String,
    val blockHeight: Long,
    val confirmations: Long,
    val blockTime: Long,
    val value: String,
    val valueIn: String,
    val fees: String
)

data class BlockBookBlockVin (
    val n: Long,
    val value: String,
    val addresses: List<String>? = null,
    val isAddress: Boolean? = null,
    val spent: Boolean? = null
)

data class BlockBookBalanceHistory (
    val time: Long,
    val txs: Long,
    val received: String,
    val sent: String,
    val sentToSelf: String,
    val rates: Map<String, Double>
)