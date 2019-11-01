package com.infosys.assignment.feed.api

import com.infosys.assignment.feed.model.FeedResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiFeed {
    @GET("facts.json")
    fun getFeed(): Single<FeedResponse>
}