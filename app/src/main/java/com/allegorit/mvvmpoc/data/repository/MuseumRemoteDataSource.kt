package com.allegorit.mvvmpoc.data.repository

import com.allegorit.mvvmpoc.data.model.Museum
import com.allegorit.mvvmpoc.data.model.OperationResult

interface MuseumRemoteDataSource {
    suspend fun retrieveMuseum(): OperationResult<Museum>
}