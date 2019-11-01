package com.infosys.assignment.feed.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.infosys.assignment.databinding.ViewFeedBinding
import com.infosys.assignment.feed.model.Feed

class AdapterFeed internal constructor(
    private val context: Context
) : RecyclerView.Adapter<AdapterFeed.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(this.context)
    private var feeds = emptyList<Feed>()


    inner class ViewHolder(val binding: ViewFeedBinding) :
        RecyclerView.ViewHolder(binding.layoutMain)

    internal fun setData(feeds: List<Feed>) {
        this.feeds = feeds
        notifyDataSetChanged()
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewFeedBinding.inflate(this.inflater, parent, false)
        return ViewHolder(binding = binding)
    }

    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {
        holder.binding.feed = this.feeds[position]
    }

    override fun getItemCount(): Int {
        return this.feeds.size
    }
}