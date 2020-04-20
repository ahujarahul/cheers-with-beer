package com.rahulahuja.cheerswithbeer.presentation.viewModel.beers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahulahuja.cheerswithbeer.business.domain.usecase.GetBeersUseCase
import com.rahulahuja.cheerswithbeer.presentation.enums.ResultType
import com.rahulahuja.cheerswithbeer.presentation.models.BeerUI
import com.rahulahuja.cheerswithbeer.presentation.models.BeersEntity
import kotlinx.coroutines.launch
import com.rahulahuja.cheerswithbeer.data.Result
import com.rahulahuja.cheerswithbeer.presentation.mapper.BeersEntityToUIMapper
import kotlinx.coroutines.delay

class BeersViewModel : ViewModel() {

    private val beersMutableLiveData = MutableLiveData<List<BeerUI>>()
    val beersLiveData get() = beersMutableLiveData
    private var getMealsByBeersUseCase: GetBeersUseCase? = null

    private val areEmptyBeersMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val areEmptyBeersLiveData get() = areEmptyBeersMutableLiveData

    private val isErrorMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isErrorLiveData get() = isErrorMutableLiveData

    private val isLoadingMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isLoadingLiveData get() = isLoadingMutableLiveData

    init {
        getMealsByBeersUseCase = GetBeersUseCase()
        handleBeersLoad()
    }

    fun handleBeersLoad() {
        isLoadingLiveData(true)
        viewModelScope.launch {
            val beersEntityResult: Result<BeersEntity> = getMealsByBeersUseCase.execute()

            updateAppropriateLiveData(beersEntityResult)
        }
    }

    private fun updateAppropriateLiveData(result: Result<BeersEntity>) {
        if (isResultSuccess(result.resultType)) {
            onResultSuccess(result.data)
        } else {
            onResultError()
        }
    }

    private fun onResultSuccess(beersEntity: BeersEntity?) {
        val beers = BeersEntityToUIMapper.map(beersEntity?.beers)

        if (beers.isEmpty()) {
            areEmptyBeersMutableLiveData.value = true
        } else {
            beersMutableLiveData.value = beers
        }

        isLoadingLiveData(false)
    }

    private fun onResultError() {
        viewModelScope.launch {
            delay(300)
            isLoadingLiveData(false)
        }.invokeOnCompletion {
            isErrorMutableLiveData.value = true
        }
    }

    private fun isResultSuccess(resultType: ResultType): Boolean {
        return resultType == ResultType.SUCCESS
    }

    private fun isLoadingLiveData(isLoading: Boolean) {
        this.isLoadingMutableLiveData.value = isLoading
    }

    fun handleFavoriteButton(beerUI: BeerUI?) {
        TODO("Not yet implemented")
    }
}
