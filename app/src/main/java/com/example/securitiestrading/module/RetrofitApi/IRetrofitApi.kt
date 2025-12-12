package com.example.securitiestrading.module.RetrofitApi

import com.example.securitiestrading.data.bean.BwibbuAllBean
import com.example.securitiestrading.data.bean.StockDayAllBean
import com.example.securitiestrading.data.bean.StockDayAvgAllBean
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface IRetrofitApi {
    // 取 BWIBBU_ALL
    @GET("exchangeReport/BWIBBU_ALL")
    fun getbwibbuAll(): Flow<List<BwibbuAllBean>>

    @GET("exchangeReport/STOCK_DAY_AVG_ALL")
    fun getStockDayAvgAll(): Flow<List<StockDayAvgAllBean>>

    @GET("exchangeReport/STOCK_DAY_ALL")
    fun getStockDayAll(): Flow<List<StockDayAllBean>>
}