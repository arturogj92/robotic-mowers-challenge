package com.agjurado.roboticmowers.domain

import arrow.core.Either
import com.agjurado.roboticmowers.domain.error.MowerError
import io.kotest.assertions.arrow.either.shouldBeLeft
import io.kotest.assertions.arrow.either.shouldBeRight
import org.hamcrest.Matchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test

class MowerTests {

    @Test
    fun `a mower can't be deployed outside the plateau`() {
        val aValidMower: Either<MowerError, Mower> = Mower.create(
            plateau = Plateau(xSize = Size(5), ySize = Size(5)),
            initialLocation = Location(Coords(6, 3), North),
            instructions = listOf(Left, Right, Move, Move)
        )

        aValidMower.shouldBeLeft()
    }

    @Test
    fun `a mower is deployed inside the plateau`() {
        val mowerWithInvalidPositions: Either<MowerError, Mower> = Mower.create(
            plateau = Plateau(xSize = Size(5), ySize = Size(5)),
            initialLocation = Location(Coords(0, 0), North),
            instructions = listOf(Left, Right, Move, Move)
        )

        mowerWithInvalidPositions.shouldBeRight()
    }

    @Test
    fun `the mower will finish in a location inside the plateau after processing the instructions`() {
        val mower = Mower(
            plateau = Plateau(xSize = Size(5), ySize = Size(5)),
            initialLocation = Location(Coords(1, 2), North),
            instructions = listOf(Left, Move, Left, Move, Left, Move, Left, Move, Move)
        )

        mower.processInstructions()

        val result = mower.getCurrentLocation()

        val expectedEndLocation = Location(Coords(1, 3), North)

        assertThat(result, `is`(expectedEndLocation))
    }

}
