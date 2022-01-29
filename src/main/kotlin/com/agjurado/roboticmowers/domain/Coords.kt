package com.agjurado.roboticmowers.domain

data class Coords(val x: Int, val y: Int) {

    fun getAboveCoords() = Coords(x, y + 1)
    fun getBelowCoords() = Coords(x, y - 1)
    fun getRightCoords() = Coords(x + 1, y)
    fun getLeftCoords() = Coords(x - 1, y)

}
