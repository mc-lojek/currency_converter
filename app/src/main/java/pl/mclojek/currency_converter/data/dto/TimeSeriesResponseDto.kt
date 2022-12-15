package pl.mclojek.currency_converter.data.dto

import com.squareup.moshi.Json

data class TimeSeriesResponseDto(
    val base: String,
    @field:Json(name = "end_date") val endDate: String,
    val rates: Map<String, Map<String, Float>>,
)
