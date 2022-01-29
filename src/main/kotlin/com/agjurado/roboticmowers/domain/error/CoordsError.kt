package com.agjurado.roboticmowers.domain.error

sealed interface CoordsError: DomainError

object InvalidRawCoordsAmount: CoordsError
object NotNumericCoords: CoordsError
