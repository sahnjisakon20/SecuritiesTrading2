package com.example.securitiestrading.repository

import com.example.securitiestrading.data.bean.BwibbuAllBean
import com.example.securitiestrading.data.bean.StockDayAllBean
import com.example.securitiestrading.data.bean.StockDayAvgAllBean
import com.example.securitiestrading.repository.api.IApiModel
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Factory(binds = [IRepository::class])
class Repository: IRepository, KoinComponent {
    private val fidoApiModel: IApiModel by inject()

    override fun getBwibbuAll(): Flow<List<BwibbuAllBean>> {
        return fidoApiModel.getBwibbuAll()
    }

    override fun getStockDayAvgAll(): Flow<List<StockDayAvgAllBean>> {
        return fidoApiModel.getStockDayAvgAllBean()
    }

    override fun getStockDayAll(): Flow<List<StockDayAllBean>> {
        return fidoApiModel.getStockDayAllBean()
    }

}