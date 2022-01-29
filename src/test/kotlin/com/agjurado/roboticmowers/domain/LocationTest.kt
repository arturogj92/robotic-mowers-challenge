package com.agjurado.roboticmowers.domain

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class LocationTest {

    @Test
    fun `calculating the forward location when facing the north`() {
        val location: Location = Location(Coords(2, 3), North)
        val expected: Location = Location(Coords(2, 4), North)

        assertThat(location.getForwardLocation(), `is`(expected))
    }

    @Test
    fun `calculating the forward location when facing the East`() {
        val location: Location = Location(Coords(2, 3), East)
        val expected: Location = Location(Coords(3, 3), East)

        assertThat(location.getForwardLocation(), `is`(expected))
    }

    @Test
    fun `calculating the forward location when facing the West`() {
        val location: Location = Location(Coords(2, 3), West)
        val expected: Location = Location(Coords(1, 3), West)

        assertThat(location.getForwardLocation(), `is`(expected))
    }

    @Test
    fun `calculating the forward location when facing the South`() {
        val location: Location = Location(Coords(2, 3), South)
        val expected: Location = Location(Coords(2, 2), South)

        assertThat(location.getForwardLocation(), `is`(expected))
    }

}
