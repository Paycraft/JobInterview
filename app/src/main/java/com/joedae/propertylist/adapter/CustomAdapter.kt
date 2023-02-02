package com.joedae.propertylist.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.joedae.propertylist.R
import com.joedae.propertylist.data.Listing
import com.joedae.propertylist.data.Property
import com.joedae.propertylist.databinding.ActivityListViewBinding
import com.joedae.propertylist.databinding.ActivityMainBinding


class CustomAdapter(var mList: List<Property>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ActivityListViewBinding.inflate(LayoutInflater.from(parent.context))

        Log.i("Test", "created viewholder")
        return PropertyViewholder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is PropertyViewholder) {
            holder.binder(mList[position].listing)
        }

        Log.i("Test", "binding")
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class PropertyViewholder(val binding: ActivityListViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun binder(listing: Listing) {
            binding.propertyTitle.text = listing.localization.de.text.toString()
            binding.price.text = listing.prices.buy.price + " " + listing.prices.currency
            binding.address.text = listing.address.street + ", " + listing.address.locality
            binding.firstImage.setImageResource(R.drawable.image1)
        }

    }
}