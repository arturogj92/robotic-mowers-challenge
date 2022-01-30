package com.agjurado.roboticmowers.domain

import arrow.core.Validated
import com.agjurado.roboticmowers.infrastructure.seatmaintenanceoffice.parser.InvalidInput
import com.agjurado.roboticmowers.infrastructure.seatmaintenanceoffice.parser.SeatMaintenanceOfficeInputParser

interface InputParser<T> {
    fun parseInput(input: T): List<Validated<List<InvalidInput>, Mower>>
}
