package dev.crash.etherscan

class Etherscan(API_KEY: String) : EtherParent("https://api.etherscan.io/api", API_KEY)

class EtherscanGoerli(API_KEY: String) : EtherParent("https://api-goerli.etherscan.io/api", API_KEY)

class EtherscanKovan(API_KEY: String) : EtherParent("https://api-kovan.etherscan.io/api", API_KEY)

class EtherscanRinkeby(API_KEY: String) : EtherParent("https://api-rinkeby.etherscan.io/api", API_KEY)

class EtherscanRopsten(API_KEY: String) : EtherParent("https://api-ropsten.etherscan.io/api", API_KEY)