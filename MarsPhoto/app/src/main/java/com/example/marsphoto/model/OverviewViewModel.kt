package com.example.marsphoto.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsphoto.MarsApiStatus
import com.example.marsphoto.data.MarsPhoto
import com.example.marsphoto.network.MarsApi
import kotlinx.coroutines.launch
import java.lang.Exception

class OverviewViewModel : ViewModel() {

    private var _status = MutableLiveData<MarsApiStatus>()
    var status: LiveData<MarsApiStatus> = _status

    private var _photos = MutableLiveData<List<MarsPhoto>>()
    var photos: LiveData<List<MarsPhoto>> = _photos

    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        viewModelScope.launch {
            _status.value = MarsApiStatus.LOADING
            try {
                _photos.value = MarsApi.retrofitService.getPhotos()
                _status.value = MarsApiStatus.SUCCESS
            } catch (e: Exception) {
                _status.value = MarsApiStatus.ERROR
            }
        }
    }
}