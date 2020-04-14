package com.rahulahuja.cheerswithbeer.presentation.ui.beers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.rahulahuja.cheerswithbeer.R
import com.rahulahuja.cheerswithbeer.presentation.viewModel.beers.BeersViewModel

class BeersFragment : Fragment() {

    companion object {
        fun newInstance() = BeersFragment()
    }

    private lateinit var viewModel: BeersViewModel

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
        // TODO: Use the ViewModel
    }

}
