package com.infosys.assignment.feed.model

data class FeedResponse(
    val title: String = "",
    val rows: List<Feed>? = null,
    val throwable: Throwable? = null
)