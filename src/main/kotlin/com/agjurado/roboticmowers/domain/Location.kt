package com.agjurado.roboticmowers.domain


data class Location(val coords: Coords, val direction: Direction) {

    fun getForwardLocation(): Location =
        when (direction) {
            North -> copy(coords = coords.getAboveCoords())
            West -> copy(coords = coords.getLeftCoords())
            South -> copy(coords = coords.getBelowCoords())
            East -> copy(coords = coords.getRightCoords())
        }
}
