package com.agjurado.roboticmowers.shared

fun String.removeWhiteSpaces() = this.filterNot { it.isWhitespace() }
