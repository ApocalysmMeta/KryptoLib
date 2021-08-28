package dev.crash.tronscan

import com.fasterxml.jackson.databind.JsonNode

data class TronscanLatestBlock (
    val hash: String,
    val confirmed: Boolean,
    val number: Long,
    val size: Long,
    val timestamp: Long,
    val parentHash: String,
    val witnessAddress: String,
    val nrOfTrx: Long,
    val txTrieRoot: String,
    val witnessId: Long
)

data class TronscanResponse<T> (
    val total: Long,
    val rangeTotal: Long,
    val data: T
)

data class TronscanBlock (
    val number: Long,
    val hash: String,
    val size: Long,
    val timestamp: Long,
    val txTrieRoot: String,
    val parentHash: String,
    val witnessId: Long,
    val witnessAddress: String,
    val nrOfTrx: Long,
    val witnessName: String,
    val version: String,
    val confirmed: Boolean,
    val confirmations: Long,
    val netUsage: Long,
    val energyUsage: Long,
    val blockReward: Long,
    val voteReward: Long,
    val revert: Boolean
)

data class TronscanAddressList (
    val total: Long,
    val data: List<TronscanAddressListData>,
    val contractMap: Map<String, Boolean>,
    val rangeTotal: Long,
    val contractInfo: Map<String, TronscanContractInfo>
)

data class TronscanContractInfo (
    val tag1: String,
    val tag1Url: String,
    val vip: Boolean
)

data class TronscanAddressListData (
    val address: String,
    val addressTagLogo: String,
    val balance: Long,
    val power: Long,
    val totalTransactionCount: Long,
    val addressTag: String?
)

data class TronscanAccount (
    val trc20token_balances: List<TronscanAccountTrc20TokenBalance>,
    val transactions_out: Long,
    val acquiredDelegateFrozenForBandWidth: Long,
    val rewardNum: Long,
    val tokenBalances: List<TronscanAccountTokenBalance>,
    val delegateFrozenForEnergy: Long,
    val balances: List<TronscanAccountTokenBalance>,
    val trc721token_balances: List<JsonNode?>,
    val balance: Long,
    val voteTotal: Long,
    val totalFrozen: Long,
    val tokens: List<TronscanAccountTokenBalance>,
    val delegated: JsonNode,
    val transactions_in: Long,
    val totalTransactionCount: Long,
    val representative: TronscanAccountRepresentative,
    val frozenForBandWidth: Long,
    val reward: Long,
    val addressTagLogo: String,
    val allowExchange: List<JsonNode?>,
    val address: String,
    val frozen_supply: List<JsonNode?>,
    val bandwidth: TronscanAccountBandwidth,
    val date_created: Long,
    val accountType: Long,
    val addressTag: String,
    val exchanges: List<JsonNode?>,
    val frozen: TronscanAccountFrozen,
    val accountResource: TronscanAccountResource,
    val transactions: Long,
    val witness: Long,
    val delegateFrozenForBandWidth: Long,
    val name: String,
    val frozenForEnergy: Long,
    val acquiredDelegateFrozenForEnergy: Long,
    val activePermissions: List<JsonNode?>
)

data class TronscanAccountResource (
    val frozen_balance_for_energy: JsonNode
)

data class TronscanAccountTokenBalance (
    val amount: Double?,
    val tokenPriceInTrx: Double?,
    val tokenId: String,
    val balance: String,
    val tokenName: String,
    val tokenDecimal: Long,
    val tokenAbbr: String,
    val tokenCanShow: Long,
    val tokenType: String,
    val vip: Boolean,
    val tokenLogo: String,
    val owner_address: String?,
    val transferCount: Long?,
    val nrOfTokenHolders: Long?
)

data class TronscanAccountBandwidth (
    val energyRemaining: Long,
    val totalEnergyLimit: Long,
    val totalEnergyWeight: Long,
    val netUsed: Long,
    val storageLimit: Long,
    val storagePercentage: Double,
    val assets: Map<String, TronscanAccountAsset>,
    val netPercentage: Double,
    val storageUsed: Long,
    val storageRemaining: Long,
    val freeNetLimit: Long,
    val energyUsed: Long,
    val freeNetRemaining: Long,
    val netLimit: Long,
    val netRemaining: Long,
    val energyLimit: Long,
    val freeNetUsed: Long,
    val totalNetWeight: Long,
    val freeNetPercentage: Double,
    val energyPercentage: Double,
    val totalNetLimit: Long
)

data class TronscanAccountAsset (
    val netPercentage: Double,
    val netLimit: Long,
    val netRemaining: Long,
    val netUsed: Long
)

data class TronscanAccountFrozen (
    val total: Long,
    val balances: List<TronscanAccountFrozenBalance>
)

