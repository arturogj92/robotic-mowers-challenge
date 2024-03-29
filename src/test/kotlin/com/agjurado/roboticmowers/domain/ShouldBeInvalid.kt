package com.agjurado.roboticmowers.domain

import arrow.core.Either
import io.kotest.assertions.arrow.validation.shouldBeInvalid
import io.kotest.assertions.arrow.validation.shouldBeValid

infix fun <L,R>Either<L, R>.shouldBeInvalid(invalidValue: L) = this.toValidated() shouldBeInvalid invalidValue
infix fun <L,R>Either<L, R>.shouldBeValid(validValue: R) = this.toValidated() shouldBeValid validValue
