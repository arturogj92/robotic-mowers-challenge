package com.agjurado.roboticmowers.domain


data class Location(val coords: Coords, val direction: Direction) {

    fun getForwardLocation(): Location =
        when (direction) {
            North -> copy(coords = coords.getAboveCoord())
            West -> copy(coords = coords.getLeftCoord())
            South -> copy(coords = coords.getBelowCoord())
            East -> copy(coords = coords.getRightCoord())
        }

}
