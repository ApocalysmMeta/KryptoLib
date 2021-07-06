package de.crash.coingecko

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import de.crash.get
import de.crash.joinToNoSpaceString
import java.net.URL
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*

object CoingeckoAPI {
    private const val baseURL = "https://api.coingecko.com/api/v3"

    fun ping(): String {
        val response = URL("$baseURL/ping").get()
        return jacksonObjectMapper().readTree(response)["gecko_says"].asText()
    }

    fun simplePrice(ids: List<String>, vs_currencies: List<String> = listOf("usd"), includeMarketCap: Boolean = false, includeDayVolume: Boolean = false
                    , includeDayChange: Boolean = false, includeLastUpdated: Boolean = false): Map<String, JsonNode> {
        val response = URL("$baseURL/simple/price?ids=${ids.joinToNoSpaceString()}" +
                "&vs_currencies=${vs_currencies.joinToNoSpaceString()}&include_market_cap=$includeMarketCap" +
                "&include_24hr_vol=$includeDayVolume&include_24hr_change=$includeDayChange&include_last_updated_at=$includeLastUpdated").get()
        return jacksonObjectMapper().readValue<CoingeckoSimplePriceResponse>(response)
    }

    fun simpleTokenPrice(assetPlatform: String, contractAdresses: List<String>, vs_currencies: List<String> = listOf("usd"), includeMarketCap: Boolean = false
                         , includeDayVolume: Boolean = false, includeDayChange: Boolean = false, includeLastUpdated: Boolean = false): Map<String, JsonNode> {
        val response = URL("$baseURL/simple/token_price/$assetPlatform?contract_addresses=${contractAdresses.joinToNoSpaceString()}" +
                "&vs_currencies=${vs_currencies.joinToNoSpaceString()}&include_market_cap=$includeMarketCap" +
                "&include_24hr_vol=$includeDayVolume&include_24hr_change=$includeDayChange&include_last_updated_at=$includeLastUpdated").get()
        return jacksonObjectMapper().readValue<CoingeckoSimplePriceResponse>(response)
    }

    fun supportedVsCurrencies(): List<String> {
        val response = URL("$baseURL/simple/supported_vs_currencies").get()
        return jacksonObjectMapper().readValue<CoingeckoSupportedVsCurrenciesResponse>(response)
    }

    fun listCoins(includePlatform: Boolean = false): List<CoingeckoCoinsListObj>{
        val response = URL("$baseURL/coins/list?include_platform=$includePlatform").get()
        return jacksonObjectMapper().readValue<CoingeckoCoinsListResponse>(response)
    }

    fun assetPlatforms(): List<CoingeckoAssetPlatformObj> {
        val response = URL("$baseURL/asset_platforms").get()
        return jacksonObjectMapper().readValue<CoingeckoAssetPlatformsResponse>(response).toList()
    }

    fun exchanges(): List<CoingeckoExchange> {
        val response = URL("$baseURL/exchanges").get()
        return jacksonObjectMapper().readValue<CoingeckoExchangesResponse>(response)
    }

    fun exchangesList(): List<CoingeckoIdNameObj> {
        val response = URL("$baseURL/exchanges/list").get()
        return jacksonObjectMapper().readValue<CoingeckoDerivativesExchangesListResponse>(response)
    }

    fun exchange(exchangeId: String): CoingeckoExchangeResponse {
        val response = URL("$baseURL/exchanges/$exchangeId").get()
        return jacksonObjectMapper().readValue(response)
    }

    fun financePlatforms(): List<CoingeckoFinancePlatform> {
        val response = URL("$baseURL/finance_platforms").get()
        return jacksonObjectMapper().readValue<CoingeckoFinancePlatformsResponse>(response)
    }

    fun financeProducts(): List<CoingeckoFinanceProduct> {
        val response = URL("$baseURL/finance_products").get()
        return jacksonObjectMapper().readValue<CoingeckoFinanceProductsResponse>(response)
    }

    fun indexes(): List<CoingeckoIndexesObj> {
        val response = URL("$baseURL/indexes").get()
        return jacksonObjectMapper().readValue<CoingeckoIndexesResponse>(response)
    }

    fun indexes(marketId: String, indexId: String): CoingeckoIndexesObj {
        val response = URL("$baseURL/indexes/$marketId/$indexId").get()
        return jacksonObjectMapper().readValue(response)
    }

    fun indexesList(): List<CoingeckoIdNameObj> {
        val response = URL("$baseURL/indexes/list").get()
        return jacksonObjectMapper().readValue<CoingeckoDerivativesExchangesListResponse>(response)
    }

    fun derivatives(): List<CoingeckoDerivative> {
        val response = URL("$baseURL/derivatives").get()
        return jacksonObjectMapper().readValue<CoingeckoDerivativesResponse>(response)
    }

