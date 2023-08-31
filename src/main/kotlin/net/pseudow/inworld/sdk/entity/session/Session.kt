package net.pseudow.inworld.sdk.entity.session

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.pseudow.inworld.sdk.entity.Location
import net.pseudow.inworld.sdk.mapping.entity.SimpleSessionAsStringSerializer

interface Session {
    val location: Location
}

@Serializable(with = SimpleSessionAsStringSerializer::class)
data class SimpleSession(override val location: Location): Session

@Serializable
data class FullSession(
    @SerialName("name")
    override val location: Location,
    @SerialName("sessionCharacters")
    val characters: Collection<SessionCharacter>,
    @SerialName("loadedScene")
    val loadedFrom: Location
): Session