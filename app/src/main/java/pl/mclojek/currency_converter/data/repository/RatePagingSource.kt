package pl.mclojek.currency_converter.data.repository

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import pl.mclojek.currency_converter.data.dto.DailyRateSummaryDto
import pl.mclojek.currency_converter.data.dto.TimeSeriesResponseDto
import pl.mclojek.currency_converter.data.mapper.asLocalDate
import pl.mclojek.currency_converter.data.mapper.asString
import pl.mclojek.currency_converter.data.mapper.toDomain
import pl.mclojek.currency_converter.data.network.ApiService
import pl.mclojek.currency_converter.domain.model.DailyRateSummary
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject


class RatePagingSource @Inject constructor(
    val apiService: ApiService
) : RxPagingSource<LocalDate, DailyRateSummary>() {

    override fun loadSingle(
        params: LoadParams<LocalDate>
    ): Single<LoadResult<LocalDate, DailyRateSummary>> {

        val page = params.key ?: LocalDate.now()

        return apiService.getTimeSeries(
            "fXjtV2iCM7HAL9M2Ozu50pM02V1FL9Vy",
            page.asString(),
            page.asString(),
            "GBP,PLN"
        )
            .subscribeOn(Schedulers.io())
            .map {
                LoadResult.Page(
                    data = it.toDomain(),
                    prevKey = null,
                    nextKey = it.endDate.asLocalDate().minusDays(1)
                ) as LoadResult<LocalDate, DailyRateSummary>
            }
            .onErrorReturn { LoadResult.Error(it) }

    }

    override fun getRefreshKey(state: PagingState<LocalDate, DailyRateSummary>): LocalDate? {
        TODO("Not yet implemented")
    }
}