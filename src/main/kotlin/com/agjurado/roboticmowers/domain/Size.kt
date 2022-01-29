package com.agjurado.roboticmowers.domain

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.agjurado.roboticmowers.domain.error.InvalidSize

data class Size(val value: Int) {

    init {
        require(validate(value).isRight()) {"Invalid size"}
    }

    companion object {
        fun parse(value: Int): Either<InvalidSize, Size> =
            validate(value)
                .map(::Size)

        private fun validate(value: Int) =
            if (value <= 1) InvalidSize.left()
            else value.right()
    }
}
