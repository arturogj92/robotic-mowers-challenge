package com.agjurado.roboticmowers.domain

sealed class MowerAction

object Right: MowerAction()
object Left: MowerAction()
object Move: MowerAction()
