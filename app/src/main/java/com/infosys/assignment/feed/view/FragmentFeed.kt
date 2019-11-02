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
import com.infosys.assignment.databinding.FragmentFeedBinding
import com.infosys.assignment.feed.adapter.AdapterFeed
import com.infosys.assignment.feed.model.FeedResponse
import com.infosys.assignment.feed.viewmodel.ViewModelFeed

/*
* Fragment for light weight UI component
* For showing list of feed using RecyclerView to best scrolling performance
* this fragment attached with ActivityDashboard class with navigation component
* */
class FragmentFeed : Fragment() {
    private lateinit var binding: FragmentFeedBinding
    private lateinit var adapter: AdapterFeed
    private lateinit var providers: ViewModelFeed

    // Create view
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentFeedBinding.inflate(layoutInflater, container, false)
        this.binding.swipeRefresh.setOnRefreshListener { getFeeds() }

        // Feed Adapter one time init
        this.adapter = AdapterFeed(context!!)
        this.binding.rvFeeds.layoutManager = LinearLayoutManager(context)
        this.binding.rvFeeds.setHasFixedSize(true)
        this.binding.rvFeeds.adapter = this.adapter

        getFeeds()

        return this.binding.root
    }


    // Call API to get list of feeds data and set on adapter.
    private fun getFeeds() {
        // Show progressbar
        this.binding.swipeRefresh.isRefreshing = true

        // Get provider from ViewModel
        this.providers = ViewModelProviders.of(activity!!)
            .get(ViewModelFeed::class.java)

        this.providers.getFeed()
            .observe(viewLifecycleOwner, Observer<FeedResponse> { response ->
                if (response.rows != null) {

                    // Update actionbar title
                    this.providers.title(title = response.title)

                    // Filter response data base on null
                    this.adapter.setData(data = response.rows.filter {
                        !it.title.isNullOrEmpty()
                    })

                    // Hide progressbar
                    this.binding.swipeRefresh.isRefreshing = false
                } else {
                    // Show error message when API returns exception
                    Toast.makeText(
                        this.context,
                        getString(R.string.errorSomethingWentWrong),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
}
