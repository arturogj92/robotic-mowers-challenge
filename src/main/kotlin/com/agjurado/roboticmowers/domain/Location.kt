package com.agjurado.roboticmowers.domain


data class Location(val coords: Coords, val direction: Direction) {

    fun getForwardLocation(): Location =
        when (direction) {
            North -> copy(coords = coords.copy(y = coords.y + 1))
            West -> copy(coords = coords.copy(x = coords.x - 1))
            South -> copy(coords = coords.copy(y = coords.y - 1))
            East -> copy(coords = coords.copy(x = coords.x + 1))
        }


}
