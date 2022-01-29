package com.agjurado.roboticmowers.domain

import arrow.core.Either
import com.agjurado.roboticmowers.domain.error.MowerError
import io.kotest.assertions.arrow.either.shouldBeLeft
import io.kotest.assertions.arrow.either.shouldBeRight
import org.junit.jupiter.api.Test

class MowerTests {

    @Test
    fun `a mower can't be deployed outside the plateau`() {

        val mower: Either<MowerError, Mower> = Mower.create(
            plateau = Plateau(xSize = Size(5), ySize = Size(5)),
            position = Location(x = 6, y = 3, Direction.NORTH),
            instructions = listOf(Left, Right, Move, Move)
        )

        mower.shouldBeLeft()
    }

    @Test
    fun `a mower is deployed inside the plateau`() {

        val mower: Either<MowerError, Mower> = Mower.create(
            plateau = Plateau(xSize = Size(5), ySize = Size(5)),
            position = Location(x = 0, y = 0, Direction.NORTH),
            instructions = listOf(Left, Right, Move, Move)
        )

        mower.shouldBeRight()
    }

}
