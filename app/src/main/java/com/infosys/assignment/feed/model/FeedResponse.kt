package com.infosys.assignment.feed.model


/*
* FeedResponse is data model class API response
* With list of fees and title
* primary constructor with default value
* */
data class FeedResponse(
    val title: String = "",
    val rows: List<Feed>? = null
)