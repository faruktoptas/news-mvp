package me.toptas.rssreader.base

import me.toptas.rssreader.model.Error

/**
 * Created by ftoptas on 28/01/17.
 */

/**
 * Each view should extend from this interface
 */
interface BaseView{
    fun onFail(error:Error)
}
