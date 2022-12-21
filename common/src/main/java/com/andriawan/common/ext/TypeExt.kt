package com.andriawan.common.ext

fun Int?.orZero(): Int = this ?: 0
fun Double?.orZero(): Double = this ?: 0.0
fun Boolean?.orFalse(): Boolean = this ?: false