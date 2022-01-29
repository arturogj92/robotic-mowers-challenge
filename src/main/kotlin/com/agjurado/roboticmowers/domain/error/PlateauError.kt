package com.agjurado.roboticmowers.domain.error

sealed interface PlateauError: DomainError

object ExceedsPlateauLimit: PlateauError
