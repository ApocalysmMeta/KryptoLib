package dev.crash.coingecko

import com.fasterxml.jackson.databind.JsonNode

class CoingeckoSimplePriceResponse(elements: Map<String, JsonNode>) : HashMap<String, JsonNode>(elements)

class CoingeckoSupportedVsCurrenciesResponse(elements: Collection<String>) : ArrayList<String>(elements)

class CoingeckoCoinsListResponse(elements: Collection<CoingeckoCoinsListObj>) : ArrayList<CoingeckoCoinsListObj>(elements)

data class CoingeckoCoinsListObj (
    val id: String,
    val symbol: String,
    val name: String,
    val platforms: Map<String, String?>
)

data class CoingeckoCoinResponse (
    val id: String,
    val symbol: String,
    val name: String,
    val asset_platform_id: String?,
    val platforms: JsonNode,
    val block_time_in_minutes: Long,
    val hashing_algorithm: String,
    val categories: List<String>,
    val public_notice: String?,
    val additional_notices: List<String?>,
    val localization: JsonNode?,
    val description: JsonNode,
    val links: CoingeckoCoinLinks,
    val image: CoingeckoCoinImage,
    val country_origin: String,
    val genesis_date: String,
    val sentiment_votes_up_percentage: Double,
    val sentiment_votes_down_percentage: Double,
    val market_cap_rank: Long,
    val coingecko_rank: Long,
    val coingecko_score: Double,
    val developer_score: Double,
    val community_score: Double,
    val liquidity_score: Double,
    val public_interest_score: Double,
    val market_data: CoingeckoCoinMarketData?,
    val community_data: CoingeckoCoinCommunityData?,
    val developer_data: CoingeckoCoinDeveloperData?,
    val public_interest_stats: CoingeckoCoinPublicInterestStats?,
    val status_updates: List<Any?>,
    val last_updated: String,
    val tickers: List<CoingeckoCoinTicker?>
)

data class CoingeckoCoinCommunityData (
    val facebook_likes: Long?,
    val twitter_followers: Long?,
    val reddit_average_posts_48h: Double?,
    val reddit_average_comments_48h: Double?,
    val reddit_subscribers: Long?,
    val reddit_accounts_active_48h: Long?,
    val telegram_channel_user_count: Long?
)

data class CoingeckoCoinDeveloperData (
    val forks: Long,
    val stars: Long,
    val subscribers: Long,
    val total_issues: Long,
    val closed_issues: Long,
    val pull_requests_merged: Long,
    val pull_request_contributors: Long,
    val code_additions_deletions_4_weeks: CoingeckoCoinCodeAdditionsDeletions4Weeks,
    val commit_count_4_weeks: Long,
    val last_4_weeks_commit_activity_series: List<Long>
)

data class CoingeckoCoinCodeAdditionsDeletions4Weeks (
    val additions: Long,
    val deletions: Long
)

data class CoingeckoCoinImage (
    val thumb: String,
    val small: String,
    val large: String
)

data class CoingeckoCoinLinks (
    val homepage: List<String?>,
    val blockchain_site: List<String?>,
    val official_forum_url: List<String?>,
    val chat_url: List<String?>,
    val announcement_url: List<String?>,
    val twitter_screen_name: String?,
    val facebook_username: String?,
    val bitcointalk_thread_identifier: String?,
    val telegram_channel_identifier: String?,
    val subreddit_url: String?,
    val repos_url: CoingeckoCoinReposURL
)

data class CoingeckoCoinReposURL (
    val github: List<String?>,
    val bitbucket: List<String?>
)

