package com.rahulahuja.cheerswithbeer.presentation.ui.beers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.rahulahuja.cheerswithbeer.R
import com.rahulahuja.cheerswithbeer.presentation.mapper.BeerAdapterModelToBeerUiMapper
import com.rahulahuja.cheerswithbeer.presentation.mapper.BeerUIToAdapterModelMapper
import com.rahulahuja.cheerswithbeer.presentation.models.BeerAdapterModel
import com.rahulahuja.cheerswithbeer.presentation.models.BeerDetailUI
import com.rahulahuja.cheerswithbeer.presentation.models.BeerUI
import com.rahulahuja.cheerswithbeer.presentation.ui.MainActivity
import com.rahulahuja.cheerswithbeer.presentation.ui.beerDetails.BeerDetailsFragment
import com.rahulahuja.cheerswithbeer.presentation.ui.beers.adapter.BeersAdapter
import com.rahulahuja.cheerswithbeer.presentation.ui.favoutireBeers.FavouriteBeersFragment
import com.rahulahuja.cheerswithbeer.presentation.viewModel.beers.BeersViewModel
import kotlinx.android.synthetic.main.fragment_beers.*
import java.util.ArrayList

class BeersFragment : Fragment(), View.OnClickListener, BeersFragmentCallback {

    private val beersAdapter = BeersAdapter(this)
    private var viewModel = BeersViewModel()

    private fun setBeerDetailListener(beerAdapterModel: BeerAdapterModel) {
        viewModel.handleFavoriteButton(BeerAdapterModelToBeerUiMapper.map(beerAdapterModel))
    }

    private fun setFavoriteBeerListener(beerAdapterModel: BeerAdapterModel) {
        showBeerDetails(getBeerDetailsFragment(
            BeerDetailUI(
                image = beerAdapterModel.image,
                foodPairing = beerAdapterModel.foodPairing as ArrayList<String>)))
    }

    companion object {
        fun newInstance() = BeersFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_beers, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(BeersViewModel::class.java)
        viewModel = ViewModelProvider(this).get(BeersViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        setDefaultViews()
        setClickListeners()
        observeViewModel()
    }

    private fun setDefaultViews() {
        (activity as MainActivity).setTitle(resources.getString(R.string.beers))
    }

    private fun setClickListeners() {
        fab_favourites.setOnClickListener(this)
    }

    private fun observeViewModel() {
        viewModel.beersLiveData.observe(viewLifecycleOwner, Observer(::onBeersReceived))
//        viewModel.isErrorLiveData.observe(this, Observer { onErrorReceived() })
//        viewModel.areEmptyBeersLiveData.observe(this, Observer { onEmptyBeersReceived() })
//        viewModel.isLoadingLiveData.observe(this, Observer(::onLoadingStateReceived))
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.fab_favourites -> showFavouriteBeers()
        }
    }

    private fun showFavouriteBeers() {
        (activity as MainActivity)
            .replaceFragment(FavouriteBeersFragment.newInstance(), false)
    }

    private fun showBeerDetails(beerDetailsFragment: BeerDetailsFragment) {
        (activity as MainActivity)
            .replaceFragment(beerDetailsFragment, false)
    }

    private fun getBeerDetailsFragment(beerDetailUi: BeerDetailUI): BeerDetailsFragment {
        return BeerDetailsFragment.newInstance(beerDetailUi)
    }

    private fun onBeersReceived(beers: List<BeerUI>) {
        populateBeers(beers)
    }

    private fun populateBeers(beersUI: List<BeerUI>?) {
        beersUI?.let {
            populateRecyclerView(BeerUIToAdapterModelMapper.map(it))
        }
    }

    private fun populateRecyclerView(beersAdapterModel: List<BeerAdapterModel>) {
        rv_beers_list?.apply {
            layoutManager = LinearLayoutManager(context)
            beersAdapter?.setData(beersAdapterModel)
            adapter = beersAdapter?.apply {
                updateAdapter(beersAdapterModel)
            }
            setHasFixedSize(true)
        }
    }

    override fun onSetBeerDetailListener(beerAdapterModel: BeerAdapterModel) {
        setBeerDetailListener(beerAdapterModel)
    }

    override fun onSetFavoriteBeerListener(beerAdapterModel: BeerAdapterModel) {
        setFavoriteBeerListener(beerAdapterModel)
    }
}
