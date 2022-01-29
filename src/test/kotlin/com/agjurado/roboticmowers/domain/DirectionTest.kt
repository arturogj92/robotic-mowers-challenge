package com.agjurado.roboticmowers.domain

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class DirectionTest {

    @Test
    fun `when the current direction is North and a turnLeft request is received, the new direction must be West`() {
        val north: Direction = North

        assertThat(north.turnLeft(), `is`(West))
    }

    @Test
    fun `when the current direction is North and a turnRight request is received, the new direction must be East`() {
        val north: Direction = North

        assertThat(north.turnRight(), `is`(East))
    }

    @Test
    fun `when the current direction is West and a turnLeft request is received, the new direction must be South`() {
        val west: Direction = West

        assertThat(west.turnLeft(), `is`(South))
    }

    @Test
    fun `when the current direction is West and a turnRight request is received, the new direction must be North`() {
        val west: Direction = West

        assertThat(west.turnRight(), `is`(North))
    }

    @Test
    fun `when the current direction is South and a turnLeft request is received, the new direction must be East`() {
        val south: Direction = South

        assertThat(south.turnLeft(), `is`(East))
    }

    @Test
    fun `when the current direction is South and a turnRight request is received, the new direction must be West`() {
        val south: Direction = South

        assertThat(south.turnRight(), `is`(West))
    }

    @Test
    fun `when the current direction is East and a turnLeft request is received, the new direction must be North`() {
        val east: Direction = East

        assertThat(east.turnLeft(), `is`(North))
    }


    @Test
    fun `when the current direction is East and a turnRight request is received, the new direction must be South`() {
        val east: Direction = East

        assertThat(east.turnRight(), `is`(South))
    }
}
