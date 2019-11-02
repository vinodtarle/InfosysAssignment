package com.infosys.assignment.feed.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.infosys.assignment.R
import com.infosys.assignment.connection.NetworkConnection.Companion.isInternet
import com.infosys.assignment.databinding.FragmentFeedBinding
import com.infosys.assignment.feed.adapter.AdapterFeed
import com.infosys.assignment.feed.model.FeedResponse
import com.infosys.assignment.feed.viewmodel.ViewModelFeed

class FragmentFeed : Fragment() {
    private lateinit var binding: FragmentFeedBinding
    private lateinit var adapter: AdapterFeed
    private lateinit var providers: ViewModelFeed

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentFeedBinding.inflate(layoutInflater, container, false)
        this.binding.swipeRefresh.setOnRefreshListener { getFeeds() }

        this.adapter = AdapterFeed(context!!)
        this.binding.rvFeeds.layoutManager = LinearLayoutManager(context)
        this.binding.rvFeeds.setHasFixedSize(true)
        this.binding.rvFeeds.adapter = this.adapter

        getFeeds()

        return this.binding.root
    }

    private fun getFeeds() {
        if (isInternet()) {
            this.binding.swipeRefresh.isRefreshing = true

            this.providers = ViewModelProviders.of(activity!!)
                .get(ViewModelFeed::class.java)

            this.providers.getFeed()
                .observe(viewLifecycleOwner, Observer<FeedResponse> { response ->
                    this.providers.title(title = response.title)
                    this.adapter.setData(data = response.rows)
                    this.binding.swipeRefresh.isRefreshing = false
                })
        } else {
            Toast.makeText(
                this.context,
                getString(R.string.msgNoInternet),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
