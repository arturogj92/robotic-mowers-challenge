package com.agjurado.roboticmowers.shared.languageextensions

fun <T> List<T>.filterIndexedBase1(predicate: (Int, T) -> Boolean): List<T> =
    this.filterIndexed { index, x -> predicate(toBase1(index), x) }

private fun toBase1(index: Int) = index + 1