data class TronscanAccountFrozenBalance (
    val expires: Long,
    val amount: Long
)

data class TronscanAccountRepresentative (
    val lastWithDrawTime: Long,
    val allowance: Long,
    val enabled: Boolean,
    val url: String
)

data class TronscanAccountTrc20TokenBalance (
    val tokenId: String,
    val balance: String,
    val tokenName: String,
    val tokenAbbr: String,
    val tokenDecimal: Long,
    val tokenCanShow: Long,
    val tokenType: String,
    val tokenLogo: String,
    val vip: Boolean,
    val tokenPriceInTrx: Double?,
    val amount: Double?,
    val nrOfTokenHolders: Long,
    val transferCount: Long
)

data class TronscanTransactionResponse (
    val total: Long,
    val rangeTotal: Long,
    val data: List<TronscanTransactionsObj>,
    val wholeChainTxCount: Long,
    val contractMap: Map<String, Boolean>,
    val contractInfo: Map<String, TronscanContractInfo>
)

data class TronscanTransactionsObj (
    val block: Long,
    val hash: String,
    val timestamp: Long,
    val ownerAddress: String,
    val toAddressList: List<String>,
    val toAddress: String,
    val contractType: Long,
    val confirmed: Boolean,
    val revert: Boolean,
    val contractData: TronscanContractData,
    val SmartCalls: String,
    val Events: String,
    val id: String,
    val data: String,
    val fee: String,
    val contractRet: String,
    val result: String,
    val amount: String,
    val cost: TronscanTransactionCost,
    val tokenInfo: TronscanTokenInfo,
    val tokenType: String
)

data class TronscanContractData (
    val data: String?,
    val owner_address: String,
    val contract_address: String?,
    val call_value: Long?,
    val amount: Long?,
    val to_address: String?
)

data class TronscanTransactionCost (
    val net_fee: Long,
    val energy_usage: Long,
    val energy_fee: Long,
    val energy_usage_total: Long,
    val origin_energy_usage: Long,
    val net_usage: Long
)

data class TronscanTokenInfo (
    val tokenId: String,
    val tokenAbbr: String,
    val tokenName: String,
    val tokenDecimal: Long,
    val tokenCanShow: Long,
    val tokenType: String,
    val tokenLogo: String,
    val tokenLevel: String,
    val vip: Boolean
)

data class TronscanTransaction (
    val block: Long,
    val hash: String,
    val timestamp: Long,
    val ownerAddress: String,
    val signature_addresses: List<String?>,
    val contractType: Long,
    val toAddress: String,
    val confirmations: Long,
    val confirmed: Boolean,
    val revert: Boolean,
    val contractRet: String,
    val contractData: TronscanContractData,
    val data: String,
    val cost: Map<String, Long>,
    val trigger_info: TronscanTransactionTriggerInfo,
    val internal_transactions: JsonNode,
    val fee_limit: Long,
    val srConfirmList: List<TronscanTransactionSrConfirmList>,
    val contract_type: String,
    val event_count: Long,
    val info: JsonNode,
    val contractInfo: Map<String, TronscanContractInfo>,
    val contract_map: Map<String, Boolean>
)

data class TronscanTransactionSrConfirmList (
    val address: String,
    val name: String,
    val block: Long,
    val url: String
)

data class TronscanTransactionTriggerInfo (
    val method: String,
    val parameter: TronscanTransactionParameter,
    val contract_address: String,
    val call_value: Long
)

data class TronscanTransactionParameter (
    val _number: String,
    val _direction: String
)

data class TronscanTransferResponse (
    val total: Long,
    val data: List<TronscanTransfer>,
    val contractMap: Map<String, Boolean>,
    val rangeTotal: Long,
    val contractInfo: JsonNode
)

data class TronscanTransfer (
    val id: String,
    val block: Long,
    val transactionHash: String,
    val timestamp: Long,
    val transferFromAddress: String,
    val transferToAddress: String,
    val amount: Long,
    val tokenName: String,
    val confirmed: Boolean,
    val data: String,
    val contractRet: String,
    val revert: Boolean,
    val tokenInfo: TronscanTokenInfo
)

data class TronscanNodeResponse (
    val total: Long,
    val code: Long,
    val data: List<TronscanNode>
)

data class TronscanNode (
    val country: String,
    val lng: Double,
    val province: String,
    val city: String,
    val ip: String,
    val lat: Double
)

data class TronscanFunds (
    val genesisBlockIssue: Long,
    val totalDonateBalance: Long,
    val totalFundBalance: Long,
    val totalBlockPay: Long,
    val totalNodePay: Long,
    val burnPerDay: Long,
    val burnByCharge: Double,
    val totalTurnOver: Long,
    val fundTrx: Double,
    val turnOver: Double
)