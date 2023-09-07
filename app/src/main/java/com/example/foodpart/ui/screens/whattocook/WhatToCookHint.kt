package com.example.foodpart.ui.screens.whattocook

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun WhatToCookHint(
    viewModel: WhatToCookScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val isHintShow by viewModel.isHintShow.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colors.surface,
                shape = MaterialTheme.shapes.medium
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly

    ) {
        Row(

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(8.dp, 3.dp),
                text = "راهنما",
                style = MaterialTheme.typography.body1
            )
            Spacer(
                modifier = Modifier
                    .weight(1F)
            )

            if (isHintShow) {
                IconButton(
                    onClick = { viewModel.setHintShow(false) }) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        contentDescription = "close"
                    )
                }
            } else {
                IconButton(
                    onClick = { viewModel.setHintShow(true) }) {
                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowDown,
                        contentDescription = "hint"
                    )
                }
            }


        }
        if (isHintShow) Text(
            modifier = Modifier
                .padding(8.dp, bottom = 16.dp),
            text = "کاربر گرامی مواد اولیه ی موجود را در قسمت “تو خونه چی داری ؟” وارد کرده و با علامت “،” از یکدیگر جدا کنید سپس کل زمانی که دارین را به دقیقه در بخش “چقدر وقت داری ؟” وارد کنین. سطح دستور پخت را انتخاب کرده و کلیک جستجو را بزنید تا با توجه به اطلاعات شما غذاهای پیشنهاد را نمایش دهیم.",
            style = MaterialTheme.typography.body1.copy(textAlign = TextAlign.Start)
        )

    }

}