data class CoingeckoCoinMarketData (
    val current_price: Map<String, Double>,
    val total_value_locked: JsonNode?,
    val mcap_to_tvl_ratio: JsonNode?,
    val fdv_to_tvl_ratio: JsonNode?,
    val roi: JsonNode?,
    val ath: Map<String, Double>,
    val ath_change_percentage: Map<String, Double>,
    val ath_date: Map<String, String>,
    val atl: Map<String, Double>,
    val atl_change_percentage: Map<String, Double>,
    val atl_date: Map<String, String>,
    val market_cap: Map<String, Double>,
    val market_cap_rank: Long,
    val fully_diluted_valuation: Map<String, Double>,
    val total_volume: Map<String, Double>,
    val high_24h: Map<String, Double>,
    val low_24h: Map<String, Double>,
    val price_change_24h: Double,
    val price_change_percentage_24h: Double,
    val price_change_percentage_7d: Double,
    val price_change_percentage_14d: Double,
    val price_change_percentage_30d: Double,
    val price_change_percentage_60d: Double,
    val price_change_percentage_200d: Double,
    val price_change_percentage_1y: Double,
    val market_cap_change_24h: Long,
    val market_cap_change_percentage_24h: Double,
    val price_change_24h_in_currency: Map<String, Double>,
    val price_change_percentage_1h_in_currency: Map<String, Double>,
    val price_change_percentage_24h_in_currency: Map<String, Double>,
    val price_change_percentage_7d_in_currency: Map<String, Double>,
    val price_change_percentage_14d_in_currency: Map<String, Double>,
    val price_change_percentage_30d_in_currency: Map<String, Double>,
    val price_change_percentage_60d_in_currency: Map<String, Double>,
    val price_change_percentage_200d_in_currency: Map<String, Double>,
    val price_change_percentage_1y_in_currency: Map<String, Double>,
    val market_cap_change_24h_in_currency: Map<String, Double>,
    val market_cap_change_percentage_24h_in_currency: Map<String, Double>,
    val total_supply: Long,
    val max_supply: Long,
    val circulating_supply: Long,
    val sparkline_7d: CoingeckoCoinSparkline7D,
    val last_updated: String
)

data class CoingeckoCoinSparkline7D (
    val price: List<Double>
)

data class CoingeckoCoinPublicInterestStats (
    val alexa_rank: Long,
    val bing_matches: JsonNode?
)

data class CoingeckoCoinTicker (
    val base: String,
    val target: String,
    val market: CoingeckoCoinMarket,
    val last: Double,
    val volume: Double,
    val converted_last: Map<String, Double>,
    val converted_volume: Map<String, Double>,
    val trust_score: String,
    val bid_ask_spread_percentage: Double,
    val timestamp: String,
    val last_traded_at: String,
    val last_fetch_at: String,
    val is_anomaly: Boolean,
    val is_stale: Boolean,
    val trade_url: String?,
    val token_info_url: Any?,
    val coin_id: String,
    val target_coin_id: String?
)

data class CoingeckoCoinMarket (
    val name: String,
    val identifier: String,
    val has_trading_incentive: Boolean
)

class CoingeckoAssetPlatformsResponse(elements: Collection<CoingeckoAssetPlatformObj>) : ArrayList<CoingeckoAssetPlatformObj>(elements)

data class CoingeckoAssetPlatformObj (
    val id: String,
    val chain_identifier: Long?,
    val name: String,
    val shortname: String
)

class CoingeckoCoinsCategoryListResponse(elements: Collection<CoingeckoCoinsCategoryListObj>) : ArrayList<CoingeckoCoinsCategoryListObj>(elements)

data class CoingeckoCoinsCategoryListObj (
    val category_id: String,
    val name: String
)

class CoingeckoCoinsCategoriesResponse(elements: Collection<CoingeckoCoinsCatogory>) : ArrayList<CoingeckoCoinsCatogory>(elements)

data class CoingeckoCoinsCatogory (
    val id: String,
    val name: String,
    val market_cap: Double,
    val market_cap_change_24h: Double,
    val volume_24h: Double,
    val updated_at: String
)


class CoingeckoExchangesResponse(elements: Collection<CoingeckoExchange>) : ArrayList<CoingeckoExchange>(elements)

data class CoingeckoExchange (
    val id: String,
    val name: String,
    val year_established: Long?,
    val country: String?,
    val description: String?,
    val url: String,
    val image: String,
    val has_trading_incentive: Boolean?,
    val trust_score: Long,
    val trust_score_rank: Long,
    val trade_volume_24h_btc: Double,
    val trade_volume_24h_btc_normalized: Double
)

data class CoingeckoExchangeResponse (
    val name: String,
    val year_established: Long,
    val country: String,
    val description: String,
    val url: String,
    val image: String,
    val facebook_url: String,
    val reddit_url: String,
    val telegram_url: String,
    val slack_url: String,
    val other_url_1: String,
    val other_url_2: String,
    val twitter_handle: String,
    val has_trading_incentive: Boolean,
    val centralized: Boolean,
    val public_notice: String,
    val alert_notice: String,
    val trust_score: Long,
    val trust_score_rank: Long,
    val trade_volume_24h_btc: Double,
    val trade_volume_24h_btc_normalized: Double,
    val tickers: List<CoingeckoExchangeTicker>,
    val status_updates: List<CoingeckoExchangeStatusUpdate>
)

