package com.agjurado.roboticmowers.domain

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.agjurado.roboticmowers.domain.error.InvalidInitialPosition
import com.agjurado.roboticmowers.domain.error.MowerError

data class Mower(
    private val plateau: Plateau,
    private val initialLocation: Location,
    private val instructions: List<MowerAction>,
) {
    private var currentLocation = initialLocation

    init {
        require(validateInitialLocation(plateau,
            initialLocation).isRight()) { "The mower initial position is not valid" }
    }

    fun mow() =
        instructions.forEach(::handleInstruction)

    private fun handleInstruction(mowerAction: MowerAction) {
        when (mowerAction) {
            Left -> turnLeft()
            Right -> turnRight()
            Move -> move()
        }
    }

    private fun turnLeft() {
        currentLocation = currentLocation.copy(direction = currentLocation.direction.turnLeft())
    }

    private fun turnRight() {
        currentLocation = currentLocation.copy(direction = currentLocation.direction.turnRight())
    }

    private fun move() {
        currentLocation = currentLocation.getForwardLocation()
    }

    fun getCurrentLocation() = currentLocation

    companion object {
        fun create(
            plateau: Plateau,
            initialLocation: Location,
            instructions: List<MowerAction>,
        ): Either<MowerError, Mower> =
            validateInitialLocation(plateau, initialLocation)
                .map { Mower(plateau, initialLocation, instructions) }

        private fun validateInitialLocation(plateau: Plateau, initialLocation: Location) =
            if (!plateau.isValidLocation(initialLocation)) InvalidInitialPosition.left()
            else initialLocation.right()

    }
}
