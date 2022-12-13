package pl.mclojek.currency_converter.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.mclojek.currency_converter.data.repository.RateRepositoryImpl
import pl.mclojek.currency_converter.domain.repository.RateRepository

@InstallIn(SingletonComponent::class)
@Module
class RateModule {

    @Provides
    fun provideRateRepository(): RateRepository {
        return RateRepositoryImpl()
    }

}