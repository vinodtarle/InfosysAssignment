package com.infosys.assignment.feed.view

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.infosys.assignment.R
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityDashboardTest {

    @get:Rule
    var activityScenarioRule = ActivityTestRule(ActivityDashboard::class.java)

    private var activity: ActivityDashboard? = null

    @Before
    fun setUp() {
        this.activity = activityScenarioRule.activity
    }

    @Test
    fun testActivityCreated() {
        val view = activity!!.findViewById(R.id.layoutMain) as ConstraintLayout
        Assert.assertNotNull(view)
    }

    @Test
    fun testEvent() {
    }

    @After
    fun tearDown() {
        activity = null
    }
}