data class CoingeckoExchangeStatusUpdate (
    val description: String,
    val category: String,
    val created_at: String,
    val user: String,
    val user_title: String,
    val pin: Boolean,
    val project: CoingeckoExchangeProject
)

data class CoingeckoExchangeProject (
    val type: String,
    val id: String,
    val name: String,
    val image: CoingeckoExchangeProjectImage
)

data class CoingeckoExchangeProjectImage (
    val thumb: String,
    val small: String,
    val large: String
)

data class CoingeckoExchangeTicker (
    val base: String,
    val target: String,
    val market: CoingeckoExchangeMarket,
    val last: Double,
    val volume: Double,
    val converted_last: Map<String, Double>,
    val converted_volume: Map<String, Double>,
    val trust_score: String,
    val bid_ask_spread_percentage: Double,
    val timestamp: String,
    val last_traded_at: String,
    val last_fetch_at: String,
    val is_anomaly: Boolean,
    val is_stale: Boolean,
    val trade_url: String,
    val token_info_url: String?,
    val coin_id: String?,
    val target_coin_id: String?
)

data class CoingeckoExchangeMarket (
    val name: String,
    val identifier: String,
    val has_trading_incentive: Boolean
)

data class CoingeckoExchangeTickersResponse(
    val name: String,
    val tickers: List<CoingeckoExchangeTicker>
)

class CoingeckoExchangeStatusUpdatesResponse(elements: Collection<CoingeckoExchangeStatusUpdate>) : ArrayList<CoingeckoExchangeStatusUpdate>(elements)

class CoingeckoExchangeVolumeChartResponse(elements: Collection<List<Any>>) : ArrayList<List<Any>>(elements)

class CoingeckoFinancePlatformsResponse(elements: Collection<CoingeckoFinancePlatform>) : ArrayList<CoingeckoFinancePlatform>(elements)

data class CoingeckoFinancePlatform (
    val name: String,
    val facts: String,
    val category: String,
    val centralized: Boolean,
    val website_url: String
)

class CoingeckoFinanceProductsResponse(elements: Collection<CoingeckoFinanceProduct>) : ArrayList<CoingeckoFinanceProduct>(elements)

data class CoingeckoFinanceProduct (
    val platform: String,
    val identifier: String,
    val supply_rate_percentage: String?,
    val borrow_rate_percentage: String?,
    val number_duration: Long?,
    val length_duration: Long?,
    val start_at: Long,
    val end_at: Long,
    val value_at: Long,
    val redeem_at: Long
)

class CoingeckoIndexesResponse(elements: Collection<CoingeckoIndexesObj>) : ArrayList<CoingeckoIndexesObj>(elements)

data class CoingeckoIndexesObj (
    val name: String,
    val id: String?,
    val market: String,
    val last: Double?,
    val is_multi_asset_composite: Boolean?
)


class CoingeckoDerivativesResponse(elements: Collection<CoingeckoDerivative>) : ArrayList<CoingeckoDerivative>(elements)

data class CoingeckoDerivative (
    val market: String,
    val symbol: String,
    val index_id: String,
    val price: String,
    val price_percentage_change_24h: Double,
    val contract_type: String,
    val index: Double?,
    val basis: Double,
    val spread: Double?,
    val funding_rate: Double,
    val open_interest: Double?,
    val volume_24h: Double,
    val last_traded_at: Long,
    val expired_at: Long?
)

class CoingeckoDerivativesExchangesResponse(elements: Collection<CoingeckoDerivativesExchange>) : ArrayList<CoingeckoDerivativesExchange>(elements)

data class CoingeckoDerivativesExchange (
    val name: String,
    val id: String,
    val open_interest_btc: Double?,
    val trade_volume_24h_btc: String,
    val number_of_perpetual_pairs: Long,
    val number_of_futures_pairs: Long,
    val image: String,
    val year_established: Long?,
    val country: String?,
    val description: String,
    val url: String
)

data class CoingeckoDerivativesExchangeResponse (
    val name: String,
    val open_interest_btc: Double,
    val trade_volume_24h_btc: String,
    val number_of_perpetual_pairs: Long,
    val number_of_futures_pairs: Long,
    val image: String,
    val year_established: Any?,
    val country: String,
    val description: String,
    val url: String,
    val tickers: List<CoingeckoDerivativesExchangeTicker>
)

