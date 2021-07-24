
import dev.crash.post
import java.net.URL

fun main(){
    println(URL("https://dogechain.info/api/v1/pushtx").post())
}