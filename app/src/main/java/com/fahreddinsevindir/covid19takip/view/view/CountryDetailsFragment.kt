package com.fahreddinsevindir.covid19takip.view.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.fahreddinsevindir.covid19takip.R
import com.fahreddinsevindir.covid19takip.databinding.FragmentCountryDetailsBinding
import com.fahreddinsevindir.covid19takip.view.viewmodel.CountryDetailsViewModel
import kotlinx.android.synthetic.main.fragment_country_details.*


class CountryDetailsFragment : Fragment() {
    private var countryUuid = 0
    private lateinit var viewModel:CountryDetailsViewModel
    private lateinit var dataBinding:FragmentCountryDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_country_details,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            countryUuid = CountryDetailsFragmentArgs.fromBundle(
                it
            ).countryUuid
        }
        viewModel = ViewModelProviders.of(this).get(CountryDetailsViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)

        observeLiveData()
    }
    private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country->
            country?.let {
                dataBinding.selectedCountry = country

            }
        })

    }


}