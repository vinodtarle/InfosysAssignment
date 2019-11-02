package com.infosys.assignment.feed.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.infosys.assignment.api.RetrofitBuilder
import com.infosys.assignment.feed.api.ApiFeed
import com.infosys.assignment.feed.model.FeedResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class ViewModelFeed : ViewModel() {
    private var feeds: MutableLiveData<FeedResponse> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getFeed(): LiveData<FeedResponse> {
        if (feeds.value == null) {
            RetrofitBuilder.build()
                .create(ApiFeed::class.java)
                .getFeed()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : DisposableSingleObserver<FeedResponse>() {
                    override fun onSuccess(response: FeedResponse) {
                        feeds.value = response
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        }
        return feeds
    }
}