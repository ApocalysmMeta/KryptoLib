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
