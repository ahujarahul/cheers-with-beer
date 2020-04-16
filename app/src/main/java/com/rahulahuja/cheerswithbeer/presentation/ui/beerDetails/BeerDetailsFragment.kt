package com.rahulahuja.cheerswithbeer.presentation.ui.beerDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.rahulahuja.cheerswithbeer.R
import com.rahulahuja.cheerswithbeer.presentation.constants.BUNDLE_BEER_DETAIL
import com.rahulahuja.cheerswithbeer.presentation.models.BeerDetailUI
import com.rahulahuja.cheerswithbeer.presentation.ui.MainActivity
import com.rahulahuja.cheerswithbeer.presentation.viewModel.beerDetails.BeerDetailsViewModel

class BeerDetailsFragment : Fragment() {

    companion object {
        fun newInstance(beerDetailUi: BeerDetailUI) = BeerDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(BUNDLE_BEER_DETAIL, beerDetailUi)
            }
        }
    }

    private lateinit var viewModel: BeerDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_beer_details, container, false)
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).setTitle(resources.getString(R.string.beer_details))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BeerDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
