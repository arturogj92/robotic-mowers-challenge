package com.agjurado.roboticmowers.domain

import arrow.core.Either
import com.agjurado.roboticmowers.domain.error.InvalidSize
import io.kotest.assertions.arrow.either.shouldBeLeft
import io.kotest.assertions.arrow.either.shouldBeRight
import org.junit.jupiter.api.Test

class SizeTest {

    @Test
    fun `a size can't be lower than one`() {
        val anInvalidSize: Either<InvalidSize, Size> = Size.parse(0)

        anInvalidSize.shouldBeLeft()
    }

    @Test
    fun `creating an invalid size`() {
        val aValidSize: Either<InvalidSize, Size> = Size.parse(2)

        aValidSize.shouldBeRight()
    }

}
