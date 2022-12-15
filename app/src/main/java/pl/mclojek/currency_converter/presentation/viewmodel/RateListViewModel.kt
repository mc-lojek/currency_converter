package pl.mclojek.currency_converter.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import pl.mclojek.currency_converter.data.util.Resource
import pl.mclojek.currency_converter.domain.model.DailyRateSummary
import pl.mclojek.currency_converter.domain.model.Rate
import pl.mclojek.currency_converter.domain.repository.RateRepository
import java.time.LocalDate
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class RateListViewModel @Inject constructor(
    val repo: RateRepository
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _summariesLiveData = MutableLiveData<PagingData<DailyRateSummary>>()
    val summariesLiveData = _summariesLiveData as LiveData<PagingData<DailyRateSummary>>

    init {
        compositeDisposable.add(
            repo.getSummaryPerDay(LocalDate.now())
                .cachedIn(viewModelScope)
                .subscribe {
                    _summariesLiveData.value = it
                }
        )
    }



}