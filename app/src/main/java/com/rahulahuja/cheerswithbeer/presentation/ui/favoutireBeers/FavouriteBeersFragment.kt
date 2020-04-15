package com.rahulahuja.cheerswithbeer.presentation.ui.favoutireBeers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.rahulahuja.cheerswithbeer.R
import com.rahulahuja.cheerswithbeer.presentation.viewModel.favoutireBeers.FavouriteBeersViewModel

class FavouriteBeersFragment : Fragment() {

    companion object {
        fun newInstance() = FavouriteBeersFragment()
    }

    private lateinit var viewModel: FavouriteBeersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourite_beers, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavouriteBeersViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
