package com.getlosthere.nyethack.extensions

fun String.frame(padding: Int): String {
    val formatChar = "*"
    val middle = formatChar.padEnd(padding).plus(this).plus(formatChar.padStart(padding))
    val end = (0 until middle.length).joinToString("") { formatChar }

    return "$end\n$middle\n$end"
}