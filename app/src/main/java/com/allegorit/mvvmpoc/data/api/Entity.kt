package com.allegorit.mvvmpoc.data.api

import com.allegorit.mvvmpoc.data.model.Museum

data class MuseumResponse(val status: Int?, val msg: String?, val data: List<Museum>?) {
    fun isSuccess(): Boolean = (status == 200)
}