package net.pseudow.inworld.sdk.entity.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.pseudow.inworld.sdk.mapping.type.IntAsStringSerializer

/**
 * Representation of the end user (the player) of your game.
 * You can specify additional data for the Inworld engine to be more precise during its responses.
 *
 * @param id represents the UniqueId of you player
 * @param name represents the Name of your player NPCs will use to communicate
 */
@Serializable
data class User(
    @SerialName("endUserId")
    val id: String,
    @SerialName("givenName")
    val name: String,
    val gender: String? = null,
    val role: String? = null,
    @Serializable(with = IntAsStringSerializer::class)
    val age: Int? = null
)