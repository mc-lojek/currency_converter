package pl.mclojek.currency_converter.data.mapper

import pl.mclojek.currency_converter.data.dto.DailyRateSummaryDto
import pl.mclojek.currency_converter.domain.model.DailyRateSummary

fun DailyRateSummaryDto.toDomain() = DailyRateSummary(day, baseCurrency, rates)