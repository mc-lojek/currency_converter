package pl.mclojek.currency_converter.data.repository

import io.reactivex.Observable
import io.reactivex.Single
import pl.mclojek.currency_converter.domain.model.DailyRateSummary
import pl.mclojek.currency_converter.domain.model.Rate
import pl.mclojek.currency_converter.domain.repository.RateRepository
import java.time.LocalDate
import kotlin.random.Random

class RateRepositoryImpl : RateRepository {
    override fun getSummaryPerDay(day: LocalDate): Single<DailyRateSummary> {
        return Single.just(
            DailyRateSummary(
                LocalDate.now(),
                "USD",
                listOf(Rate("GBP", Random.nextFloat()), Rate("PLN", Random.nextFloat()))
            )
        )
    }

    override fun getSummaryPerPeriod(
        startDay: LocalDate,
        endDay: LocalDate
    ): Observable<List<DailyRateSummary>> {
        TODO()
    }
}