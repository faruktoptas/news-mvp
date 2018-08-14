package me.toptas.rssreader.network

import me.toptas.rssreader.model.Error
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by ftoptas on 24/07/18.
 */
abstract class ApiCallback<T> : Callback<T> {


    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.code() == 200 && response.body() != null) {
            onSuccess(response.body()!!)
        } else {
            onFail(Error.generic())
        }
    }

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        onFail(Error.network())
    }

    abstract fun onSuccess(response: T)

    open fun onFail(error: Error) {

    }
}