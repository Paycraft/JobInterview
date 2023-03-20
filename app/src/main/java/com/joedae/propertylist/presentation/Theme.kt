package com.joedae.propertylist.data

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.joedae.propertylist.R

val customFont = FontFamily(Font(R.font.opensans_normal))
val Typography = Typography(defaultFontFamily = customFont)

@Composable
fun MyApplicationTheme(content: @Composable () -> Unit) {
    MaterialTheme(typography = Typography, content = content)
}