package com.allegorit.mvvmpoc.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allegorit.mvvmpoc.data.model.OperationResult
import com.allegorit.mvvmpoc.data.repository.DbRepository
import com.allegorit.mvvmpoc.data.repository.MuseumRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MuseumViewModel(
    private val remoteRepository: MuseumRemoteDataSource,
    private val dbRepository: DbRepository
) : ViewModel() {

    val museum = dbRepository.getMuseums()
    val isViewLoading = MutableLiveData<Boolean>()
    val onMessageError = MutableLiveData<Any>()
    val isEmptyList = MutableLiveData<Boolean>()


    fun loadMuseum() {
        isViewLoading.postValue(true)
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                remoteRepository.retrieveMuseum()
            }
            isViewLoading.postValue(false)
            when (response) {
                is OperationResult.Success -> {
                    response.data?.let {
                        if (it.isNotEmpty()) dbRepository.sync(it)
                    }
                }
                is OperationResult.Error -> {
                    onMessageError.postValue(response.exception)
                }
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}