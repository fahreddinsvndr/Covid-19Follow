package com.fahreddinsevindir.covid19takip.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.fahreddinsevindir.covid19takip.R
import com.fahreddinsevindir.covid19takip.databinding.ItemCountryBinding
import com.fahreddinsevindir.covid19takip.view.model.Model
import com.fahreddinsevindir.covid19takip.view.model.Results

import com.fahreddinsevindir.covid19takip.view.view.CountryFragmentDirections
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter(val countryList: ArrayList<Model>):RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(),CountryClickListener {
    class CountryViewHolder(var view: ItemCountryBinding):RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemCountryBinding>(inflater,R.layout.item_country,parent,false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.country = countryList[position]
        holder.view.listener = this
    }
    fun updateCountryList(newCountryList: List<Model>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClicked(v: View) {
        val uuid =v.countryUuidText.text.toString().toInt()
        val action= CountryFragmentDirections.actionCountryFragmentToCountryDetailsFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }
}