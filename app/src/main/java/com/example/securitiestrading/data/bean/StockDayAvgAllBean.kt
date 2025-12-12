package com.example.securitiestrading.data.bean

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class StockDayAvgAllBean(
    @SerializedName("Code")
    val code: String?,
    @SerializedName("Name")
    val name: String?,
    @SerializedName("ClosingPrice")
    val closingPrice: String?,
    @SerializedName("MonthlyAveragePrice")
    val monthlyAverage: String?
): Parcelable
