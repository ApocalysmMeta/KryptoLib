package dev.crash.venly

data class VenlyMediaObj(val type: String, val value: String)

data class VenlyCreateContractBody(val name: String, val description: String, val chain: String, val symbol: String, val image: String,
                                   val externalUrl: String, val media: List<VenlyMediaObj>)

data class VenlyContractInfo(val name: String, val description: String, val confirmed: Boolean, val id: Int,
                             val chain: String, val symbol: String, val image: String, val externalUrl: String, val media: List<VenlyMediaObj>,
                             val transactionHash: String)