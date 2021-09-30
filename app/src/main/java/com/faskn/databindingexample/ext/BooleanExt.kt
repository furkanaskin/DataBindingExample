package com.faskn.databindingexample.ext

fun Boolean?.orTrue() = this ?: true

fun Boolean?.orFalse() = this ?: false

fun Boolean.reverse() = this.not()