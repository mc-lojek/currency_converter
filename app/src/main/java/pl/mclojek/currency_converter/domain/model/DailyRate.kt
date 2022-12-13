package pl.mclojek.currency_converter.domain.model

import java.time.LocalDate

data class DailyRateSummary(
    val day: LocalDate,
    val baseCurrency: String,
    val rates: List<Rate>,
)

data class Rate(
    val currency: String,
    val value: Float,
)