package com.example.foodpart.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.foodpart.R


val Typography = Typography(
    h1 = TextStyle(
        fontSize = 24.sp,
        fontFamily = FontFamily(Font(R.font.iranyekan_bold)),
        fontWeight = FontWeight(800),
        letterSpacing = 0.36.sp,
        textAlign = TextAlign.Center
    ),
    h2 = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily(Font(R.font.iranyekan_bold)),
        fontWeight = FontWeight(800),
        letterSpacing = 0.3.sp,
        textAlign = TextAlign.Center
    ),
    h3 = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.iranyekan_regular)),
        fontWeight = FontWeight(700),
        textAlign = TextAlign.Center
    ),
    subtitle1 = TextStyle(
        fontSize = 13.sp,
        lineHeight = 19.8.sp,
        fontFamily = FontFamily(Font(R.font.iranyekan_regular)),
        fontWeight = FontWeight(400),
        textAlign = TextAlign.Center
    ),
    subtitle2 = TextStyle(
        fontSize = 11.sp,
        lineHeight = 16.7.sp,
        fontFamily = FontFamily(Font(R.font.iranyekan_mediom)),
        fontWeight = FontWeight(400),
        textAlign = TextAlign.Center
    )

)
