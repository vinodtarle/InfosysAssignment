package com.infosys.assignment.feed.view

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.infosys.assignment.feed.viewmodel.ViewModelFeed
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FragmentFeedTest {
    @get:Rule
    var activityTestRule = ActivityTestRule(ActivityDashboard::class.java)

    private val viewModel by lazy { ViewModelFeed() }
    private var activity: ActivityDashboard? = null

    @Before
    fun setUp() {
        this.activity = activityTestRule.activity
    }

    private fun setTitleValue(value: String) {
        viewModel.title(value)
    }

    // Success scenario -> validate title value -> result expected passed (OK)
    @Test
    fun testTitleUpdateSame() {
        // given
        val givenValue = "New title"
        setTitleValue(givenValue)

        // when
        viewModel.title("New title")

        // then
        Assert.assertSame("New title", viewModel.title.value)
    }

    // Failed scenario -> validate title value -> result expected failed (OK)
    @Test
    fun testTitleUpdateDifferent() {
        // given
        val givenValue = "Old Title"
        setTitleValue(givenValue)

        // when
        viewModel.title("New title")

        // then
        Assert.assertSame("New title", viewModel.title.value)
    }
}