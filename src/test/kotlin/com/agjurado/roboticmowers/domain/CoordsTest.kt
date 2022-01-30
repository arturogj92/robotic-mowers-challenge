package com.agjurado.roboticmowers.domain

import com.agjurado.roboticmowers.domain.error.InvalidRawCoordsAmount
import com.agjurado.roboticmowers.domain.error.NotNumericCoords
import io.kotest.assertions.arrow.either.shouldBeRight
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class CoordsTest {

    @Test
    fun `calculating some above coords`() {
        assertThat(Coords(0, 0).getAboveCoords(), `is`(Coords(0, 1)))
        assertThat(Coords(2, 4).getAboveCoords(), `is`(Coords(2, 5)))
    }

    @Test
    fun `calculating some below coords`() {
        assertThat(Coords(2, 5).getBelowCoords(), `is`(Coords(2, 4)))
        assertThat(Coords(1, 1).getBelowCoords(), `is`(Coords(1, 0)))
    }

    @Test
    fun `calculating some left coords`() {
        assertThat(Coords(2, 5).getLeftCoords(), `is`(Coords(1, 5)))
        assertThat(Coords(1, 1).getLeftCoords(), `is`(Coords(0, 1)))
    }

    @Test
    fun `calculating some right coords`() {
        assertThat(Coords(2, 5).getRightCoords(), `is`(Coords(3, 5)))
        assertThat(Coords(1, 1).getRightCoords(), `is`(Coords(2, 1)))
    }

    @Test
    fun `parsing well formatted raw coords separated by spaces`() {
        val rawCoords = "3 3"

        Coords.parseRaw(rawCoords).shouldBeRight()
    }

    @Test
    fun `parsing bad formatted raw coords with invalid number of coords, an error must be returned`() {
        val invalidRawCoords = listOf(
            "33",
            "1 3 5",
            "555",
            "8494513",
            ""
        )

        invalidRawCoords.forEach {
            Coords.parseRaw(it) shouldBeInvalid InvalidRawCoordsAmount
        }
    }

    @Test
    fun `parsing bad formatted raw coords with not numeric coords, an error must be returned`() {
        val invalidRawCoords = listOf(
            "5 a",
            "a b",
            "B D",
        )

        invalidRawCoords.forEach {
            Coords.parseRaw(it) shouldBeInvalid NotNumericCoords
        }
    }



}
