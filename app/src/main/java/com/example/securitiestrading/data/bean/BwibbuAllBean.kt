package com.example.securitiestrading.data.bean

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
@Parcelize
data class BwibbuAllBean(
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
    val pbRatio: String?
): Parcelable