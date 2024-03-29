package com.agjurado.roboticmowers.domain

import arrow.core.Either
import com.agjurado.roboticmowers.domain.error.MowerError
import io.kotest.assertions.arrow.either.shouldBeLeft
import io.kotest.assertions.arrow.either.shouldBeRight
import org.hamcrest.Matchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MowerTests {

    @Test
    fun `a mower can't be deployed outside the plateau`() {
        val anInvalidMower: Either<MowerError, Mower> = Mower.create(
            plateau = Plateau(upperRightCoords = Coords(5,5)),
            initialLocation = Location(Coords(6, 3), North),
            actions = listOf(Left, Right, Move, Move)
        )

        anInvalidMower.shouldBeLeft()
    }

    @Test
    fun `a mower is deployed inside the plateau`() {
        val aValidMower: Either<MowerError, Mower> = Mower.create(
            plateau = Plateau(upperRightCoords = Coords(5,5)),
            initialLocation = Location(Coords(0, 0), North),
            actions = listOf(Left, Right, Move, Move)
        )

        aValidMower.shouldBeRight()
    }

    @Test
    fun `if the mower is created without using the factory method and starts in an invalid location, an exception is raised`() {
        assertThrows<IllegalArgumentException> {
            Mower(
                plateau = Plateau(upperRightCoords = Coords(5,5)),
                initialLocation = Location(Coords(-1, 0), North),
                actions = listOf(Left, Right, Move, Move)
            )
        }
    }

    @Test
    fun `the mower will finish in a location inside the plateau after processing the actions`() {
        val mower = Mower(
            plateau = Plateau(upperRightCoords = Coords(5,5)),
            initialLocation = Location(Coords(1, 1), North),
            actions = listOf(Move, Move, Right)
        )

        mower.mow()

        val result = mower.getCurrentLocation()

        val expectedEndLocation = Location(Coords(1, 3), East)

        assertThat(result, `is`(expectedEndLocation))
    }

    @Test
    fun `if the mower goes out off the plateau, it will get stucked`() {
        val mower = Mower(
            plateau = Plateau(upperRightCoords = Coords(5,5)),
            initialLocation = Location(Coords(1, 2), North),
            actions = listOf(Left, Move, Move, Move)
        )

        assertThrows<IllegalStateException> {
            mower.mow()
        }
    }

}
