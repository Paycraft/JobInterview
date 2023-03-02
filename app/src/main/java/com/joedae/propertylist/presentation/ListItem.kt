package com.joedae.propertylist.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joedae.propertylist.R
import com.joedae.propertylist.data.*
import com.joedae.propertylist.data.db.SetFavorite

@Composable
fun ListItem(properties: List<Property>, setFavorite: SetFavorite?) {
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
        items(properties) { property ->
            Row {
                Text(property.listing.prices.buy.price.toString())
                Image(
                    painter = painterResource(R.drawable.main),
                    contentDescription = "main image",
//                    modifier = Modifier.clickable {  }
                )
                if (!property.isFavorite) {
                    Icon(
                        painter = painterResource(R.drawable.fav_border),
                        contentDescription = "favourite button",
                        modifier = Modifier.clickable { setFavorite?.onSetFavorite(property.id) }
                    )
                } else {
                    Icon(
                        painter = painterResource(R.drawable.fav_filled),
                        contentDescription = "favourite button",
                        modifier = Modifier.clickable { setFavorite?.onSetFavorite(property.id) }
                    )
                }
            }

            Column {
                Text(property.listing.localization.de.text.title)
                Text(property.listing.address.locality + property.listing.address.street)
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
            "https://media2.homegate.ch/t_customer_logo/logos/l_heia_v1.png",
            "SMG Swiss Marketplace Group AG",
            "Homegate",
            Address("Zürich", "CH", "ZH", "Werdstrasse 21", "8004"),
            false,
            false,
            false,
            "smg-swiss-marketplace-group-ag"
        ), Listing(
            "104123262",
            "BUY",
            emptyList(),
            Prices("CHF", Buy("ALL", 9999999, "ONETIME")),
            Address("La Brévine", "CH", "NE", "Musterstrasse 999", "2406"),
            Characteristics(
                null,
                1,
                null,
                9.5,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                1,
                null,
                null,
                1,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            ),
            Localization(
                "de", De(
                    emptyList(),
                    com.joedae.propertylist.data.Text("Schloss", null, null),
                    emptyList()
                )
            ),
            Lister(
                null,
                null,
                null,
                "https://media2.homegate.ch/t_customer_logo/logos/l_heia_v1.png",
                null,
                "+41 44 711 86 67",
                null,
                null
            )
        ), false
    )

    ListItem(listOf(property), null)
}
