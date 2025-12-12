package com.example.securitiestrading.repository.api

import com.example.securitiestrading.data.bean.BwibbuAllBean
import com.example.securitiestrading.data.bean.StockDayAllBean
import com.example.securitiestrading.data.bean.StockDayAvgAllBean
import com.example.securitiestrading.module.RetrofitApi.IRetrofitApi
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Factory(binds = [IApiModel::class])
class ApiModel: IApiModel, KoinComponent {
    private val apiModel: IRetrofitApi by inject()

    override fun getBwibbuAll(): Flow<List<BwibbuAllBean>> {
        return apiModel.getbwibbuAll()
    }

    override fun getStockDayAvgAllBean(): Flow<List<StockDayAvgAllBean>> {
        return apiModel.getStockDayAvgAll()
    }

    override fun getStockDayAllBean(): Flow<List<StockDayAllBean>> {
        return apiModel.getStockDayAll()
    }

}