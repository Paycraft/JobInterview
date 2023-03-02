package com.joedae.propertylist.data

import kotlinx.serialization.*

@Serializable
data class Property (
    val id: String,
    val remoteViewing: Boolean,
    val listingType: ListingType,
    val listerBranding: ListerBranding,
    val listing: Listing,
    var isFavorite: Boolean
)

@Serializable
data class ListerBranding (
    val logoUrl: String,
    val legalName: String,
    val name: String,
    val address: Address,
    val adActive: Boolean,
    val isQualityPartner: Boolean,
    val isPremiumBranding: Boolean,
    val profilePageUrlKeyword: String
)

@Serializable
data class Address (
    val locality: String,
    val country: String,
    val region: String,
    val street: String,
    val postalCode: String,
    val geoCoordinates: GeoCoordinates? = null
)

@Serializable
data class GeoCoordinates (
    val accuracy: String?,
    val manual: Boolean?,
    val latitude: Double,
    val longitude: Double
)

@Serializable
data class Listing (
    val id: String,
    val offerType: String,
    val categories: List<String>,
    val prices: Prices,
    val address: Address,
    val characteristics: Characteristics,
    val localization: Localization,
    val lister: Lister
)

@Serializable
data class Characteristics (
    val cubage: Long?,
    val totalFloorSpace: Long,
    val hasFireplace: Boolean?,
    val numberOfRooms: Double,
    val yearBuilt: Long?,
    val isMinergieGeneral: Boolean?,
    val isWheelchairAccessible: Boolean?,
    val hasSwimmingPool: Boolean?,
    val ceilingHeight: Double?,
    val distanceShop: Long?,
    val yearLastRenovated: Long?,
    val hasGarage: Boolean?,
    val hasParking: Boolean?,
    val numberOfFloors: Long?,
    val lotSize: Long,
    val hasBalcony: Boolean?,
    val distanceMotorway: Long?,
    val livingSpace: Long,
    val hasCableTv: Boolean?,
    val hasNiceView: Boolean?,
    val distanceHighSchool: Long?,
    val distanceKindergarten: Long?,
    val distancePrimarySchool: Long?,
    val distancePublicTransport: Long?,
    val hasElevator: Boolean?,
    val isNewBuilding: Boolean?,
    val isMinergieCertified: Boolean?,
    val isChildFriendly: Boolean?
)

@Serializable
data class Lister (
    val accountActive: Boolean?,
    val migratedToAws: Boolean?,
    val type: String?,
    val logoUrl: String,
    val legalName: String?,
    val phone: String,
    val name: String?,
    val id: String?,
)

@Serializable
data class Localization (
    val primary: String,
    val de: De
)

@Serializable
data class De (
    val attachments: List<Attachment>,
    val text: Text,
    val urls: List<ListingType>
)

@Serializable
data class Attachment (
    val type: String,
    val url: String,
    val file: String
)

@Serializable
data class Text (
    val title: String,
    val description: String?,
    val situation: String?
)

@Serializable
data class ListingType (
    val type: String
)

@Serializable
data class Prices (
    val currency: String,
    val buy: Buy
)

@Serializable
data class Buy (
    val area: String,
    val price: Long,
    val interval: String
)
