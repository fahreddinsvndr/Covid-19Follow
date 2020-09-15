package com.fahreddinsevindir.covid19takip.view.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.fahreddinsevindir.covid19takip.R
import com.fahreddinsevindir.covid19takip.view.adapter.CountryAdapter
import com.fahreddinsevindir.covid19takip.view.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.fragment_country.*


class CountryFragment : Fragment() {
    private lateinit var viewModel:CountryViewModel
    private val countryAdapter=CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.refreshData()

        countryList.layoutManager = LinearLayoutManager(context)
        countryList.adapter = countryAdapter

        swipeRefreshLayout.setOnRefreshListener {
            countryList.visibility = View.GONE
            countryError.visibility = View.GONE
            countryLoading.visibility = View.VISIBLE
            viewModel.refreshFromAPI()
            swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()
    }
    private fun observeLiveData(){
      viewModel.countries.observe(viewLifecycleOwner, Observer { countries->
          countries?.let {
              countryList.visibility = View.VISIBLE
              countryAdapter.updateCountryList(countries)
          }
      })
        viewModel.countryError.observe(viewLifecycleOwner, Observer { error->
            error?.let {
                if (it){
                    countryError.visibility = View.VISIBLE
                }else{
                    countryError.visibility= View.GONE
                }
            }
        })
        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading->
            loading?.let {
                if (it){
                    countryLoading.visibility = View.VISIBLE
                    countryList.visibility = View.GONE
                    countryError.visibility=View.GONE
                }else{
                    countryLoading.visibility=View.GONE

                }
            }
        })
    }





}