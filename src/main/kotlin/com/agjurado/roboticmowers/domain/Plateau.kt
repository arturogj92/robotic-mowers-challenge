package com.agjurado.roboticmowers.domain

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.agjurado.roboticmowers.domain.error.ExceedsPlateauLimit
import com.agjurado.roboticmowers.domain.error.PlateauError

data class Plateau(private val upperRightCoords: Coords) {
    private val bottomLeftCoords = Coords(LOWER_X, LOWER_Y)

    init {
        require(validatePlateauLimits(upperRightCoords).isRight()) { "The plateau's size is not valid" }
    }

    fun isValidLocation(position: Location) =
        when {
            position.coords.x > upperRightCoords.x -> false
            position.coords.y > upperRightCoords.y -> false
            position.coords.x < bottomLeftCoords.x -> false
            position.coords.y < bottomLeftCoords.y -> false
            else -> true
        }

    companion object {
        fun createPlateauFrom(upperRightCoords: Coords) =
            validatePlateauLimits(upperRightCoords)
                .map(::Plateau)

        private fun validatePlateauLimits(coords: Coords): Either<PlateauError, Coords> =
            if (coords.x <= LOWER_X || coords.y <= LOWER_Y) ExceedsPlateauLimit.left()
            else coords.right()

        private const val LOWER_X = 0
        private const val LOWER_Y = 0
    }
}
