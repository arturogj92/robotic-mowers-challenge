package com.agjurado.roboticmowers.infrastructure.seatmaintenanceoffice

import com.agjurado.roboticmowers.application.MowerActivator
import com.agjurado.roboticmowers.domain.InputParser
import com.agjurado.roboticmowers.domain.Mower
import org.springframework.stereotype.Component

@Component
class SeatMaintenanceOfficeReceiver(
    private val inputParser: InputParser<SeatMaintenanceOfficeMowerInput>,
    private val mowerActivator: MowerActivator
) {

    init {
        inputParser
            .parseInput(exampleInput)
            .map { powerOnMow(it) }
            .mapLeft { println("WARNING! - The input is not valid, got the following errors $it") }
    }

    private fun powerOnMow(parsedMow: List<Mower>) {
        parsedMow
            .map(mowerActivator::startMowing)
    }


    companion object {
        private val exampleInput = SeatMaintenanceOfficeMowerInput(
            """
            5 5
            1 2 N
            LMLMLMLMM
            3 3 E
            MMRMMRMRRM
        """.trimIndent()
        )
    }
}
