package com.joedae.propertylist.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joedae.propertylist.R
import com.joedae.propertylist.data.*

@Composable
fun ListItem(properties: List<Property>) {
    Column (horizontalAlignment = Alignment.CenterHorizontally) {
        properties.forEach {
            Row {
                Text(text = it.listing.prices.buy.price)
                Image(painter = painterResource(R.drawable.image1), contentDescription = "Main Image")
            }

            Column {
                Text(text = it.listing.localization.de.text.title)
                Text(text = it.listing.address.locality)
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
@Preview
fun ListItemPreview() {
    val property = Property(
        "1", Listing(
            Prices("CHF", Buy("99")), Address("Mustertrasse", "Bern"), Localization(
                De(
                    emptyArray(), com.joedae.propertylist.data.Text("Schloss")
                )
            )
        ), false
    )
    ListItem(listOf(property))
}
