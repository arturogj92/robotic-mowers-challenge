package com.agjurado.roboticmowers.domain

sealed class Direction {
  abstract fun turnLeft(): Direction
  abstract fun turnRight(): Direction
}

object North: Direction() {
  override fun turnLeft() = West
  override fun turnRight() = East

}

object West: Direction() {
  override fun turnLeft() = South
  override fun turnRight() = North

}

object South: Direction() {
  override fun turnLeft() = East
  override fun turnRight() = West

}

object East: Direction() {
  override fun turnLeft() = North
  override fun turnRight() = South

}
