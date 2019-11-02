package com.infosys.assignment.feed.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.infosys.assignment.api.RetrofitBuilder
import com.infosys.assignment.feed.api.ApiFeed
import com.infosys.assignment.feed.model.FeedResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class ViewModelFeed : ViewModel() {
    private val _title = MutableLiveData<String>()
    private val feeds: MutableLiveData<FeedResponse> = MutableLiveData()
    private val disposable = CompositeDisposable()

    val title: LiveData<String> get() = _title

    fun title(title: String) = _title.postValue(title)

    fun getFeed(): LiveData<FeedResponse> {
        if (feeds.value == null) {
            val t = RetrofitBuilder.build()
                .create(ApiFeed::class.java)
                .getFeed()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : DisposableSingleObserver<FeedResponse>() {
                    override fun onSuccess(response: FeedResponse) {
                        feeds.value = response
                    }

                    override fun onError(throwable: Throwable) {
                        throwable.printStackTrace()
                    }
                })
            this.disposable.add(t)
        }
        return feeds
    }

    public override fun onCleared() {
        this.disposable.dispose()
        super.onCleared()
    }
}