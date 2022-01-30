package com.agjurado.roboticmowers.domain

import arrow.core.left
import arrow.core.right
import com.agjurado.roboticmowers.domain.error.DirectionMustBeALetter
import com.agjurado.roboticmowers.domain.error.UnknownDirection

sealed interface Direction {
    fun turnLeft(): Direction
    fun turnRight(): Direction

    companion object {
        fun parseDirection(rawDirection: Char) =
            when {
                !rawDirection.isLetter() -> DirectionMustBeALetter.left()
                !isKnownDirection(rawDirection) -> UnknownDirection.left()
                else -> mapDirection(rawDirection).right()
            }

        private fun mapDirection(rawDirection: Char): Direction =
            when (rawDirection) {
                NORTH -> North
                EAST -> East
                SOUTH -> South
                WEST -> West
                else -> throw IllegalStateException("Unknown direction")
            }

        private fun isKnownDirection(rawDirection: Char) =
            when (rawDirection) {
                NORTH -> true
                EAST -> true
                SOUTH -> true
                WEST -> true
                else -> false
            }

        private const val NORTH = 'N'
        private const val EAST = 'E'
        private const val SOUTH = 'S'
        private const val WEST = 'W'
    }
}

object North : Direction {
    override fun turnLeft() = West
    override fun turnRight() = East
}

object West : Direction {
    override fun turnLeft() = South
    override fun turnRight() = North
}

object South : Direction {
    override fun turnLeft() = East
    override fun turnRight() = West

}

object East : Direction {
    override fun turnLeft() = North
    override fun turnRight() = South
}
