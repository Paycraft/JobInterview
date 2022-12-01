package com.joedae.propertylist.data

data class Localization(val de: De)

data class De(val attachments: Array<Attachments>, val text: Text)
data class Attachments(val url: String)
data class Text(val title: String)
