package com.agjurado.roboticmowers.infrastructure.seatmaintenanceoffice.parser

import arrow.core.Validated
import com.agjurado.roboticmowers.domain.*
import com.agjurado.roboticmowers.infrastructure.seatmaintenanceoffice.adapters.SeatMaintenanceOfficeMowerInput
import com.agjurado.roboticmowers.infrastructure.seatmaintenanceoffice.adapters.InvalidInput
import com.agjurado.roboticmowers.infrastructure.seatmaintenanceoffice.adapters.SeatMaintenanceOfficeInputParser
import io.kotest.assertions.arrow.validation.shouldBeInvalid
import io.kotest.assertions.arrow.validation.shouldBeValid
import org.junit.jupiter.api.Test

class SeatMaintenanceOfficeInputParserTest {

    @Test
    fun `parsing a valid Seat Maintenance Officer input, the mowers must be configured correctly`() {
        val input = SeatMaintenanceOfficeMowerInput(
            """
            5 5
            1 2 N
            LMLMLMLMM
            3 3 E
            MMRMMRMRRM
        """.trimIndent()
        )

        val result: Validated<Set<InvalidInput>, List<Mower>> = SeatMaintenanceOfficeInputParser.parseInput(input)

        val firstExpectedMower = Mower(
            Plateau(Coords(5, 5)),
            Location(Coords(1, 2), North),
            listOf(Left, Move, Left, Move, Left, Move, Left, Move, Move)
        )

        val secondExpectedMower = Mower(
            Plateau(Coords(5, 5)),
            Location(Coords(3, 3), East),
            listOf(Move, Move, Right, Move, Move, Right, Move, Right, Right, Move)
        )

        result shouldBeValid listOf(firstExpectedMower, secondExpectedMower)
    }

    @Test
    fun `an input with a letter in the upper coords must return the corresponding input error`() {
        val input = SeatMaintenanceOfficeMowerInput(
            """
            2 E
            1 2 E
            MMRMMRMRRM
        """.trimIndent()
        )

        val result: Validated<Set<InvalidInput>, List<Mower>> = SeatMaintenanceOfficeInputParser.parseInput(input)

        val invalidInput = setOf(InvalidInput(field="NotNumericCoords", error="NotNumericCoords"))

        result shouldBeInvalid invalidInput
    }

    @Test
    fun `an input with a not enough big plateau must return the corresponding input error`() {
        val input = SeatMaintenanceOfficeMowerInput(
            """
            0 0
            1 2 E
            MMRMMRMRRM
        """.trimIndent()
        )

        val result: Validated<Set<InvalidInput>, List<Mower>> = SeatMaintenanceOfficeInputParser.parseInput(input)

        val invalidInput = setOf(InvalidInput(field="ExceedsPlateauLimit", error="ExceedsPlateauLimit"))

        result shouldBeInvalid invalidInput
    }


    @Test
    fun `an input with unknown direction must return the corresponding input error`() {
        val input = SeatMaintenanceOfficeMowerInput(
            """
            5 2
            1 2 V
            MMRMMRMRRM
        """.trimIndent()
        )

        val result: Validated<Set<InvalidInput>, List<Mower>> = SeatMaintenanceOfficeInputParser.parseInput(input)

        val invalidInput = setOf(InvalidInput(field="Direction", error="UnknownDirection"))

        result shouldBeInvalid invalidInput
    }

    @Test
    fun `an input with unknown action must return the corresponding input error`() {
        val input = SeatMaintenanceOfficeMowerInput(
            """
            5 2
            1 2 E
            JMRMMRMRRM
        """.trimIndent()
        )

        val result: Validated<Set<InvalidInput>, List<Mower>> = SeatMaintenanceOfficeInputParser.parseInput(input)

        val invalidInput = setOf(InvalidInput(field="MowerActionError", error="UnknownMowerAction"))

        result shouldBeInvalid invalidInput
    }


    @Test
    fun `if the input contains several bad inputs the errors will be accumulated`() {
        val input = SeatMaintenanceOfficeMowerInput(
            """
            1 0
            1 2 R
            JMRMMRMRRM
        """.trimIndent()
        )

        val result: Validated<Set<InvalidInput>, List<Mower>> = SeatMaintenanceOfficeInputParser.parseInput(input)

        val invalidInputs = setOf(
            InvalidInput(field="ExceedsPlateauLimit", error="ExceedsPlateauLimit"),
            InvalidInput(field="Direction", error="UnknownDirection"),
            InvalidInput(field="MowerActionError", error="UnknownMowerAction"),
        )

        result shouldBeInvalid invalidInputs
    }



}