data class CoingeckoDerivativesExchangeTicker (
    val symbol: String,
    val base: String,
    val target: String,
    val trade_url: String,
    val contract_type: String,
    val last: Double,
    val h24_percentage_change: Double,
    val index: Double?,
    val index_basis_percentage: Double,
    val bid_ask_spread: Double,
    val funding_rate: Double,
    val open_interest_usd: Double,
    val h24_volume: Double,
    val converted_volume: CoingeckoDerivativeExchangeConverted,
    val converted_last: CoingeckoDerivativeExchangeConverted,
    val last_traded: Long,
    val expired_at: Long?
)

data class CoingeckoDerivativeExchangeConverted (
    val btc: String,
    val eth: String,
    val usd: String
)

class CoingeckoDerivativesExchangesListResponse(elements: Collection<CoingeckoIdNameObj>) : ArrayList<CoingeckoIdNameObj>(elements)

data class CoingeckoIdNameObj (
    val id: String,
    val name: String
)

data class CoingeckoStatusUpdatesResponse (val status_updates: List<CoingeckoStatusUpdate>)

data class CoingeckoStatusUpdate (
    val description: String,
    val category: String,
    val created_at: String,
    val user: String?,
    val user_title: String,
    val pin: Boolean,
    val project: CoingeckoStatusProject
)

data class CoingeckoStatusProject (
    val type: String,
    val id: String,
    val name: String,
    val symbol: String?,
    val image: CoingeckoStatusProjectImage
)

data class CoingeckoStatusProjectImage (
    val thumb: String,
    val small: String,
    val large: String
)

data class CoingeckoEventsResponse (
    val data: List<CoingeckoEvent>,
    val count: Long,
    val page: Long
)

data class CoingeckoEvent (
    val type: String,
    val title: String,
    val description: String,
    val organizer: String,
    val start_date: String,
    val end_date: String,
    val website: String,
    val email: String,
    val venue: String,
    val address: String,
    val city: String,
    val country: String,
    val screenshot: String
)


data class CoingeckoEventCountriesResponse (
    val data: List<CoingeckoEventCountry>,
    val count: Long
)

data class CoingeckoEventCountry (
    val country: String?,
    val code: String
)

data class CoingeckoEventTypesResponse (
    val data: List<String>,
    val count: Long
)

data class CoingeckoExchangeRatesResponse (
    val rates: Map<String, CoingeckoExchangeRate>
)

data class CoingeckoExchangeRate (
    val name: String,
    val unit: String,
    val value: Double,
    val type: String
)

data class CoingeckoSearchTrendingResponse (
    val coins: List<CoingeckoSearchTrendingCoin>,
    val exchanges: List<Any?>
)

data class CoingeckoSearchTrendingCoin (
    val item: CoingeckoSearchTrendingItem
)

data class CoingeckoSearchTrendingItem (
    val id: String,
    val coin_id: Long,
    val name: String,
    val symbol: String,
    val market_cap_rank: Long,
    val thumb: String,
    val small: String,
    val large: String,
    val slug: String,
    val price_btc: Double,
    val score: Long
)

data class CoingeckoGlobalDataResponse (val data: CoingeckoGlobalData)

data class CoingeckoGlobalData (
    val active_cryptocurrencies: Long,
    val upcoming_icos: Long,
    val ongoing_icos: Long,
    val ended_icos: Long,
    val markets: Long,
    val total_market_cap: Map<String, Double>,
    val total_volume: Map<String, Double>,
    val market_cap_percentage: Map<String, Double>,
    val market_cap_change_percentage_24h_usd: Double,
    val updated_at: Long
)

data class CoingeckoGlobalDefiResponse (val data: CoingeckoGlobalDefiData)

data class CoingeckoGlobalDefiData (
    val defi_market_cap: String,
    val eth_market_cap: String,
    val defi_to_eth_ratio: String,
    val trading_volume_24h: String,
    val defi_dominance: String,
    val top_coin_name: String,
    val top_coin_defi_dominance: Double
)

data class CoingeckoCompaniesPublicTreasuryResponse (
    val total_holdings: Double,
    val total_value_usd: Double,
    val market_cap_dominance: Double,
    val companies: List<CoingeckoCompany>
)

data class CoingeckoCompany (
    val name: String,
    val symbol: String,
    val country: String,
    val total_holdings: Long,
    val total_entry_value_usd: Long,
    val total_current_value_usd: Long,
    val percentage_of_total_supply: Double
)
