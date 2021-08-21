package dev.crash.etherscan

data class BscscanValidator(
    val validatorAddress: String,
    val validatorName: String,
    val validatorStatus: String,
    val validatorVotingPower: String,
    val validatorVotingPowerProportion: String
)