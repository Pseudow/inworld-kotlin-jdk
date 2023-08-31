package net.pseudow.inworld.sdk.entity.interaction

import kotlinx.serialization.Serializable

@Serializable
data class RelationshipUpdate(
    val trust: Int,
    val respect: Int,
    val familiar: Int,
    val flirtatious: Int,
    val attraction: Int
)