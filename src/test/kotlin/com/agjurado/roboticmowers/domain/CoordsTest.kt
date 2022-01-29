package com.agjurado.roboticmowers.domain

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



}
