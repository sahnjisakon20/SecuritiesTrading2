package com.example.securitiestrading.repository

import com.example.securitiestrading.data.bean.BwibbuAllBean
import com.example.securitiestrading.data.bean.StockDayAllBean
import com.example.securitiestrading.data.bean.StockDayAvgAllBean
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getBwibbuAll(): Flow<List<BwibbuAllBean>>
    fun getStockDayAvgAll(): Flow<List<StockDayAvgAllBean>>
    fun getStockDayAll(): Flow<List<StockDayAllBean>>
}