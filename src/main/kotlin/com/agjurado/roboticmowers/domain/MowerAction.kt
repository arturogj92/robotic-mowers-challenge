package com.agjurado.roboticmowers.domain

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.agjurado.roboticmowers.domain.error.MowerActionError
import com.agjurado.roboticmowers.domain.error.UnknownMowerAction

sealed interface MowerAction {

    companion object {
        fun parseRawAction(rawAction: Char): Either<MowerActionError, MowerAction> =
            when (rawAction) {
                'R' -> Right.right()
                'L' -> Left.right()
                'M' -> Move.right()
                else -> UnknownMowerAction.left()
            }
    }
}

object Right : MowerAction
object Left : MowerAction
object Move : MowerAction
