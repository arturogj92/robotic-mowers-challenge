package com.agjurado.roboticmowers.domain

import arrow.core.Validated
import com.agjurado.roboticmowers.infrastructure.seatmaintenanceoffice.parser.InvalidInput

interface InputParser<T> {
    fun parseInput(input: T): Validated<Set<InvalidInput>, List<Mower>>
}
