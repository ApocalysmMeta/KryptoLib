import de.crash.coingecko.CoingeckoAPI

fun main(){
    CoingeckoAPI.exchangeRates().forEach {
        println(it.key)
    }
}