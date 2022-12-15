package pl.mclojek.currency_converter.data.mapper

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.asString(): String = this.format(DateTimeFormatter.ISO_DATE)

fun String.asLocalDate(): LocalDate = LocalDate.parse(this)
