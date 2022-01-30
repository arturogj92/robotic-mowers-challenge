package com.agjurado.roboticmowers.shared.languageextensions

fun String.removeWhiteSpaces() = this.filterNot { it.isWhitespace() }
