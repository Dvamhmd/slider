package com.example.dots.utilities

import java.text.NumberFormat
import java.util.Locale

fun formatRupiah(number: Int): String {
    val formatter = NumberFormat.getNumberInstance(Locale("in", "ID"))
    return "Rp ${formatter.format(number)}"
}

fun String?.toRupiah(): String {
    val num = this?.toDoubleOrNull()?.toInt() ?: 0
    val formatter = NumberFormat.getNumberInstance(Locale("in", "ID"))
    return "Rp ${formatter.format(num)}"
}

