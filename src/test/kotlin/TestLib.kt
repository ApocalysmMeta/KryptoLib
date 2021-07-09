import dev.crash.dogechain.DogechainInfoJsonAPI

fun main(){
    println(DogechainInfoJsonAPI.getBlock(120000).hash)
}