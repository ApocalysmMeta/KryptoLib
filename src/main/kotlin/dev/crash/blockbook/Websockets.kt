package dev.crash.blockbook

class DigiExplorerWebsocket : BlockBookWebsocket("wss://digiexplorer.info/websocket")

class BTCBlockBookWebsocket : BlockBookWebsocket("wss://btc1.trezor.io/websocket")

class BCHBlockBookWebsocket : BlockBookWebsocket("wss://bch1.trezor.io/websocket")

class LTCBlockBookWebsocket : BlockBookWebsocket("wss://ltc1.trezor.io/websocket")

class DASHBlockBookWebsocket : BlockBookWebsocket("wss://dash1.trezor.io/websocket")

class DOGEBlockBookWebsocket : BlockBookWebsocket("wss://doge1.trezor.io/websocket")