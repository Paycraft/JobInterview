package com.joedae.propertylist.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joedae.propertylist.R
import com.joedae.propertylist.data.Property
import com.joedae.propertylist.data.db.SetFavorite
import com.joedae.propertylist.databinding.ActivityListViewBinding


class CustomAdapter(val mList: List<Property>, val setFavorite: SetFavorite) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ActivityListViewBinding.inflate(LayoutInflater.from(parent.context))

        Log.i("Test", "created viewholder")
        return PropertyViewholder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is PropertyViewholder) {
            holder.binder(mList[position])
        }

        Log.i("Test", "binding")
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class PropertyViewholder(val binding: ActivityListViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun binder(property: Property) {
            binding.propertyTitle.text = property.listing.localization.de.text.title
            binding.price.text = property.listing.prices.buy.price + " " + property.listing.prices.currency
            binding.address.text = property.listing.address.street + ", " + property.listing.address.locality
            binding.firstImage.setImageResource(R.drawable.image1)

            binding.favoritesButton.setOnClickListener {
                setFavorite.onSetFavorite(property.id)
            }
        }

    }
}