package com.agjurado.roboticmowers.domain

import arrow.core.Either
import io.kotest.assertions.arrow.validation.shouldBeInvalid

infix fun <L,R>Either<L, R>.shouldBeInvalid(invalidValue: L) = this.toValidated() shouldBeInvalid invalidValue
