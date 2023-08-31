package net.pseudow.inworld.sdk.entity.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.pseudow.inworld.sdk.entity.Location
import net.pseudow.inworld.sdk.entity.Parameter

@Serializable
data class TriggerEvent(
    @SerialName("trigger")
    val location: Location,
    val parameters: Collection<Parameter>? = null
)