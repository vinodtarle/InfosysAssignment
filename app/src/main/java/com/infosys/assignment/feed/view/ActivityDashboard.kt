package com.infosys.assignment.feed.view

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.infosys.assignment.R
import com.infosys.assignment.databinding.ActivityDashboardBinding
import com.infosys.assignment.feed.model.Feed

class ActivityDashboard : AppCompatActivity() {
    private lateinit var context: Context
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var adapter: AdapterFeed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        getFeed()
    }

    private fun init() {
        this.context = this
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)

        // adapter init
        this.adapter = AdapterFeed(context)
        this.binding.rvFeeds.layoutManager = LinearLayoutManager(this.context)
        this.binding.rvFeeds.setHasFixedSize(true)
        this.binding.rvFeeds.adapter = adapter

        // set onRefresh listener
        this.binding.swipeRefresh.setOnRefreshListener { getFeed() }
    }

    private fun getFeed() {
        ViewModelProviders.of(this)
            .get(ViewModelFeed::class.java)
            .getFeed()
            .observe(this, Observer {
                if (it.throwable != null) {
                    bindFeed(it.rows!!)
                } else {
                    Toast.makeText(
                        this.context,
                        "Something went wrong, Try again later!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    private fun bindFeed(feeds: List<Feed>) {
        this.adapter.setData(feeds = feeds)
    }
}