package com.example.foodpart.ui.screens.whattocook

import android.graphics.drawable.Icon
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.example.foodpart.R

sealed class DefficultyItems(
    var icon: Int,
    val name: String
){
    object noMatter :DefficultyItems(R.drawable.check_circle_outline,"مهم نیست")
    object easy : DefficultyItems(R.drawable.check_circle_outline_not_selected,"آسان")
    object medium : DefficultyItems(R.drawable.check_circle_outline_not_selected,"متوسط")

    object hard : DefficultyItems(R.drawable.check_circle_outline_not_selected,"سخت")

}

val defficultyItemsList = listOf<DefficultyItems>(
    DefficultyItems.noMatter,
    DefficultyItems.easy,
    DefficultyItems.medium,
    DefficultyItems.hard
)
