package com.joedae.propertylist.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joedae.propertylist.R
import com.joedae.propertylist.data.*

@Composable
fun PDP(property: Property) {
    Column {
        Column {
            Image(
                painterResource(R.drawable.image1),
                "Images",
                Modifier.size(width = 393.dp, height = 303.dp)
            )
            Text(
                property.listing.localization.de.text.title,
                fontSize = 27.sp,
                fontWeight = W700
            )
            Text(
                "Rent per month",
                fontSize = 19.sp,
                fontWeight = W400
            )
            Text(
                property.listing.prices.currency + " " + property.listing.prices.buy.price.toString() + ".-",
                fontSize = 27.sp,
                fontWeight = W700
            )
            Row {
                Text("Rooms")
                Spacer(modifier = Modifier.width(200.dp))
                Text("Livingspace")
            }
            Row {
//                Icon(
//                    painter = painterResource(id = R.drawable.fav_filled),
//                    contentDescription = "favourite button"
//                )
                Text("Rooms")
                Spacer(modifier = Modifier.width(200.dp))
                Text("Livingspace")
            }
        }
    }
}

@Composable
@Preview
fun PDPPreview() {
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
            Characteristics(1.0, 1, 1, 1),
            Localization(
                "de", De(
                    emptyList(), com.joedae.propertylist.data.Text("Schloss"), emptyList()
                )
            ),
            Lister(
                "+41 44 711 86 67",
                "https://media2.homegate.ch/t_customer_logo/logos/l_heia_v1.png"
            )
        ), false
    )

    PDP(property)
}