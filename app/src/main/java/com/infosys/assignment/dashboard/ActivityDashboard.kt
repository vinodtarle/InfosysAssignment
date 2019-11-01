package com.infosys.assignment.dashboard

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.infosys.assignment.R
import com.infosys.assignment.databinding.ActivityDashboardBinding

class ActivityDashboard : AppCompatActivity() {
    private lateinit var context: Context
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
        this.context = this
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
    }
}

