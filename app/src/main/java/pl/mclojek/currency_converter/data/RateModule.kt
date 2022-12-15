package pl.mclojek.currency_converter.data

import android.util.Log
import androidx.paging.PagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.mclojek.currency_converter.data.network.ApiService
import pl.mclojek.currency_converter.data.repository.RatePagingSource
import pl.mclojek.currency_converter.data.repository.RateRepositoryImpl
import pl.mclojek.currency_converter.domain.model.DailyRateSummary
import pl.mclojek.currency_converter.domain.repository.RateRepository
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.LocalDate
import java.util.logging.Level


@InstallIn(SingletonComponent::class)
@Module
class RateModule {

    @Provides
    fun provideRateRepository(apiService: ApiService, pagingSource: RatePagingSource): RateRepository {
        Log.d("dzialam", "dsad")
        return RateRepositoryImpl(apiService, pagingSource)
    }

    @Provides
    fun providePagingSource(apiService: ApiService): RatePagingSource {
        return RatePagingSource(apiService)
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        Log.d("dzialam", "dsa11d")
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideRetrofit(): Retrofit {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Log.d("dzialam", "dsadsddssss")
        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.apilayer.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

}