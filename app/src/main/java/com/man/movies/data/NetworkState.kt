package com.man.movies.data

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED,
}

class NetworkState(val status: Status, val msg: String) {
    companion object {
        val LOADED: NetworkState
        val LOADING: NetworkState
        val ERROR: NetworkState
        val ENDOFLIST: NetworkState

        init {
            LOADED = NetworkState(Status.SUCCESS, "Success")
            LOADING = NetworkState(Status.RUNNING, "Running")
            ERROR = NetworkState(Status.FAILED, "Error")
            ENDOFLIST = NetworkState(Status.FAILED, "End of List")
        }
    }
}