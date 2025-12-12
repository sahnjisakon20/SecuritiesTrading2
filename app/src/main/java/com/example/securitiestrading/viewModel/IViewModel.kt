package com.example.securitiestrading.viewModel

import com.example.securitiestrading.data.UiState
import com.example.securitiestrading.data.bean.BwibbuAllBean
import com.example.securitiestrading.data.bean.StockDayAllBean
import com.example.securitiestrading.data.bean.StockDayAvgAllBean
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow

interface IViewModel {
    val loadingState: StateFlow<Boolean>
    val bwibbuAllState: MutableSharedFlow<UiState<List<BwibbuAllBean>>>
    val stockDayAvgAllState: MutableSharedFlow<UiState<List<StockDayAvgAllBean>>>

    val stockDayAllState: MutableSharedFlow<UiState<List<StockDayAllBean>>>
//
//    fun getBwibbuAll()
//    fun getStockDayAvgAll()
//    fun getStockDayAll()
}