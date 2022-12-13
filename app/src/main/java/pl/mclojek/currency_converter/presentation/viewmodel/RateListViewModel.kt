package pl.mclojek.currency_converter.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.mclojek.currency_converter.data.util.Resource
import pl.mclojek.currency_converter.domain.model.DailyRateSummary
import pl.mclojek.currency_converter.domain.repository.RateRepository
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class RateListViewModel @Inject constructor(
    val repo: RateRepository
): ViewModel() {

    private val _summariesLiveData = MutableLiveData<Resource<DailyRateSummary>>()
    val summariesLiveData = _summariesLiveData as LiveData<Resource<DailyRateSummary>>

    fun loadSummary(date: LocalDate) {
        repo.getSummaryPerDay(LocalDate.now())

    }



}