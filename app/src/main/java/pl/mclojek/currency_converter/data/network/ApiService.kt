package pl.mclojek.currency_converter.data.network

import io.reactivex.Single
import pl.mclojek.currency_converter.data.dto.TimeSeriesResponseDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("/fixer/timeseries")
    fun getTimeSeries(
        @Header("apikey") apikey: String,
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("symbols") symbols: String,
    ): Single<TimeSeriesResponseDto>


}