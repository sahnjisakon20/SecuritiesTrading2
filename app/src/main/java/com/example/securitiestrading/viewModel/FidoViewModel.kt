package com.example.securitiestrading.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.securitiestrading.data.UiState
import com.example.securitiestrading.data.bean.BwibbuAllBean
import com.example.securitiestrading.data.bean.StockDataBean
import com.example.securitiestrading.data.bean.StockDayAllBean
import com.example.securitiestrading.data.bean.StockDayAvgAllBean
import com.example.securitiestrading.repository.IRepository
import com.example.securitiestrading.ui.widget.DialogType
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.koin.android.annotation.KoinViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.HttpException

// 呼叫repo func並開放state讓UI層監聽
@KoinViewModel
class FidoViewModel() : ViewModel(), IViewModel, KoinComponent {
    private val fidoRepository: IRepository by inject()
    override val loadingState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val bwibbuAllState: MutableSharedFlow<UiState<List<BwibbuAllBean>>> = MutableSharedFlow()
    override val stockDayAvgAllState: MutableSharedFlow<UiState<List<StockDayAvgAllBean>>> = MutableSharedFlow()
    override val stockDayAllState: MutableSharedFlow<UiState<List<StockDayAllBean>>> = MutableSharedFlow()

//    override fun getBwibbuAll() {
//        viewModelScope.launch {
//            fidoRepository.getBwibbuAll()
//                .onStart { loadingState.value = true }
//                .catch { e ->
//                    bwibbuAllState.emit(handleRegisterStartErrorType(e))
//                }
//                .onCompletion { loadingState.value = false }
//                .collect { data ->
//                    bwibbuAllState.emit(UiState.Success(data))
//                }
//        }
//
//    }
//
//    override fun getStockDayAvgAll() {
//        viewModelScope.launch {
//            fidoRepository.getStockDayAvgAll()
//                .onStart { loadingState.value = true }
//                .catch { e ->
//                    stockDayAvgAllState.emit(handleRegisterStartErrorType(e))
//                }
//                .onCompletion { loadingState.value = false }
//                .collect { data ->
//                    stockDayAvgAllState.emit(UiState.Success(data))
//                }
//        }
//    }
//
//    override fun getStockDayAll() {
//        viewModelScope.launch {
//            fidoRepository.getStockDayAll()
//                .onStart { loadingState.value = true }
//                .catch { e ->
//                    stockDayAllState.emit(handleRegisterStartErrorType(e))
//                }
//                .onCompletion { loadingState.value = false }
//                .collect { data ->
//                    stockDayAllState.emit(UiState.Success(data))
//                }
//        }
//    }

    fun observeStocks(): StateFlow<UiState<List<StockDataBean>>> {
        val dayAvg = fidoRepository.getStockDayAvgAll()
        val day = fidoRepository.getStockDayAll()
        val bwibbu = fidoRepository.getBwibbuAll()

        return combine(bwibbu, dayAvg, day) { list1, list2, list3 ->
            // 三份資料都拿到之後合併
            mergeStockData(list1, list2, list3)
        }.map<List<StockDataBean>, UiState<List<StockDataBean>>> { UiState.Success(it) }
            .catch { emit(UiState.Error(DialogType.SYSTEM_ERROR)) }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(100),
                UiState.Loading
            )
    }

    fun mergeStockData(
        bwibbu: List<BwibbuAllBean>,
        avg: List<StockDayAvgAllBean>,
        day: List<StockDayAllBean>
    ): List<StockDataBean> {

        val bwibbuMap = bwibbu.associateBy { it.code ?: "" }
        val avgMap = avg.associateBy { it.code ?: "" }
        val dayMap = day.associateBy { it.code ?: "" }

        val keys = (bwibbuMap.keys + avgMap.keys + dayMap.keys)
            .filter { it.isNotBlank() }

        fun toDoubleSafe(s: String?) = s?.replace(",", "")?.toDoubleOrNull()

        return keys.map { code ->
            val bwibbu = bwibbuMap[code]
            val avg = avgMap[code]
            val day = dayMap[code]

            StockDataBean(
                date = bwibbu?.date,
                code = code,
                name = bwibbu?.name,
                peRatio = bwibbu?.peRatio,
                dividendYield = bwibbu?.dividendYield,
                pbRatio = bwibbu?.peRatio,
                tradeVolume = day?.tradeVolume,
                tradeValue = day?.tradeVolume,
                openingPrice = day?.openingPrice,
                highestPrice = day?.highestPrice,
                lowestPrice = day?.lowestPrice,
                closingPrice = day?.closingPrice,
                change = day?.change,
                transaction = day?.transaction,
                monthlyAverage = avg?.monthlyAverage
            )
        }
    }

    private fun handleRegisterStartErrorType(e: Throwable): UiState.Error {
        return try {
            if (e is HttpException) {
                val body = e.response()?.errorBody()?.string()
                Log.d("[aaron_tt][handleRegisterStartErrorType][HttpException]", body.toString())
                UiState.Error(DialogType.DEVICE_BINDED)
            } else {
                UiState.Error(DialogType.SYSTEM_ERROR)
            }
        } catch (e: Exception) {
            Log.d("[aaron_tt][handleRegisterStartErrorType][catch]", e.message.toString())
            UiState.Error(DialogType.SYSTEM_ERROR)
        }
    }
}