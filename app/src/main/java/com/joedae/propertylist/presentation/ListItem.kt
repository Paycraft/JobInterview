package com.joedae.propertylist.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
        items(properties) { property ->
            Row {
                Text(text = property.listing.prices.buy.price.toString())
                Image(painter = painterResource(R.drawable.image1), contentDescription = "Main Image")
            }

            Column {
                Text(text = property.listing.localization.de.text.title)
                Text(text = property.listing.address.locality + property.listing.address.street)
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
@Preview
fun ListItemPreview() {
    val property = Property(
        "104123262", false, ListingType("TOP"), ListerBranding(
            "https://media2.homegate.ch/t_customer_logo/logos/l_heia_v1.png", "SMG Swiss Marketplace Group AG", "Homegate",
            Address("Zürich", "CH", "ZH", "Werdstrasse 21", "8004"), false, false, false, "smg-swiss-marketplace-group-ag"
        ), Listing(
            "104123262",
            "BUY",
            emptyList(),
            Prices("CHF", Buy("ALL", 9999999, "ONETIME")),
            Address("La Brévine", "CH", "NE", "Musterstrasse 999", "2406"),
            Characteristics(1.0, 1, 1, 1),
            Localization(
                "de", De(
                    emptyList(), com.joedae.propertylist.data.Text("Schloss"), emptyList()
                )
            ), Lister("+41 44 711 86 67", "https://media2.homegate.ch/t_customer_logo/logos/l_heia_v1.png")
        )
    )

    ListItem(listOf(property))
}
