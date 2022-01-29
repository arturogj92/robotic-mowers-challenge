package com.agjurado.roboticmowers.domain

import arrow.core.Either
import com.agjurado.roboticmowers.domain.error.PlateauError
import io.kotest.assertions.arrow.either.shouldBeLeft
import io.kotest.assertions.arrow.either.shouldBeRight
import org.junit.jupiter.api.Test

class PlateauTests {

    @Test
    fun `a plateau can be created if the upper right coords are correct`() {
        val plateau: Either<PlateauError, Plateau> = Plateau.createPlateauFrom(upperRightCoords = Coords(5,5))

        plateau.shouldBeRight()
    }

    @Test
    fun `a plateau can't be created if the upper right coords are invalid`() {
        val plateau: Either<PlateauError, Plateau> = Plateau.createPlateauFrom(upperRightCoords = Coords(-1,5))

        plateau.shouldBeLeft()
    }


}
