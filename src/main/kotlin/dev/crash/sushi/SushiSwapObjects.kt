package dev.crash.sushi

data class SushiSwapPair (
    val Pair_ID: String,
    val Token_1_contract: String,
    val Token_1_symbol: String,
    val Token_1_name: String,
    val Token_1_decimals: Long,
    val Token_1_price: Double,
    val Token_1_reserve: Double,
    val Token_1_derivedETH: Double,
    val Token_2_contract: String,
    val Token_2_symbol: String,
    val Token_2_name: String,
    val Token_2_decimals: Long,
    val Token_2_price: Double,
    val Token_2_reserve: Double,
    val Token_2_derivedETH: Double
)

data class SushiSwapToken (
    val Contract: String,
    val Symbol: String,
    val Name: String,
    val Decimals: Long
)

data class SushiSwapHistoricalLiquidity (
    val Date: Long,
    val Token_1_contract: String,
    val Token_2_contract: String,
    val Token_1_reserve: Double,
    val Token_2_reserve: Double,
    val USD_total_liquidity: Double
)

data class SushiSwapHistoricalVolume (
    val Date: Long,
    val Token_1_contract: String,
    val Token_2_contract: String,
    val Token_1_volume: Double,
    val Token_2_volume: Double,
    val USD_total_volume: Double
)

data class SushiSwapHistoricalTransactionCount (
    val Date: Long,
    val Token_1_contract: String,
    val Token_2_contract: String,
    val Transactions: Int
)

data class SushiSwapAPY (
    val date: Long,
    val apy: Double,
    val apr: Double
)

data class SushiSwapUserReward (
    val week: Int,
    val amount: Double
)

data class SushiSwapPairTransaction (
    val timestamp: Long,
    val side: String,
    val priceBase: Double,
    val priceUSD: Double,
    val volumeUSD: Double,
    val txHash: String,
    val receiver: String,
    val maker: String,
    val amountBase: Double,
    val amountQuote: Double,
    val tokenBase: String,
    val tokenQuote: String
)

data class SushiSwapUserTransaction (
    val timestamp: Long,
    val side: String,
    val pairID: String,
    val priceBase: Double,
    val priceUSD: Double,
    val volumeUSD: Double,
    val txHash: String,
    val receiver: String,
    val maker: String,
    val amountBase: Double,
    val amountQuote: Double,
    val tokenBase: String,
    val tokenQuote: String
)

data class SushiSwapBentoInfo (
    val contract: String,
    val number_of_users: Int,
    val number_of_kashi_pairs: Int,
    val number_of_tokens: Int
)

data class SushiSwapBentoAddressTransaction (
    val timestamp: Long,
    val block: Long,
    val txHash: String,
    val maker: String,
    val receiver: String,
    val token: String,
    val symbol: String,
    val amount: Long,
    val share: Double,
    val type: String
)

data class SushiSwapBentoTokenTransaction (
    val timestamp: Long,
    val block: Long,
    val txHash: String,
    val maker: String,
    val receiver: String,
    val symbol: String,
    val amount: Long,
    val share: Double,
    val type: String
)