    fun derivativeExchanges(): List<CoingeckoDerivativesExchange> {
        val response = URL("$baseURL/derivatives/exchanges").get()
        return jacksonObjectMapper().readValue<CoingeckoDerivativesExchangesResponse>(response)
    }

    fun derivativeExchange(exchangeId: String, includeTickers: String? = null): CoingeckoDerivativesExchangeResponse {
        val response = URL("$baseURL/derivatives/exchanges/$exchangeId?${includeTickers ?: ""}").get()
        return jacksonObjectMapper().readValue(response)
    }

    fun derivativesExchangesList(): List<CoingeckoIdNameObj> {
        val response = URL("$baseURL/derivatives/exchanges/list").get()
        return jacksonObjectMapper().readValue<CoingeckoDerivativesExchangesListResponse>(response)
    }

    fun statusUpdates(): List<CoingeckoStatusUpdate> {
        val response = URL("$baseURL/status_updates").get()
        return jacksonObjectMapper().readValue<CoingeckoStatusUpdatesResponse>(response).status_updates
    }

    fun events(page: Int = 1, upcomingEventsOnly: Boolean = true): List<CoingeckoEvent> {
        val response = URL("$baseURL/events?page=$page&upcoming_events_only=$upcomingEventsOnly").get()
        return jacksonObjectMapper().readValue<CoingeckoEventsResponse>(response).data
    }

    fun eventsByType(type: String, page: Int = 1, upcomingEventsOnly: Boolean = true): List<CoingeckoEvent> {
        val response = URL("$baseURL/events?page=$page&upcoming_events_only=$upcomingEventsOnly&type=$type").get()
        return jacksonObjectMapper().readValue<CoingeckoEventsResponse>(response).data
    }

    fun eventsByCountry(countryCode: String, page: Int = 1, upcomingEventsOnly: Boolean = true): List<CoingeckoEvent> {
        val response = URL("$baseURL/events?page=$page&upcoming_events_only=$upcomingEventsOnly&country_code=$countryCode").get()
        return jacksonObjectMapper().readValue<CoingeckoEventsResponse>(response).data
    }

    fun eventsByCountryAndType(countryCode: String, type: String, page: Int = 1, upcomingEventsOnly: Boolean = true): List<CoingeckoEvent> {
        val response = URL("$baseURL/events?page=$page&upcoming_events_only=$upcomingEventsOnly&country_code=$countryCode&type=$type").get()
        return jacksonObjectMapper().readValue<CoingeckoEventsResponse>(response).data
    }

    fun eventsByDate(fromDate: Date, toDate: Date, page: Int = 1, upcomingEventsOnly: Boolean = true): List<CoingeckoEvent> {
        return eventsByDate(fromDate.toInstant(), toDate.toInstant(), page, upcomingEventsOnly)
    }

    fun eventsByDate(fromDate: Instant, toDate: Instant, page: Int = 1, upcomingEventsOnly: Boolean = true): List<CoingeckoEvent> {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return eventsByDate(formatter.format(fromDate), formatter.format(toDate), page, upcomingEventsOnly)
    }

    fun eventsByDate(fromDate: String, toDate: String, page: Int = 1, upcomingEventsOnly: Boolean = true): List<CoingeckoEvent> {
        val response = URL("$baseURL/events?page=$page&upcoming_events_only=$upcomingEventsOnly&from_date=$fromDate&to_date=$toDate").get()
        return jacksonObjectMapper().readValue<CoingeckoEventsResponse>(response).data
    }

    fun eventCountries(): List<CoingeckoEventCountry> {
        val response = URL("$baseURL/events/countries").get()
        return jacksonObjectMapper().readValue<CoingeckoEventCountriesResponse>(response).data
    }

    fun eventTypes(): List<String> {
        val response = URL("$baseURL/events/types").get()
        return jacksonObjectMapper().readValue<CoingeckoEventTypesResponse>(response).data
    }

    fun exchangeRates(): Map<String, CoingeckoExchangeRate> {
        val response = URL("$baseURL/exchange_rates").get()
        return jacksonObjectMapper().readValue<CoingeckoExchangeRatesResponse>(response).rates
    }

    fun searchTrending(): CoingeckoSearchTrendingResponse {
        val response = URL("$baseURL/search/trending").get()
        return jacksonObjectMapper().readValue(response)
    }

    fun global(): CoingeckoGlobalData {
        val response = URL("$baseURL/global").get()
        return jacksonObjectMapper().readValue<CoingeckoGlobalDataResponse>(response).data
    }

    fun globalDefi(): CoingeckoGlobalDefiData {
        val response = URL("$baseURL/global/decentralized_finance_defi").get()
        return jacksonObjectMapper().readValue<CoingeckoGlobalDefiResponse>(response).data
    }

    //Just bitcoin or ethereum
    fun companies(coinId: String): CoingeckoCompaniesPublicTreasuryResponse {
        val response = URL("$baseURL/companies/public_treasury/$coinId").get()
        return jacksonObjectMapper().readValue(response)
    }
}