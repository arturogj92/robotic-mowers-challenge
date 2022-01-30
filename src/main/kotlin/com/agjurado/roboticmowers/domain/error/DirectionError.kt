package com.agjurado.roboticmowers.domain.error

sealed interface DirectionError : DomainError

object DirectionMustBeALetter : DirectionError
object UnknownDirection : DirectionError
