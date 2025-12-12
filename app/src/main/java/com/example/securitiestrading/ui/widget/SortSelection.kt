package com.example.securitiestrading.ui.widget

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.example.securitiestrading.data.bean.StockDataBean

@ExperimentalMaterial3Api
@Composable
fun SortSelection (sortType: SortType, stockList: List<StockDataBean>){

}

enum class SortType {
    ASC, DESC
}