import dev.crash.venly.VenlyAPI

fun main(){
    val venly = VenlyAPI("3b613051-4e10-479d-a75a-ee355541e5a0", "82c19251-1753-44f5-ae76-93438d3628de")
    venly.retrieveAllContracts().forEach {
        println(it.name)
    }
}