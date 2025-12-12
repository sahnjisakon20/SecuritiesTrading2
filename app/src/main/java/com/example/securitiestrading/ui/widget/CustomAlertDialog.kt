package com.example.securitiestrading.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.securitiestrading.ui.theme.CommonMargin

@Composable
fun CustomAlertDialog(
    type: DialogType,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
//    val isRegisterLeave = type == DialogType.REGISTER_LEAVE
//    val isPermissions = type == DialogType.PERMISSION_CAMERA || type == DialogType.PERMISSION_NOTIFICATION

    Dialog(onDismissRequest = {}) {
        Card(
            modifier = Modifier
                .width(320.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(CommonMargin.m5),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Icon
//                Image(
//                    painter = painterResource(id = type.iconRes),
//                    contentDescription = "Alert Icon",
//                    modifier = Modifier.size(55.dp)
//                )

                // 標題
//                CommonTextBase(
//                    text = context.getString(type.titleRes),
//                    modifier = Modifier.padding(top = 15.dp),
//                    fontSize = 22.dp,
//                    color = Dark,
//                    bold = true
//                )

                // 說明文字
//                CommonTextS(
//                    text = context.getString(type.messageRes),
//                    modifier = Modifier.padding(top = 10.dp),
//                    color = Light,
//                    boldParts = when (type) {
//                        DialogType.LOGIN_SUCCESS -> {
//                            arrayListOf(Pair(context.getString(R.string.info_net), Default))
//                        }
//                        else -> emptyList()
//                    }
//                )

                // 確認按鈕
//                FidoButton(
//                    text = context.getString(
//                        if (isRegisterLeave) R.string.leave_register_confirm_btn
//                        else if (isPermissions) R.string.go_to_settings
//                        else R.string.default_confirm_btn
//                    ),
//                    modifier = Modifier.padding(vertical = 15.dp),
//                    colorTint = if (isRegisterLeave) Danger else Default,
//                    onClick = onConfirm
//                )

                // 取消按鈕
//                if (isRegisterLeave) {
//                    FidoButton(
//                        text = context.getString(R.string.leave_register_dismiss_btn),
//                        onClick = onDismiss,
//                        isFillStyle = false
//                    )
//                }
            }
        }
    }
}

enum class DialogType(
) {
    SYSTEM_ERROR,
    DEVICE_BINDED
}
