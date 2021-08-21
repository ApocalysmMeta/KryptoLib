package dev.crash.address

import dev.crash.toHexString

object AddressGen {
    fun genAddress(type: AddressType): Address {
        try {
            return when (type) {
                AddressType.BTC -> genBTCAddress()
                AddressType.BCH -> genBCHAddress()
                AddressType.ETH -> genEthAddress()
                AddressType.TRX -> genTronAddress()
                AddressType.LTC -> genLitecoinAddress()
                AddressType.DASH -> genDashAddress()
                AddressType.DOGE -> genDogeAddress()
                AddressType.DGB -> genDigibyteAddress()
                AddressType.RDD -> genReddAddress()
            }
        }catch (ex: IllegalArgumentException) {
            return genAddress(type)
        }
    }

    private fun genBCHAddress() : Address = genBTCAddress(AddressType.BCH)

    private fun genDogeAddress() : Address = genBTCAddress( AddressType.DOGE,0x1E)

    private fun genDigibyteAddress() : Address = genBTCAddress(AddressType.DGB, 0x1E)

    private fun genReddAddress() : Address = genBTCAddress(AddressType.RDD,0x3d)

    private fun genLitecoinAddress() : Address = genBTCAddress(AddressType.LTC,0x30)

    private fun genDashAddress() : Address = genBTCAddress(AddressType.DASH,0x4c)

    private fun genEthAddress() : Address {
        val keypair = genECDSAKeyPair()
        val privateKey = keypair.private.getPrivateKeyString()
        val publicKey = keypair.public.getPublicKeyBytes()
        val keccak = publicKey.keccak256()
        val last20bytes = keccak.copyOfRange(keccak.size - 20, keccak.size)
        val address = "0x${last20bytes.toHexString()}"
        return Address(privateKey, address, AddressType.ETH)
    }

    private fun genTronAddress() : Address {
        val keypair = genECDSAKeyPair()
        val privateKey = keypair.private.getPrivateKeyString()
        val publicKey = keypair.public.getPublicKeyBytes()
        val keccak = publicKey.keccak256()
        val last20bytes = keccak.copyOfRange(keccak.size - 20, keccak.size)
        val withVersion = getWithVersion(last20bytes, 0x41)
        val address = (getaddedChecksum(withVersion, withVersion.checksum())).base58()
        return Address(privateKey, address, AddressType.TRX)
    }

    private fun genBTCAddress(type: AddressType = AddressType.BTC, version: Byte = 0x00) : Address {
        val keypair = genECDSAKeyPair()
        val privateKey = keypair.private.getPrivateKeyString()
        val publicKey = keypair.public.getPublicKeyBytes()
        val publicKeyWithVersion = getWithVersion(publicKey.sha256().ripemd160(), version)
        val address = (getaddedChecksum(publicKeyWithVersion, publicKeyWithVersion.checksum())).base58()
        return Address(privateKey, address, type)
    }

    fun getBTCTestnetAddress() : Address = genBTCAddress(AddressType.BTC, 0x6f)
}