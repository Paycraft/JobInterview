package com.joedae.propertylist.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.joedae.propertylist.data.*

@Composable
fun PDP(property: Property) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Column() {
            Text(text = property.id)
        }
    }
}

@Composable
@Preview
fun PDPPreview() {

//    PDP(property)
}