package com.allegorit.mvvmpoc.data.repository

import com.allegorit.mvvmpoc.data.api.ApiService
import com.allegorit.mvvmpoc.data.model.Museum
import com.allegorit.mvvmpoc.data.model.OperationResult

class MuseumRemoteRepository(private val apiService: ApiService) : MuseumRemoteDataSource {
    override suspend fun retrieveMuseum(): OperationResult<Museum> {
        try {
            val response = apiService.getMuseums()
            response.let {
                return if (it.isSuccessful && it.body() != null) {
                    val data = it.body()?.data
                    OperationResult.Success(data)
                } else {
                    val message = it.body()?.msg
                    OperationResult.Error(Exception(message))
                }
            }
        } catch (e: Exception) {
            return OperationResult.Error(e)
        }
    }
}
