package com.agjurado.roboticmowers.domain

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.agjurado.roboticmowers.domain.error.CoordsError
import com.agjurado.roboticmowers.domain.error.InvalidRawCoordsAmount
import com.agjurado.roboticmowers.domain.error.NotNumericCoords

data class Coords(val x: Int, val y: Int) {

    fun getAboveCoords() = Coords(x, y + 1)
    fun getBelowCoords() = Coords(x, y - 1)
    fun getRightCoords() = Coords(x + 1, y)
    fun getLeftCoords() = Coords(x - 1, y)


    companion object {
        fun parseSeparatedBySpaces(rawCoords: String): Either<CoordsError, Coords> =
            validate(rawCoords)

        private fun validate(rawCoords: String): Either<CoordsError, Coords> =
            when {
                rawCoords.split(" ").size != 2 -> InvalidRawCoordsAmount.left()
                coordsAreNotNumeric(rawCoords) -> NotNumericCoords.left()
                else -> sanitize(rawCoords).let { Coords(it[0].digitToInt(), it[1].digitToInt()) }.right()
            }

        private fun coordsAreNotNumeric(rawCoords: String) = sanitize(rawCoords).any { !it.isDigit() }

        private fun sanitize(rawCoords: String) = rawCoords.filterNot { it.isWhitespace() }
    }

}

