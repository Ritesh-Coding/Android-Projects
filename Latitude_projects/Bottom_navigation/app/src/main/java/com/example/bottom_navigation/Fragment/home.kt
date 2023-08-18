package com.example.bottom_navigation.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bottom_navigation.Adapter.CustomAdapter
import com.example.bottom_navigation.ItemsViewModel
import com.example.bottom_navigation.R

class home : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.layout_home, container, false)

        //getting the recycler view by its id
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerview)


        //this creates a vertical layout manager
        recyclerView.layoutManager = LinearLayoutManager(this.context)  //here I made a change


        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ItemsViewModel(R.drawable.baseline_home_24, "Item " + i , "Button"))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerView.adapter = adapter


        return rootView
    }

}