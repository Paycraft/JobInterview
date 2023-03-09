package com.joedae.propertylist.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import com.joedae.propertylist.R
import com.joedae.propertylist.data.*

@Composable
fun PDP(loading: LiveData<Boolean>, detailData: LiveData<PropertyDetailResponse>) {

    val loading by loading.observeAsState()
    val detailProperty by detailData.observeAsState()


    if(loading!!) {
        Text("Loading...")
    } else {
        val detail = detailProperty?.listings?.get(0)
        if (detail != null) {
            Column {
                Image(
                    painterResource(R.drawable.main),
                    "Images",
                    Modifier.size(width = 393.dp, height = 303.dp)
                )
                Text(
                    detail.listing.localization.de.text.title,
                    fontSize = 27.sp,
                    fontWeight = W700
                )
                Text(
                    "Rent per month",
                    fontSize = 19.sp,
                    fontWeight = W400
                )
                Text(
                    detail.listing.prices.currency + " " + detail.listing.prices.buy.price.toString() + ".-",
                    fontSize = 27.sp,
                    fontWeight = W700
                )
                Row {
                    Text(
                        "Rooms",
                        fontSize = 19.sp,
                        fontWeight = W400
                    )
                    Spacer(modifier = Modifier.width(170.dp))
                    Text(
                        "Livingspace",
                        fontSize = 19.sp,
                        fontWeight = W400
                    )
                }
                Row {
                    Icon(
                        painter = painterResource(R.drawable.home),
                        contentDescription = "rooms icon",
                        modifier = Modifier.size(21.dp, 24.dp)
                    )
                    Text(
                        detail.listing.characteristics.numberOfRooms.toString(),
                        fontSize = 27.sp,
                        fontWeight = W700
                    )
                    Spacer(modifier = Modifier.width(170.dp))
                    Icon(
                        painter = painterResource(R.drawable.select),
                        contentDescription = "living space icon",
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        detail.listing.characteristics.livingSpace.toString() + " m²",
                        fontSize = 21.sp,
                        fontWeight = W700
                    )
                }
                Row {
                    Text(
                        "Available from",
                        fontSize = 19.sp,
                        fontWeight = W400
                    )
                    Spacer(modifier = Modifier.width(110.dp))
                    Text(
                        "Immediately",
                        fontSize = 19.sp,
                        fontWeight = W700
                    )
                }
                Image(
                    painter = painterResource(R.drawable.map),
                    contentDescription = "Map",
                    Modifier.size(width = 393.dp, height = 174.dp)
                )
                Text(
                    "Description",
                    fontSize = 27.sp,
                    fontWeight = W700
                )
                detail.listing.localization.de.text.description?.let {
                    Text(
                        it,
                        fontSize = 19.sp,
                        fontWeight = W400
                    )
                }
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
            adActive = false,
            isQualityPartner = false,
            isPremiumBranding = false,
            "smg-swiss-marketplace-group-ag"
        ), Listing(
            "104123262",
            "BUY",
            emptyList(),
            Prices("CHF", Buy("ALL", 9999999, "ONETIME")),
            Address("La Brévine", "CH", "NE", "Musterstrasse 999", "2406"),
            Characteristics(
                1,
                1,
                hasFireplace = true,
                9.5,
                1999,
                isMinergieGeneral = true,
                isWheelchairAccessible = true,
                hasSwimmingPool = true,
                3.5,
                1,
                2022,
                hasGarage = true,
                hasParking = true,
                1,
                1,
                hasBalcony = true,
                1,
                1,
                hasCableTv = true,
                hasNiceView = true,
                1,
                1,
                1,
                1,
                hasElevator = true,
                isNewBuilding = true,
                isMinergieCertified = true,
                isChildFriendly = true
            ),
            Localization(
                "de", De(
                    emptyList(),
                    Text(
                        "Schloss",
                        "echt krasse bude alta",
                        "im getho"
                    ),
                    emptyList()
                )
            ),
            Lister(
                accountActive = true,
                migratedToAws = true,
                "AGENCY",
                "https://media2.homegate.ch/t_customer_logo/logos/l_heia_v1.png",
                "SMG",
                "+41 44 711 86 67",
                "Homegate",
                "heia"
            )
        ), false
    )

//    PDP(property)
}