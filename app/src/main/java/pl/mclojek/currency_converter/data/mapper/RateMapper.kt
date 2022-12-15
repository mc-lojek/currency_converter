package pl.mclojek.currency_converter.data.mapper

import pl.mclojek.currency_converter.data.dto.DailyRateSummaryDto
import pl.mclojek.currency_converter.data.dto.TimeSeriesResponseDto
import pl.mclojek.currency_converter.domain.model.DailyRateSummary
import pl.mclojek.currency_converter.domain.model.Rate

fun DailyRateSummaryDto.toDomain() = DailyRateSummary(day, baseCurrency, rates)

fun TimeSeriesResponseDto.toDomain(): List<DailyRateSummary> {
    return rates.map { DailyRateSummary(it.key.asLocalDate(), base, it.value.map { Rate(it.key, it.value) }) }
}