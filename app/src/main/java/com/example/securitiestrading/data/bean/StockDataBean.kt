package com.example.securitiestrading.data.bean

import com.google.gson.annotations.SerializedName

data class StockDataBean (
    @SerializedName("Date")
    val date: String?,
    @SerializedName("Code")
    val code: String?,
    @SerializedName("Name")
    val name: String?,
    @SerializedName("PEratio")
    val peRatio: String?,
    @SerializedName("DividendYield")
    val dividendYield: String?,
    @SerializedName("PBratio")
    val pbRatio: String?,
    @SerializedName("TradeVolume")
    val tradeVolume: String?,
    @SerializedName("TradeValue")
    val tradeValue: String?,
    @SerializedName("OpeningPrice")
    val openingPrice: String?,
    @SerializedName("HighestPrice")
    val highestPrice: String?,
    @SerializedName("LowestPrice")
    val lowestPrice: String?,
    @SerializedName("ClosingPrice")
    val closingPrice: String?,
    @SerializedName("Change")
    val change: String?,
    @SerializedName("Transaction")
    val transaction: String?,
    @SerializedName("MonthlyAveragePrice")
    val monthlyAverage: String?
)