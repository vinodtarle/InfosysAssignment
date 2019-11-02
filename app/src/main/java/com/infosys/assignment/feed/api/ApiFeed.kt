package com.infosys.assignment.feed.api

import com.infosys.assignment.feed.model.FeedResponse
import io.reactivex.Single
import retrofit2.http.GET


/*
* ApiFeed interface to declare API method like GET, POST, UPDATE and DELETE
*
* */
interface ApiFeed {

    // Get method for get feed list response its return single FeedResponse class
    @GET("facts.json")
    fun getFeed(): Single<FeedResponse>
}