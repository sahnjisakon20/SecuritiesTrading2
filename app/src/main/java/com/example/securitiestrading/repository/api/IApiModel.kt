package com.example.securitiestrading.repository.api

import com.example.securitiestrading.data.bean.BwibbuAllBean
import com.example.securitiestrading.data.bean.StockDayAllBean
import com.example.securitiestrading.data.bean.StockDayAvgAllBean
import kotlinx.coroutines.flow.Flow

interface IApiModel {
    fun getBwibbuAll(): Flow<List<BwibbuAllBean>>
    fun getStockDayAvgAllBean(): Flow<List<StockDayAvgAllBean>>
    fun getStockDayAllBean(): Flow<List<StockDayAllBean>>
}