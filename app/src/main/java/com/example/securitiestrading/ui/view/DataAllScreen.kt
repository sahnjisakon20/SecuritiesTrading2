package com.example.securitiestrading.ui.view

import LoadingDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.securitiestrading.data.UiState
import com.example.securitiestrading.R

import com.example.securitiestrading.ui.widget.DialogType
import com.example.securitiestrading.viewModel.FidoViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import com.example.securitiestrading.data.bean.StockDataBean
import com.example.securitiestrading.ui.theme.CommonMargin
import com.example.securitiestrading.ui.widget.SortType
import com.example.securitiestrading.ui.widget.StockInfoDialog

//註冊頁
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataAllScreen() {
    val context = LocalContext.current
    val viewModel = koinViewModel<FidoViewModel>()
    var showDialogState by remember { mutableStateOf<DialogType?>(null) }
    val loadingDialogState = viewModel.loadingState.collectAsStateWithLifecycle()
    var listStockData: List<StockDataBean> by remember { mutableStateOf(mutableListOf()) }
    val uiState by remember { mutableStateOf(viewModel.observeStocks())}
    var sortType by remember { mutableStateOf(SortType.DESC) }   // 預設降序
    var showSheet by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<StockDataBean?>(null) }


    LaunchedEffect(viewModel) {
        uiState.collectLatest { state ->
            when (state) {
                is UiState.Success -> {
                    listStockData = state.data
                }
                is UiState.Error -> {

                }
                else -> {}
            }
        }
    }

    // 排序後的資料
    val sortedList = when (sortType) {
        SortType.ASC  -> listStockData.sortedBy { it.code }
        SortType.DESC -> listStockData.sortedByDescending { it.code }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(context.getString(R.string.activity_title)) },
                actions = {
                    IconButton(onClick = { showSheet = true }) {
                        Icon(Icons.Default.Menu,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        //列表
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(CommonMargin.m3),
        ) {
            items(sortedList) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { selectedItem = item }
                        .padding(CommonMargin.m1)
                        .border(
                            width = CommonMargin.m0,
                            color = Color(0xFF6A5ACD),         // ← 邊框顏色
                            shape = RoundedCornerShape(CommonMargin.m4)   // ← 要與 Card shape 相同
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Black
                    )

                ) {
                    Column(modifier = Modifier.padding(CommonMargin.m3)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            item.code?.let {
                                Text(it, style = MaterialTheme.typography.titleMedium,  color = Color.White) }
                            Spacer(Modifier.width(CommonMargin.m2))
                            item.name?.let {
                                Text(it, style = MaterialTheme.typography.bodyMedium,  color = Color.White) }
                        }

                        Spacer(Modifier.height(CommonMargin.m2))

                        // Price row: Open / Close / MonthlyAvg / Change
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            val defaultData = context.getString(R.string.text_default_data)
                            Column {
                                val changeValue = item.change?.toDoubleOrNull() ?: 0.0
                                val changeColor = if (changeValue >= 0.0) Color.Red else Color.Green

                                Text(
                                    context.getString(R.string.text_openingPrice) +
                                        (item.openingPrice ?: defaultData),
                                    color = Color.White
                                )
                                Text(
                                    context.getString(R.string.text_highestPrice) +
                                            (item.openingPrice ?: defaultData),
                                    color = Color.White
                                )
                                Text(
                                    context.getString(R.string.text_change) +
                                            (item.change ?: defaultData),
                                    color = changeColor
                                )
                                Text(
                                    context.getString(R.string.text_transaction) +
                                            (item.transaction ?: defaultData),
                                    color = Color.White
                                )
                                Text(
                                    context.getString(R.string.text_tradeVolume) +
                                            (item.tradeVolume ?: defaultData),
                                    color = Color.White
                                )
                            }

                            Column(horizontalAlignment = Alignment.End) {
                                val closeText = item.closingPrice ?: "—"
                                val monthAvg = item.monthlyAverage
                                val closeColor = when {
                                    monthAvg == null || item.closingPrice == null -> MaterialTheme.colorScheme.onSurface
                                    item.closingPrice > monthAvg -> Color.Red
                                    item.closingPrice < monthAvg -> Color.Green
                                    else -> MaterialTheme.colorScheme.onSurface
                                }
                                Text(
                                    context.getString(R.string.text_closingPrice) +
                                            (item.closingPrice ?: defaultData),
                                    color = closeColor
                                )
                                Text(
                                    context.getString(R.string.text_lowestPrice) +
                                            (item.lowestPrice ?: defaultData),
                                    color = Color.White
                                )
                                Text(
                                    context.getString(R.string.text_monthlyAverage) +
                                            (item.monthlyAverage ?: defaultData),
                                    color = Color.White
                                )
                                Text(
                                    "",
                                    color = Color.White
                                )
                                Text(
                                    context.getString(R.string.text_tradeValue) +
                                            (item.tradeValue ?: defaultData),
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    // ⭐ Bottom Sheet
    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false }
        ) {
            Column(Modifier.padding(CommonMargin.m4)) {

                Text(context.getString(R.string.text_sort_method),
                    style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(CommonMargin.m4))

                Text(
                    context.getString(R.string.text_sort_ascending),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            sortType = SortType.ASC
                            showSheet = false
                        }
                        .padding(CommonMargin.m4)
                )

                Text(
                    context.getString(R.string.text_sort_descending),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            sortType = SortType.DESC
                            showSheet = false
                        }
                        .padding(CommonMargin.m4)
                )
            }
        }
    }

    selectedItem?.let { item ->
        StockInfoDialog(
            pe = item.peRatio?.toDoubleOrNull() ?: 0.0,
            dividendYield = item.dividendYield?.toDoubleOrNull() ?: 0.0,
            pb = item.pbRatio?.toDoubleOrNull() ?: 0.0,
            onDismiss = { selectedItem = null }
        )
    }

    if (loadingDialogState.value) {
        LoadingDialog()
    }
}

fun Double.format2(): String = String.format("%.2f", this)



