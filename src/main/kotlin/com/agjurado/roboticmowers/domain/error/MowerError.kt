package com.agjurado.roboticmowers.domain.error

sealed interface MowerError: DomainError

object InvalidInitialPosition: MowerError
