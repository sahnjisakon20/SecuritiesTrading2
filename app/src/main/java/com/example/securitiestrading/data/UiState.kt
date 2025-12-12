package com.example.securitiestrading.data

import com.example.securitiestrading.ui.widget.DialogType


sealed class UiState<out T> {
    data object Idle : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
    data object Empty : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val type: DialogType) : UiState<Nothing>()
}