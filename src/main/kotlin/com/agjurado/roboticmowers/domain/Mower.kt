package com.agjurado.roboticmowers.domain

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.agjurado.roboticmowers.domain.error.InvalidInitialPosition
import com.agjurado.roboticmowers.domain.error.MowerError

data class Mower(
    private val plateau: Plateau,
    private val initialLocation: Location,
    private val actions: List<MowerAction>,
) {
    private var currentLocation = initialLocation

    init {
        require(validateInitialLocation(plateau,
            initialLocation).isRight()) { "The mower initial position is not valid" }
    }

    fun mow(): Mower =
        actions.forEach(::handleAction)
            .let { this }


    private fun handleAction(mowerAction: MowerAction) {
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
        if (mowerGetOffTheGround()) {
            throw IllegalStateException("The mower is stucked because it went off the plateau")
        }
    }

    private fun mowerGetOffTheGround(): Boolean {
        return plateau.isLocationOutsideOfPlateau(currentLocation)
    }

    fun getCurrentLocation() = currentLocation

    companion object {
        fun create(
            plateau: Plateau,
            initialLocation: Location,
            actions: List<MowerAction>,
        ): Either<MowerError, Mower> =
            validateInitialLocation(plateau, initialLocation)
                .map { Mower(plateau, initialLocation, actions) }

        private fun validateInitialLocation(plateau: Plateau, initialLocation: Location) =
            if (!plateau.isValidLocation(initialLocation)) InvalidInitialPosition.left()
            else initialLocation.right()

    }
}
