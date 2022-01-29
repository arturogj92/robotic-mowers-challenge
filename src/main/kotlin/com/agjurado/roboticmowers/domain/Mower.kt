package com.agjurado.roboticmowers.domain

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.agjurado.roboticmowers.domain.error.InvalidInitialPosition
import com.agjurado.roboticmowers.domain.error.MowerError

data class Mower(
    private val plateau: Plateau,
    private val initialPosition: Location,
    private val instructions: List<MowerAction>,
) {

    companion object {
        fun create(
            plateau: Plateau,
            position: Location,
            instructions: List<MowerAction>,
        ): Either<MowerError, Mower> =
            if (!plateau.isValidLocation(position)) InvalidInitialPosition.left()
            else Mower(plateau, position, instructions).right()
    }
}
