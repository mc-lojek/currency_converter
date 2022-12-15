package pl.mclojek.currency_converter.domain.repository

import androidx.paging.PagingData
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import pl.mclojek.currency_converter.data.dto.DailyRateSummaryDto
import pl.mclojek.currency_converter.data.dto.TimeSeriesResponseDto
import pl.mclojek.currency_converter.domain.model.DailyRateSummary
import java.time.LocalDate

interface RateRepository {
    fun getSummaryPerDay(day: LocalDate): Flowable<PagingData<DailyRateSummary>>

    fun getSummaryPerPeriod(
        startDay: LocalDate,
        endDay: LocalDate
    ): Observable<List<DailyRateSummary>>
}