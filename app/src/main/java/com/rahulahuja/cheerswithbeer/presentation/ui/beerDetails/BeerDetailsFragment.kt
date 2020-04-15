package com.rahulahuja.cheerswithbeer.presentation.ui.beerDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.rahulahuja.cheerswithbeer.R
import com.rahulahuja.cheerswithbeer.presentation.viewModel.beerDetails.BeerDetailsViewModel

class BeerDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = BeerDetailsFragment()
    }

    private lateinit var viewModel: BeerDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_beer_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BeerDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
