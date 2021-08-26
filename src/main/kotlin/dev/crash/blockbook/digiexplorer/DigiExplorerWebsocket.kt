package dev.crash.blockbook.digiexplorer

import dev.crash.blockbook.BlockBookWebsocket

class DigiExplorerWebsocket : BlockBookWebsocket("wss://digiexplorer.info/websocket") {
    init {
        connect()
    }
}