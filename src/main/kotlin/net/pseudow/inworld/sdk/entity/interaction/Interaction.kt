package net.pseudow.inworld.sdk.entity.interaction

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import net.pseudow.inworld.sdk.entity.Location
import net.pseudow.inworld.sdk.entity.event.CustomEvent
import net.pseudow.inworld.sdk.entity.event.TriggerEvent
import net.pseudow.inworld.sdk.entity.session.SimpleSession

@Serializable
data class Interaction(
    @SerialName("name")
    val location: Location,
    @SerialName("textList")
    val text: Collection<String>,
    val emotion: Emotion,
    @SerialName("sessionId")
    val session: SimpleSession,
    val relationshipUpdate: RelationshipUpdate,
    val activeTriggers: Collection<TriggerEvent>,
    val customEvent: CustomEvent,
    @SerialName("parameters")
    val parameter: JsonObject
)