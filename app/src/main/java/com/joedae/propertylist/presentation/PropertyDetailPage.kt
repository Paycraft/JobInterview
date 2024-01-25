package com.joedae.propertylist.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.joedae.propertylist.R
import com.joedae.propertylist.data.PropertyDetailResponse

@Composable
fun PDP(
    loading: LiveData<Boolean>,
    detailData: LiveData<PropertyDetailResponse>,
    onNavigateUp: () -> Unit
) {

    val isLoading by loading.observeAsState()
    val detailProperty by detailData.observeAsState()

    val switzerland = LatLng(47.0, 8.0)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(switzerland, 6f)
    }

    if (isLoading!!) {
        Text("Loading...")
    } else {
        val detail = detailProperty?.listings?.get(0)
        if (detail != null) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
            ) {
                Marker(
                    state = MarkerState(position = switzerland),
                    title = "MyPosition",
                    snippet = "This is a description of this Marker",
                    draggable = true
                )
            }

            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 10.dp)
                ) {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_back),
                            contentDescription = "Go back"
                        )
                    }
                }
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