package com.agjurado.roboticmowers.domain

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.agjurado.roboticmowers.domain.error.CoordsError
import com.agjurado.roboticmowers.domain.error.InvalidRawCoordsAmount
import com.agjurado.roboticmowers.domain.error.NotNumericCoords
import com.agjurado.roboticmowers.shared.languageextensions.removeWhiteSpaces

data class Coords(val x: Int, val y: Int) {

    fun getAboveCoords() = Coords(x, y + 1)
    fun getBelowCoords() = Coords(x, y - 1)
    fun getRightCoords() = Coords(x + 1, y)
    fun getLeftCoords() = Coords(x - 1, y)


    companion object {
        fun parseRaw(rawCoordsSeparatedBySpaces: String): Either<CoordsError, Coords> =
            validate(rawCoordsSeparatedBySpaces)

        private fun validate(rawCoordsSeparatedBySpaces: String): Either<CoordsError, Coords> =
            when {
                rawCoordsSeparatedBySpaces.split(" ").size != 2 -> InvalidRawCoordsAmount.left()
                coordsAreNotNumeric(rawCoordsSeparatedBySpaces) -> NotNumericCoords.left()
                else -> rawCoordsSeparatedBySpaces.removeWhiteSpaces().let { Coords(it[0].digitToInt(), it[1].digitToInt()) }.right()
            }

        private fun coordsAreNotNumeric(rawCoordsSeparatedBySpaces: String) = rawCoordsSeparatedBySpaces.removeWhiteSpaces().any { !it.isDigit() }

    }

}

