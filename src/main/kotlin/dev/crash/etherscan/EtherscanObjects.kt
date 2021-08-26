package dev.crash.etherscan

internal data class EtherscanResponse<T>(
    val status: Int,
    val message: String,
    val result: T
)

data class EtherscanAddressBalance(
    val account: String,
    val balance: String
)

data class EtherscanTransaction (
    val blockNumber: String,
    val timeStamp: String,
    val hash: String,
    val nonce: String,
    val blockHash: String,
    val transactionIndex: String,
    val from: String,
    val to: String,
    val value: String,
    val gas: String,
    val gasPrice: String,
    val isError: String,
    val txreceipt_status: String,
    val input: String,
    val contractAddress: String,
    val cumulativeGasUsed: String,
    val gasUsed: String,
    val confirmations: String
)

data class EtherscanInternalTransaction (
    val blockNumber: String,
    val timeStamp: String,
    val hash: String,
    val from: String,
    val to: String,
    val value: String,
    val contractAddress: String,
    val input: String,
    val type: String,
    val gas: String,
    val gasUsed: String,
    val traceId: String,
    val isError: String,
    val errCode: String
)

data class EtherscanTokenTransfer(
    val blockNumber: String,
    val timeStamp: String,
    val hash: String,
    val nonce: String,
    val blockHash: String,
    val from: String,
    val contractAddress: String,
    val to: String,
    val value: String,
    val tokenID: String?,
    val tokenName: String,
    val tokenSymbol: String,
    val tokenDecimal: String,
    val transactionIndex: String,
    val gas: String,
    val gasPrice: String,
    val gasUsed: String,
    val cumulativeGasUsed: String,
    val input: String,
    val confirmations: String
)

data class EtherscanBlock (
    val blockNumber: String,
    val timeStamp: String,
    val blockReward: String
)

data class EtherscanBlockReward (
    val blockNumber: String,
    val timeStamp: String,
    val blockMiner: String,
    val blockReward: String,
    val uncles: List<EtherscanUncle>,
    val uncleInclusionReward: String
)

data class EtherscanUncle (
    val miner: String,
    val unclePosition: String,
    val blockreward: String
)

data class EtherscanBlockCountdown (
    val CurrentBlock: String,
    val CountdownBlock: String,
    val RemainingBlock: String,
    val EstimateTimeInSec: String
)

data class EtherscanGasOracle (
    val LastBlock: String,
    val SafeGasPrice: String,
    val ProposeGasPrice: String,
    val FastGasPrice: String
)

data class EtherscanPrice (
    val ethbtc: String,
    val ethbtc_timestamp: String,
    val ethusd: String,
    val ethusd_timestamp: String
)

data class EtherscanNodeSize (
    val blockNumber: String,
    val chainTimeStamp: String,
    val chainSize: String,
    val clientType: String,
    val syncMode: String
)

data class EtherscanNodeCount (
    val UTCDate: String,
    val TotalNodeCount: String
)