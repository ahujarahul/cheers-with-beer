package com.rahulahuja.cheerswithbeer.presentation.viewModel.beers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rahulahuja.cheerswithbeer.presentation.models.BeerUI

class BeersViewModel : ViewModel() {

    private val beersMutableLiveData = MutableLiveData<List<BeerUI>>()
    val beersLiveData: LiveData<List<BeerUI>> = beersMutableLiveData

    fun handleFavoriteButton(beerUI: BeerUI?) {
        TODO("Not yet implemented")
    }
}
