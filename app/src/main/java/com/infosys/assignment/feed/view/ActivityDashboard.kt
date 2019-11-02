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
import com.infosys.assignment.connection.NetworkConnection.Companion.isInternet
import com.infosys.assignment.databinding.ActivityDashboardBinding
import com.infosys.assignment.feed.adapter.AdapterFeed
import com.infosys.assignment.feed.model.FeedResponse
import com.infosys.assignment.feed.viewmodel.ViewModelFeed


class ActivityDashboard : AppCompatActivity() {
    private val TAG = this.javaClass.simpleName
    private lateinit var context: Context
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        bindView()
    }

    private fun init() {
        this.context = this
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        this.binding.swipeRefresh.setOnRefreshListener { bindView() }
    }

    private fun bindView() {
        if (isInternet()) {
            this.binding.swipeRefresh.isRefreshing = true
            ViewModelProviders.of(this)
                .get(ViewModelFeed::class.java)
                .getFeed()
                .observe(this, Observer<FeedResponse> { response ->
                    supportActionBar.let { title = response?.title }
                    val adapter = AdapterFeed(this.context)
                    this.binding.rvFeeds.layoutManager = LinearLayoutManager(context)
                    this.binding.rvFeeds.setHasFixedSize(true)
                    this.binding.rvFeeds.adapter = adapter
                    this.binding.swipeRefresh.isRefreshing = false
                    adapter.setData(response.rows)
                })
        } else {
            Toast.makeText(this.context, "No Internet", Toast.LENGTH_SHORT).show()
        }
    }
}

