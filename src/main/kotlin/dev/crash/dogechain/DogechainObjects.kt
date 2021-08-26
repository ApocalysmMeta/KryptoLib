package dev.crash.dogechain

data class DogechainInfoUnspentOutput (
    val tx_hash: String,
    val tx_output_n: Long,
    val script: String,
    val value: String,
    val confirmations: Long
)

data class DogechainInfoBlock (
    val hash: String,
    val height: Long,
    val previous_block_hash: String,
    val next_block_hash: String,
    val is_orphan: Boolean,
    val difficulty: Double,
    val time: Long,
    val confirmations: Long,
    val merkleroot: String,
    val num_txs: Long,
    val value_in: String,
    val value_out: String,
    val version: Long,
    val average_coin_age: Double,
    val nonce: Long,
    val txs: List<String>
)

data class DogechainInfoTransaction (
    val hash: String,
    val confirmations: Long,
    val size: Long,
    val version: Long,
    val locktime: Long,
    val block_hash: String,
    val time: Long,
    val inputs_n: Long,
    val inputs: List<DogechainInfoTxInput>,
    val inputs_value: String,
    val outputs_n: Long,
    val outputs: List<DogechainInfoTxOutput>,
    val outputs_value: String,
    val fee: String
)

data class DogechainInfoTxInput (
    val pos: Long,
    val value: String,
    val type: String,
    val address: String,
    val scriptSig: DogechainInfoTxScriptSig,
    val previous_output: DogechainInfoTxPreviousOutput
)

data class DogechainInfoTxPreviousOutput (
    val hash: String,
    val pos: Long
)

data class DogechainInfoTxScriptSig (
    val hex: String
)

data class DogechainInfoTxOutput (
    val pos: Long,
    val value: String,
    val type: String,
    val address: String
)

data class DogechainWebsocketTransaction (
    val hash: String,
    val value_out: Long,
    val lock_time: Long,
    val vout_sz: Long,
    val vin_sz: Long,
    val ver: Long,
    val inputs: List<DogechainWebsocketTxInput>,
    val outputs: List<DogechainWebsocketTxOutput>,
    val size: Long
)

data class DogechainWebsocketTxInput (
    val prev_out: DogechainWebsocketTxPrevOut
)

data class DogechainWebsocketTxPrevOut (
    val hash: String,
    val n: Long,
    val value: Long,
    val addr: String
)

data class DogechainWebsocketTxOutput (
    val value: Long,
    val addr: String
)

data class DogechainWebsocketBlock (
    val txs: List<String>,
    val time: Long,
    val height: Long,
    val reward: Long,
    val nonce: Long,
    val hash: String,
    val bits: String,
    val difficulty: Double,
    val n_tx: Long,
    val merkleroot: String,
    val size: Long,
    val version: Long
)