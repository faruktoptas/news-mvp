package me.toptas.rssreader.model

/**
 * Created by ftoptas on 24/07/18.
 */
data class Error(val code: Int = 0,
                 var message: String = "") {
    companion object {
        const val ERROR_GENERIC = -1
        const val ERROR_NETWORK = -2

        fun generic() = Error(code = ERROR_GENERIC)

        fun network() = Error(code = ERROR_NETWORK)
    }

}