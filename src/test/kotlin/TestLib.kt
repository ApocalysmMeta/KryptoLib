import dev.crash.etherscan.EtherscanClient
import java.math.BigDecimal

fun main(){
    println(EtherscanClient("").getAccountBalance("0xde0b295669a9fd93d5f28d9ec85e40f4cb697bae").divide(BigDecimal("1000000000000000000")).toDouble())
}