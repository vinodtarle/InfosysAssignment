package com.infosys.assignment.feed.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.infosys.assignment.databinding.ViewFeedBinding
import com.infosys.assignment.feed.model.Feed

/*
* AdapterFeed class for Recyclerview to mangage views on UI
* */
class AdapterFeed internal constructor(
    private val context: Context
) : RecyclerView.Adapter<AdapterFeed.ViewHolder>() {

    // UI view
    private val inflater: LayoutInflater = LayoutInflater.from(this.context)

    // To store passed feed data
    private var data = emptyList<Feed>()

    inner class ViewHolder(val binding: ViewFeedBinding) :
        RecyclerView.ViewHolder(binding.layoutMain)

    // Every time set new updated feed list
    internal fun setData(data: List<Feed>) {
        this.data = data
        notifyDataSetChanged()
    }

    // Create view for items based on custom UI like view_feed
    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewFeedBinding.inflate(this.inflater, parent, false)
        return ViewHolder(binding = binding)
    }

    // Bind items on UI one for one item using binding
    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {
        // Set feed to bind on UI using Binding
        holder.binding.feed = this.data[position]
    }

    // return number of items in data list
    override fun getItemCount(): Int {
        return this.data.size
    }
}