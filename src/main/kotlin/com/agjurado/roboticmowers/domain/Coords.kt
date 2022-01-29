package com.agjurado.roboticmowers.domain

data class Coords(val x: Int, val y: Int) {

    fun getAboveCoord() = Coords(x, y + 1)
    fun getBelowCoord() = Coords(x, y - 1)
    fun getRightCoord() = Coords(x + 1, y)
    fun getLeftCoord() = Coords(x - 1, y)

}
