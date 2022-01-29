package com.agjurado.roboticmowers.domain

data class Plateau(val xSize: Size, val ySize: Size) {
    private val xMin = 0
    private val yMin = 0


    fun isValidLocation(position: Location) =
        when {
            position.coords.x > xSize.value -> false
            position.coords.y > ySize.value -> false
            position.coords.x < xMin -> false
            position.coords.y < yMin -> false
            else -> true
        }

}
