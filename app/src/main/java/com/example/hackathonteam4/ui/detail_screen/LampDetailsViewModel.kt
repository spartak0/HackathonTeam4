package com.example.hackathonteam4.ui.detail_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackathonteam4.domain.model.Lamp
import com.example.hackathonteam4.domain.repository.LampRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LampDetailsViewModel @Inject constructor(
    private val lampRepository: LampRepository
) : ViewModel() {

    private val _lamp = MutableStateFlow(Lamp())
    val lamp : StateFlow<Lamp> = _lamp

    fun getLampDetails(barcodeId: String) = viewModelScope.launch(Dispatchers.IO) {
        lampRepository.getCurrentLamp(barcodeId).collect{
            _lamp.value=it
        }
    }
}