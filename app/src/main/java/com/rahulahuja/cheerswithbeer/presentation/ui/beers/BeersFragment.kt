package com.rahulahuja.cheerswithbeer.presentation.ui.beers

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar

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
import com.rahulahuja.cheerswithbeer.utils.showError
import kotlinx.android.synthetic.main.fragment_beers.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.util.ArrayList

class BeersFragment : Fragment(), View.OnClickListener {

    private val viewModel: BeersViewModel by viewModel()

    private val beersAdapter: BeersAdapter by inject {
        parametersOf(
            favoriteBeerListener,
            beerDetailListener
        )
    }

    private val favoriteBeerListener: (BeerAdapterModel) -> Unit = { beerAdapterModel ->
        viewModel.handleFavoriteButton(BeerAdapterModelToBeerUiMapper.map(beerAdapterModel))
    }

    private val beerDetailListener: (BeerAdapterModel) -> Unit = { beerAdapterModel ->
        showBeerDetails(
            getBeerDetailsFragment(
                BeerDetailUI(
                    image = beerAdapterModel.image,
                    foodPairing = beerAdapterModel.foodPairing as ArrayList<String>
                )
            )
        )
    }

    private val actionErrorListener: (String) -> Unit = { errorMessage ->
        Snackbar.make(beers_parent, errorMessage, Snackbar.LENGTH_LONG).show()
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
        viewModel.isBeerSaveSuccess.observe(viewLifecycleOwner, Observer(::onIsBeerSaveSuccess))
        viewModel.isBeerRemovalSuccess.observe(viewLifecycleOwner, Observer(::onIsBeerRemovalSuccess))
        viewModel.isErrorLiveData.observe(this, Observer { onErrorReceived() })
        viewModel.areEmptyBeersLiveData.observe(this, Observer { onEmptyBeersReceived() })
        viewModel.isLoadingLiveData.observe(this, Observer(::onLoadingStateReceived))
    }

    private fun onEmptyBeersReceived() {
        showAlert(
            R.string.no_data_fetched,
            R.string.cancel,
            R.string.try_again)
    }

    private fun onLoadingStateReceived(isLoading: Boolean) {
        showLoadingView(isLoading)
    }

    private fun showLoadingView(isLoading: Boolean) {
        pb_loadingView.apply {
            visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun onErrorReceived() {
        showAlert(
            R.string.network_connection_error_title,
            R.string.cancel,
            R.string.try_again)
    }

    private fun showAlert(resIdTitle: Int, resIdNegativeButton: Int, resIdPositiveButton: Int) {
        context?.let {
            AlertDialog.Builder(it)
                .setTitle(resIdTitle)
                .setCancelable(false)
                .setNegativeButton(resIdNegativeButton) { _, _ -> (activity as MainActivity).finish() }
                .setPositiveButton(resIdPositiveButton) { _, _ -> viewModel.handleBeersLoad() }
                .show()
        }
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

    private fun onIsBeerSaveSuccess(isBeerSaveSuccess: Boolean) {
        if (!isBeerSaveSuccess) {
            showError(beers_parent, resources.getString(R.string.beer_save_failed))
        }
    }

    private fun onIsBeerRemovalSuccess(isBeerRemovalSuccess: Boolean) {
        if (!isBeerRemovalSuccess) {
            showError(beers_parent, resources.getString(R.string.beer_remove_failed))
        }
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
}
