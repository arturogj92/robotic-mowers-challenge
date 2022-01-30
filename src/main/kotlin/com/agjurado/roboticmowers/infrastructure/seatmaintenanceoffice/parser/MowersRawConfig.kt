package com.agjurado.roboticmowers.infrastructure.seatmaintenanceoffice.parser

data class MowersRawConfig(
    val rawPlateauUpperRightCoords: RawCoord,
    val rawInitialMowerCoords: List<RawCoord>,
    val rawInitialMowerDirection: List<RawDirection>,
    val rawMowerActions: List<RawActions>,
) {
    val numberOfMowersToConfigure = rawInitialMowerCoords.size

    init {
        require(rawInitialMowerDirection.size == rawInitialMowerCoords.size) { "The seat maintenance office request is invalid" }
    }
}
