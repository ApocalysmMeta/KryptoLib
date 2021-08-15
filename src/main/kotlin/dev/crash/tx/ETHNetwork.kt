package dev.crash.tx

enum class ETHNetwork(val chainId: Int) {
    MAINNET(1),
    ROPSTEN(3),
    RINKEBY(4),
    GOERLI(5),
    KOVAN(42),
    SMART_CHAIN(56),
    CLASSIC(61),
    CLASSIC_TESTNET(62),
    SMART_CHAIN_TESTNET(97)
}