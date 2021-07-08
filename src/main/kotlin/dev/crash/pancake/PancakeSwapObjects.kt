package dev.crash.pancake

data class PancakeSwapTokensResponse (
    val updated_at: Long,
    val data: Map<String, TokenData>
)

data class PancakeSwapTokenResponse(
    val updated_at: Long,
    val data: TokenData
)

data class TokenData (
    val name: String,
    val symbol: String,
    val price: String,
    val price_BNB: String
)