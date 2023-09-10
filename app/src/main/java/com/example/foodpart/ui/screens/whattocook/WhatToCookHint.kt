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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.foodpart.R

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
                modifier = Modifier.padding(8.dp),
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
                .padding(bottom = 24.dp, start = 8.dp, end = 8.dp),
            text = stringResource(R.string.what_to_cook_hint),
            style = MaterialTheme.typography.body1.copy(textAlign = TextAlign.Start)
        )

    }

}
