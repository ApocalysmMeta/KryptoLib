
import dev.crash.address.Address
import dev.crash.address.AddressType
import dev.crash.address.getUncompressedPublicKey
import dev.crash.toHexString
import dev.crash.tx.RawTransaction

fun main(){
    println(getUncompressedPublicKey("f09728d6ef5a0eb677c83d3d2d827870c9ab8d129bc212c4cc1acd9a38c46a6d").toHexString())
    val address = Address("77a3819744076d647441d800692c5d4abaa439fe825081587d43a922a7fab5e2", "mrVt86e812Xp7bTkrSSwPV8zvKUn6Lcyfu", AddressType.BTC)
    RawTransaction(address, "muHiqexMYWwUm3Dnn8shFQLPJcQ3VWAkf3", 0)
}