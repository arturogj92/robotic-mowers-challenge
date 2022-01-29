package com.agjurado.roboticmowers.domain

data class Plateau(val xSize: Size, val ySize: Size) {
    private val xMin = 0
    private val yMin = 0


    fun isValidLocation(position: Location) =
        when {
            position.x > xSize.value -> false
            position.y > ySize.value -> false
            position.x < xMin -> false
            position.y < yMin -> false
            else -> true
        }

}
