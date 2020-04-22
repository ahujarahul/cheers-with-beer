package com.rahulahuja.cheerswithbeer.presentation.viewModel.beers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahulahuja.cheerswithbeer.business.domain.usecase.GetBeersUseCase
import com.rahulahuja.cheerswithbeer.business.domain.usecase.RemoveBeerUseCase
import com.rahulahuja.cheerswithbeer.business.domain.usecase.SaveBeerUseCase
import com.rahulahuja.cheerswithbeer.enums.ResultType
import com.rahulahuja.cheerswithbeer.presentation.models.BeerUI
import com.rahulahuja.cheerswithbeer.presentation.models.BeersEntity
import kotlinx.coroutines.launch
import com.rahulahuja.cheerswithbeer.utils.NetworkResult
import com.rahulahuja.cheerswithbeer.presentation.mapper.BeerUIToEntityMapper
import com.rahulahuja.cheerswithbeer.presentation.mapper.BeersEntityToUIMapper
import kotlinx.coroutines.delay

class BeersViewModel(
    private var getMealsByBeersUseCase: GetBeersUseCase,
    private val saveBeerUseCase: SaveBeerUseCase,
    private val removeBeerUseCase: RemoveBeerUseCase
) : ViewModel() {

    private val beersMutableLiveData = MutableLiveData<List<BeerUI>>()
    val beersLiveData get() = beersMutableLiveData

    private val areEmptyBeersMutableLiveData = MutableLiveData<Boolean>()
    val areEmptyBeersLiveData get() = areEmptyBeersMutableLiveData

    private val isErrorMutableLiveData = MutableLiveData<Boolean>()
    val isErrorLiveData get() = isErrorMutableLiveData

    private val isLoadingMutableLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData get() = isLoadingMutableLiveData

    private val isBeerSaveSuccessMutableLiveData = MutableLiveData<Boolean>()
    val isBeerSaveSuccess get() = isBeerSaveSuccessMutableLiveData

    private val isBeerRemovalSuccessMutableLiveData = MutableLiveData<Boolean>()
    val isBeerRemovalSuccess get() = isBeerRemovalSuccessMutableLiveData

    init {
        handleBeersLoad()
    }

    fun handleBeersLoad() {
        isLoadingLiveData(true)
        viewModelScope.launch {
            val beersEntityResult: NetworkResult<BeersEntity> = getMealsByBeersUseCase.execute()

            updateLiveData(beersEntityResult)
        }
    }

    private fun updateLiveData(result: NetworkResult<BeersEntity>) {
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
        viewModelScope.launch {
            BeerUIToEntityMapper.map(beerUI).let { beerEntity ->
                if (beerEntity.isFavorite) {
                    saveBeerUseCase.execute(beerEntity).let { isBeerSaved ->
                        if (!isBeerSaved) {
                            isBeerSaveSuccessMutableLiveData.value = false
                        }
                    }
                } else removeBeerUseCase.execute(beerEntity.id).let { isBeerRemoved ->
                    if (!isBeerRemoved) {
                        isBeerRemovalSuccessMutableLiveData.value = false
                    }
                }
            }
        }
    }
}
