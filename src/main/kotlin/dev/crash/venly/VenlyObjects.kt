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

data class VenlyNFTMetadata (
    val name: String,
    val description: String,
    val image: String,
    val imagePreview: String,
    val imageThumbnail: String,
    val backgroundColor: String,
    val background_color: String,
    val externalUrl: String,
    val external_url: String,
    val animationUrls: List<VenlyMediaObj>,
    val attributes: List<VenlyMetadataAttribute>,
    val contract: VenlyMetadataContract,
    val asset_contract: VenlyMetadataContract,
    val fungible: Boolean
)

data class VenlyMetadataContract (
    val address: String,
    val name: String,
    val symbol: String,
    val image: String,
    val imageUrl: String,
    val image_url: String,
    val description: String,
    val externalLink: String,
    val external_link: String,
    val externalUrl: String,
    val external_url: String,
    val media: List<VenlyMediaObj>,
    val type: String
)

data class VenlyMetadataAttribute (
    val type: String,
    val name: String,
    val value: String,
    val traitType: String,
    val trait_type: String
)

data class VenlyUpdateNFTMetadataBody (
    val name: String,
    val description: String,
    val image: String,
    val backgroundColor: String,
    val externalUrl: String,
    val animationUrls: List<VenlyMediaObj>,
    val attributes: List<VenlyNFTAttribute>
)

data class VenlyNFTMintBody(
    val typeId: Int,
    val destinations: List<String>
)

data class VenlyNFTMintFungibleBody(
    val destinations: List<String>,
    val amounts: List<Long>
)

data class VenlyNFTMint (
    val transactionHash: String,
    val metadata: VenlyNFTMetadata,
    val destinations: List<String>,
    val tokenIds: List<Long>
)

data class VenlyNFTByTemplate (
    val id: Long,
    val typeId: Long,
    val metadata: VenlyNFTMetadata,
    val mineDate: String,
    val confirmed: Boolean,
    val amount: Long,
    val transactionHash: String
)