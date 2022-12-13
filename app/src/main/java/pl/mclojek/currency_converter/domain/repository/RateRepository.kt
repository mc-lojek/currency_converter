package pl.mclojek.currency_converter.domain.repository

import io.reactivex.Observable
import io.reactivex.Single
import pl.mclojek.currency_converter.data.dto.DailyRateSummaryDto
import pl.mclojek.currency_converter.domain.model.DailyRateSummary
import java.time.LocalDate

interface RateRepository {
    fun getSummaryPerDay(day: LocalDate): Single<DailyRateSummary>

    fun getSummaryPerPeriod(
        startDay: LocalDate,
        endDay: LocalDate
    ): Observable<List<DailyRateSummary>>
}