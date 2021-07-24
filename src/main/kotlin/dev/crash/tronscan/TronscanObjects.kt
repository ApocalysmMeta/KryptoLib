package dev.crash.tronscan

import com.fasterxml.jackson.databind.JsonNode

data class TronscanSystemStatus (
    val database: TronscanSystemDatabase,
    val sync: TronscanSystemSync,
    val network: TronscanSystemNetwork,
    val full: TronscanSystemFull,
    val solidity: TronscanSystemFull
)

data class TronscanSystemDatabase (
    val block: Long,
    val confirmedBlock: Long
)

data class TronscanSystemFull (
    val block: Long
)

data class TronscanSystemNetwork (
    val type: String
)

data class TronscanSystemSync (
    val progress: Double
)

data class TronscanBlock (
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

data class TronscanAccountListObj (
    val total: Long,
    val data: List<TronscanAccountListData>,
    val contractMap: Map<String, Boolean>,
    val rangeTotal: Long,
    val contractInfo: Map<String, TronscanContractInfo>
)

data class TronscanContractInfo (
    val tag1: String,
    val tag1Url: String,
    val vip: Boolean
)

data class TronscanAccountListData (
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
    val tokenBalances: List<TronscanAccountTokenBalanceElement>,
    val delegateFrozenForEnergy: Long,
    val balances: List<TronscanAccountTokenBalanceElement>,
    val trc721token_balances: List<TronscanAccountTrc20TokenBalance>,
    val balance: Long,
    val voteTotal: Long,
    val totalFrozen: Long,
    val tokens: List<TronscanAccountTokenBalanceElement>,
    val delegated: JsonNode,
    val transactions_in: Long,
    val totalTransactionCount: Long,
    val representative: TronscanAccountRepresentative,
    val frozenForBandWidth: Long,
    val reward: Long,
    val addressTagLogo: String,
    val allowExchange: List<Any?>,
    val address: String,
    val frozen_supply: List<Any?>,
    val bandwidth: TronscanAccountBandwidth,
    val date_created: Long,
    val accountType: Long,
    val addressTag: String,
    val exchanges: List<Any?>,
    val frozen: TronscanAccountFrozen,
    val accountResource: TronscanAccountResource,
    val transactions: Long,
    val witness: Long,
    val delegateFrozenForBandWidth: Long,
    val name: String,
    val frozenForEnergy: Long,
    val acquiredDelegateFrozenForEnergy: Long,
    val activePermissions: List<TronscanAccountActivePermission>
)

data class TronscanAccountActivePermission (
    val operations: String,
    val keys: List<TronscanAccountPermissionKey>,
    val threshold: Long,
    val id: Long,
    val type: String,
    val permission_name: String
)

data class TronscanAccountPermissionKey (
    val address: String,
    val weight: Long
)

data class TronscanAccountResource (
    val frozen_balance_for_energy: Any?
)

data class TronscanAccountTokenBalanceElement (
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