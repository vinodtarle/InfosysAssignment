package com.infosys.assignment.feed.model

/*
* Feed is data model class to each feed instance,
* primary constructor with default value
* */
data class Feed(
    val title: String? = "",
    val description: String? = "",
    val imageHref: String = ""
)

