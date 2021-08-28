package dev.crash.tronscan

data class TronscanBlock (
    val hash: String,
    val confirmed: Boolean,
    val number: Long,
    val size: Long,
    val timestamp: Long,
    val parentHash: String,
    val witnessAddress: String,
    val nrOfTrx: Long,
    val txTrieRoot: String,
    val witnessId: Long
)