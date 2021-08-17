package dev.crash.venly

data class VenlyMediaObj(val type: String, val value: String)

data class VenlyNFTAttribute(val type: String, val name: String, val value: String)

data class VenlyCreateContractBody(val name: String, val description: String, val chain: String, val symbol: String, val image: String,
                                   val externalUrl: String, val media: List<VenlyMediaObj>)

data class VenlyContractInfo(val name: String, val description: String, val confirmed: Boolean, val id: Int,
                             val chain: String, val symbol: String, val image: String, val externalUrl: String, val media: List<VenlyMediaObj>,
                             val transactionHash: String)

data class VenlyCreateNFTTemplateBody(val name: String, val description: String, val image: String, val externalUrl: String, val backgroudColor: String,
                                      val fungible: Boolean, val maxSupply: Long, val burnable: Boolean, val animationUrls: List<VenlyMediaObj>,
                                      val attributes: List<VenlyNFTAttribute>)

data class VenlyNFTTemplate(val name: String, val description: String, val confirmed: Boolean, val id: Int,
                            val image: String, val imageThumbnail: String, val imagePreview: String, val externalUrl: String, val backgroudColor: String,
                            val fungible: Boolean, val burnable: Boolean, val maxSupply: Long, val currentSupply: Long, val animationUrls: List<VenlyMediaObj>,
                            val attributes: List<VenlyNFTAttribute>, val transactionHash: String)