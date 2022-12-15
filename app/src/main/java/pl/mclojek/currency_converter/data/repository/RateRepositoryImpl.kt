package pl.mclojek.currency_converter.data.repository

import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pl.mclojek.currency_converter.data.dto.TimeSeriesResponseDto
import pl.mclojek.currency_converter.data.mapper.toDomain
import pl.mclojek.currency_converter.data.network.ApiService
import pl.mclojek.currency_converter.domain.model.DailyRateSummary
import pl.mclojek.currency_converter.domain.repository.RateRepository
import java.time.LocalDate
import javax.inject.Inject

class RateRepositoryImpl @Inject constructor(
    val apiService: ApiService,
    val pagingSource: RatePagingSource
) : RateRepository {

    @SuppressLint("CheckResult")
    override fun getSummaryPerDay(day: LocalDate): Flowable<PagingData<DailyRateSummary>> {

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                prefetchDistance = 3
            ),
            pagingSourceFactory = { pagingSource }
            ).flowable
//
//        val res = apiService.getTimeSeries(
//            "UJZR5eygzIkNvP8k4P5J5MoY0Lp2US5J",
//            "UJZR5eygzIkNvP8k4P5J5MoY0Lp2US5J",
//            "2020-02-20",
//            "2020-02-21"
//        )
//        Log.d("tak tak tak", "dzialam sobie")
//        res
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { emittedObject ->
//                    Log.d("tak tak tak", emittedObject.toString())
//                    Log.d("tak tak tak", emittedObject.toDomain().toString())
//                    Log.d("sds", emittedObject.rates["2020-02-20"].toString())
//                    Log.d("sds", emittedObject.rates["2020-02-20"]!!["AED"].toString())
//                },
//                { error -> error.printStackTrace() })

        //return res
    }

    override fun getSummaryPerPeriod(
        startDay: LocalDate,
        endDay: LocalDate
    ): Observable<List<DailyRateSummary>> {
        TODO()
    }
}