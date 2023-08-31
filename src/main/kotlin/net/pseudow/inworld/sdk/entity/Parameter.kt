package net.pseudow.inworld.sdk.entity

import kotlinx.serialization.Serializable

@Serializable
data class Parameter(
    val name: String,
    val value: String
)
