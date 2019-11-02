package com.infosys.assignment.feed.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.infosys.assignment.R
import com.infosys.assignment.databinding.ActivityDashboardBinding
import com.infosys.assignment.feed.viewmodel.ViewModelFeed


class ActivityDashboard : AppCompatActivity() {
    private val TAG = this.javaClass.simpleName
    private lateinit var context: Context
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setTitle()
    }

    private fun init() {
        this.context = this
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
    }


    private fun setTitle() {
        ViewModelProviders.of(this)
            .get(ViewModelFeed::class.java)
            .title
            .observe(this, Observer { _title ->
                supportActionBar.let { title = _title }
            })
    }
}
