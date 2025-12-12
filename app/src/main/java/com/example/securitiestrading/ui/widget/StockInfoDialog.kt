package com.example.securitiestrading.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.securitiestrading.R
import com.example.securitiestrading.ui.theme.CommonMargin
import com.example.securitiestrading.ui.view.format2

@Composable
fun StockInfoDialog(
    pe: Double?,
    dividendYield: Double?,
    pb: Double?,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(context.getString(R.string.text_detail_data))
        },
        text = {
            Column {
                val defaultData = context.getString(R.string.text_default_data)
                Text(context.getString(R.string.text_peRatio) +
                        (pe?.format2() ?: defaultData))
                Spacer(Modifier.height(CommonMargin.m2))

                Text(context.getString(R.string.text_dividendYield) +
                        (dividendYield?.format2() ?: defaultData))
                Spacer(Modifier.height(CommonMargin.m2))

                Text(context.getString(R.string.text_pbRatio) +
                        (pb?.format2() ?: defaultData))
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(context.getString(R.string.text_dialog_confirm))
            }
        }
    )
}