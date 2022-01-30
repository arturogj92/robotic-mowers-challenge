package com.agjurado.roboticmowers.infrastructure.seatmaintenanceoffice.parser

import arrow.core.*
import arrow.typeclasses.Semigroup
import com.agjurado.roboticmowers.domain.*
import com.agjurado.roboticmowers.domain.error.*
import com.agjurado.roboticmowers.infrastructure.seatmaintenanceoffice.SeatMaintenanceOfficeMowerInput
import com.agjurado.roboticmowers.shared.languageextensions.filterIndexedBase1
import com.agjurado.roboticmowers.shared.removeWhiteSpaces
import org.springframework.stereotype.Component


@Component
object SeatMaintenanceOfficeInputParser : InputParser<SeatMaintenanceOfficeMowerInput> {
    override fun parseInput(input: SeatMaintenanceOfficeMowerInput): List<Validated<List<InvalidInput>, Mower>> =
        mapMowerRawConfig(input.value.lines())
            .let(::generateConfiguredMowers)

    private fun generateConfiguredMowers(rawMowersConfig: MowersRawConfig) =
        (0 until rawMowersConfig.numberOfMowersToConfigure)
            .map { index -> mapMower(rawMowersConfig, index) }

    private fun mapMower(
        rawMowersConfig: MowersRawConfig,
        index: Int,
    ): Validated<List<InvalidInput>, Mower> {
        val plateau = mapPlateau(rawMowersConfig)
        val mowerInitialLocation = parseMowerInitialLocation(rawMowersConfig, index)
        val mowerActions = parseMowerActions(rawMowersConfig, index)

        return plateau
            .zip(
                Semigroup.list(),
                mowerInitialLocation,
                mowerActions,
                ::Mower
            )
    }

    private fun mapPlateau(rawMowersConfig: MowersRawConfig): Validated<List<InvalidInput>, Plateau> =
        Coords.parseRaw(rawMowersConfig.rawPlateauUpperRightCoords.value)
            .flatMap(Plateau.Companion::createPlateauFrom)
            .toValidatedNel()
            .mapLeft {
                listOf(InvalidInput(it[0]::class.java.simpleName, it.first()::class.java.simpleName))
            }

    private fun parseMowerInitialLocation(
        rawMowersConfig: MowersRawConfig,
        index: Int,
    ): Validated<List<InvalidInput>, Location> {
        val mowerInitialCoords =
            parseInput(rawMowersConfig.rawInitialMowerCoords[index].value, Coords.Companion::parseRaw)
        val mowerInitialDirection =
            parseInput(rawMowersConfig.rawInitialMowerDirection[index].value, Direction.Companion::parseRawDirection)

        return doParseLocation(mowerInitialCoords, mowerInitialDirection)
    }

    private fun parseMowerActions(
        rawMowersConfig: MowersRawConfig,
        index: Int,
    ) =
        rawMowersConfig
            .rawMowerActions[index]
            .value
            .toCharArray()
            .toList()
            .traverseEither(MowerAction.Companion::parseRawAction)
            .toValidatedNel()
            .mapLeft { listOf(InvalidInput(MowerActionError::class.java.simpleName, it.first()::class.java.simpleName)) }

    private fun mapMowerRawConfig(rawInstructions: List<String>): MowersRawConfig {
        val retrieveRawMowersInitialCoords: List<RawCoord> = extractMowerConfiguration(
            rawInstructions,
            isInstructionLineEven(),
            { it.dropLast(2) },
            ::RawCoord
        )

        val retrieveRawMowersInitialDirection: List<RawDirection> = extractMowerConfiguration(
            rawInstructions,
            isInstructionLineEven(),
            { it.removeWhiteSpaces().last() },
            ::RawDirection
        )

        val retrieveRawMowersAction: List<RawActions> = extractMowerConfiguration(
            rawInstructions,
            isInstructionLineOddAndNotFirst(),
            ::identity,
            ::RawActions
        )

        return MowersRawConfig(
            rawPlateauUpperRightCoords = rawInstructions[0].let(::RawCoord),
            rawInitialMowerCoords = retrieveRawMowersInitialCoords,
            rawInitialMowerDirection = retrieveRawMowersInitialDirection,
            rawMowerActions = retrieveRawMowersAction
        )
    }

    private fun isInstructionLineEven(): (Int) -> Boolean = { it % 2 == 0 }

    private fun isInstructionLineOddAndNotFirst(): (Int) -> Boolean = { it % 2 != 0 && it != 1}

    private fun <T, R> extractMowerConfiguration(
        rawInstructions: List<String>,
        shouldParseInstructionIf: Predicate<Int>,
        configurationExtractor: (String) -> R,
        configurationMapper: (R) -> (T),
    ) =
        rawInstructions.filterIndexedBase1 { index, _ -> shouldParseInstructionIf(index) }
            .map { configurationExtractor(it) }
            .map { configurationMapper(it) }

    private fun doParseLocation(
        mowerInitialCoords: Validated<List<InvalidInput>, Coords>,
        mowerInitialDirection: Validated<List<InvalidInput>, Direction>,
    ) = mowerInitialCoords
        .zip(
            Semigroup.list(),
            mowerInitialDirection,
            ::Location
        )

    private inline fun <T, reified R> parseInput(
        field: T,
        parser: (T) -> Either<DomainError, R>,
    ): Validated<List<InvalidInput>, R> =
        parser(field)
            .toValidatedNel()
            .mapLeft { listOf(InvalidInput(R::class.java.simpleName, it.first()::class.java.simpleName)) }

}
