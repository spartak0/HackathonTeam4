package com.example.hackathonteam4.ui.details_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackathonteam4.domain.model.Lamp
import com.example.hackathonteam4.domain.model.NetworkResult
import com.example.hackathonteam4.domain.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
) : ViewModel() {

    private val _lamp = MutableStateFlow<NetworkResult<Lamp>>(NetworkResult.Success(Lamp()))
    val lamp = _lamp.asStateFlow()

    fun fetchLamp(barcodeId: String) {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            networkRepository.getCurrentLamp(barcodeId).collect {
                _lamp.value = it
            }
        }
    }
}