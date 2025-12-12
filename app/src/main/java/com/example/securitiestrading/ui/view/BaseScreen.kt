package com.example.securitiestrading.ui.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.securitiestrading.ui.theme.CommonMargin
import com.example.securitiestrading.ui.widget.CustomAlertDialog
import com.example.securitiestrading.ui.widget.DialogType
import kotlin.let

@Composable
fun BaseScreen(innerPadding: PaddingValues, content: @Composable ColumnScope.() -> Unit) {
    BackHandler(enabled = true) { }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(
                top = innerPadding.calculateTopPadding() + CommonMargin.m8,
                bottom = innerPadding.calculateBottomPadding() + CommonMargin.m5
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }
}

@Composable
fun DialogDisplay(dialogType: DialogType?, onDismiss: () -> Unit) {
    dialogType?.let {
        CustomAlertDialog(
            type = dialogType,
            onConfirm = { onDismiss() },
            onDismiss = {}
        )
    }
}