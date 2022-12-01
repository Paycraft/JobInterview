package com.joedae.propertylist.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.joedae.propertylist.R
import com.joedae.propertylist.data.Property
import java.net.URL


class CustomAdapter(val applicationcContext: Context,val inflter: LayoutInflater, val propertyList: List<Property>) : BaseAdapter() {

    override fun getCount(): Int {
        return propertyList.size
    }

    override fun getItem(p0: Int): Any {
        return propertyList.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View? {
        var view = inflter.inflate(R.layout.activity_list_view, null)
        var imageview: ImageView = view.findViewById(R.id.firstImage)
        var title: TextView = view.findViewById(R.id.propertyTitle)
        var price: TextView = view.findViewById(R.id.price)
        var address: TextView = view.findViewById(R.id.address)

//        var inputStream = URL(propertyList.get(p0).listing.localization.de.attachments.first().url).openStream()
//        var bmp = BitmapFactory.decodeStream(inputStream)
//        imageview.setImageBitmap(bmp)
        imageview.setImageResource(R.drawable.image1)

        title.text = propertyList.get(p0).listing.localization.de.text.title
        price.text = propertyList.get(p0).listing.prices.buy.price + " " + propertyList.get(p0).listing.prices.currency
        address.text = propertyList.get(p0).listing.address.street + ", " + propertyList.get(p0).listing.address.locality

        return view
    }
}