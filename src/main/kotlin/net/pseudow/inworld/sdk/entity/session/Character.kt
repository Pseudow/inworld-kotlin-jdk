package net.pseudow.inworld.sdk.entity.session

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.pseudow.inworld.sdk.entity.Location

@Serializable
data class SessionCharacter(
    @SerialName("name")
    val characterLocation: Location,
    @SerialName("character")
    val location: Location,
    @SerialName("displayName")
    val name: String,
    @SerialName("characterAssets")
    val assets: CharacterAssets
)

@Serializable
data class CharacterAssets(
    @SerialName("avatarImg")
    val avatarImage: String,
    @SerialName("avatarImgOriginal")
    val avatarImageOriginal: String
)