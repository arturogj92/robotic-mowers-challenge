package com.agjurado.roboticmowers.infrastructure.seatmaintenanceoffice

import com.agjurado.roboticmowers.domain.InputParser

data class SeatMaintenanceOfficeMowerInput(val value: String)



class SeatMaintenanceOfficeReceiver(
    private val inputParser: InputParser<SeatMaintenanceOfficeMowerInput>
) {

    fun receive(request: SeatMaintenanceOfficeMowerInput) {
        inputParser.parseInput(request)
    }


    companion object {
        private val exampleRequest = """
            5 5
            1 2 N 
            LMLMLMLMM
            3 3 E
            MMRMMRMRRM
        """.trimIndent()
    }
}
