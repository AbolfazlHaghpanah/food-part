package com.example.foodpart.ui.screens.fooddetails

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodpart.R
import com.example.foodpart.ui.theme.green
import com.example.foodpart.ui.theme.red
import com.example.foodpart.ui.theme.yellow

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FoodDifficultyChip(
    viewModel: FoodDetailsViewModel = hiltViewModel()
) {
    val food by viewModel.food.collectAsState()
    Chip(
        onClick = {},
        border = BorderStroke(
            1.dp, color = when (food?.additionalInfo?.difficulty?.id) {
                "ppqrincaaop4cpp" -> green
                "yl9nm3vppgzco0g" -> yellow
                "xajip1pes5ljs3i" -> red
                else -> MaterialTheme.colors.surface
            }
        ),
        colors = ChipDefaults.chipColors(
            backgroundColor = when (food?.additionalInfo?.difficulty?.id) {
                "ppqrincaaop4cpp" -> green.copy(alpha = (0.1f))
                "yl9nm3vppgzco0g" -> yellow.copy(alpha = (0.1f))
                "xajip1pes5ljs3i" -> red.copy(alpha = (0.1f))
                else -> MaterialTheme.colors.secondary
            }
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.deficulity),
            contentDescription = "",
            tint = when (food?.additionalInfo?.difficulty?.id) {
                "ppqrincaaop4cpp" -> green
                "yl9nm3vppgzco0g" -> yellow
                "xajip1pes5ljs3i" -> red
                else -> MaterialTheme.colors.secondary
            }
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = food?.additionalInfo?.difficulty?.name?:"",
            style = MaterialTheme.typography.caption
        )
    }
}