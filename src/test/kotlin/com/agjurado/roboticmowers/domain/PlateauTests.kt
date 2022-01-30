package com.agjurado.roboticmowers.domain

import arrow.core.Either
import com.agjurado.roboticmowers.domain.error.PlateauError
import io.kotest.assertions.arrow.either.shouldBeLeft
import io.kotest.assertions.arrow.either.shouldBeRight
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlateauTests {

    @Test
    fun `a plateau can be created if the upper right coords are correct`() {
        val validPlateau: Either<PlateauError, Plateau> = Plateau.createPlateauFrom(upperRightCoords = Coords(5,5))

        validPlateau.shouldBeRight()
    }

    @Test
    fun `a plateau can't be created if the upper right coords are invalid`() {
        val invalidPlateau: Either<PlateauError, Plateau> = Plateau.createPlateauFrom(upperRightCoords = Coords(-1,5))

        invalidPlateau.shouldBeLeft()
    }

    @Test
    fun `if the plateau is created without using the factory method and is invalid, an exception is raised`() {
        assertThrows<IllegalArgumentException> {
            Plateau(upperRightCoords = Coords(-1, -1))
        }
    }

    @Test
    fun `check if a location is outside the plateau`() {
        val plateau = Plateau(Coords(5, 5))

        plateau.isLocationOutsideOfPlateau(Location(Coords(-1, 2), East))
    }

}
