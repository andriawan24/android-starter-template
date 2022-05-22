package com.andriawan.common_ui

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp
    ),
    h5 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp
    ),
    h6 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)
