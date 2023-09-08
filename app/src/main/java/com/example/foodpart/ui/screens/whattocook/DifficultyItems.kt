package com.example.foodpart.ui.screens.whattocook

import com.example.foodpart.R

sealed class DifficultyItems(
    var icon: Int,
    val name: String
){
    object NoMatter :DifficultyItems(R.drawable.check_circle_outline_not_selected,"مهم نیست")
    object Easy : DifficultyItems(R.drawable.check_circle_outline_not_selected,"آسان")
    object Medium : DifficultyItems(R.drawable.check_circle_outline_not_selected,"متوسط")
    object Hard : DifficultyItems(R.drawable.check_circle_outline_not_selected,"سخت")

}

val difficultyItemsList = listOf(
    DifficultyItems.NoMatter,
    DifficultyItems.Easy,
    DifficultyItems.Medium,
    DifficultyItems.Hard
)
