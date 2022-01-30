package com.agjurado.roboticmowers.domain.error

sealed interface MowerActionError: DomainError

object UnknownMowerAction: MowerActionError
