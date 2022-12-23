package com.joedae.propertylist.presentation

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewTreeLifecycleOwner
import com.joedae.propertylist.adapter.CustomAdapter
import com.joedae.propertylist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var propertyViewModel: PropertyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        propertyViewModel = PropertyViewModel()
        propertyViewModel.getListings()
        propertyViewModel.propertyData.observe(this) {
            val customerAdapter: CustomAdapter = CustomAdapter(this.applicationContext, layoutInflater, it.results)
            binding.list.adapter = customerAdapter
        }

        return super.onCreateView(name, context, attrs)
    }
}