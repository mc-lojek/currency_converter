package pl.mclojek.currency_converter.data.dto

import pl.mclojek.currency_converter.domain.model.Rate
import java.time.LocalDate

data class DailyRateSummaryDto(
    val day: LocalDate,
    val baseCurrency: String,
    val rates: List<Rate>,
)
