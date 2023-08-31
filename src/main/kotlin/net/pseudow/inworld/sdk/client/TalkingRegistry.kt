package net.pseudow.inworld.sdk.client

import net.pseudow.inworld.sdk.entity.Location
import net.pseudow.inworld.sdk.entity.event.TriggerEvent
import net.pseudow.inworld.sdk.entity.interaction.Interaction
import net.pseudow.inworld.sdk.entity.session.FullSession
import net.pseudow.inworld.sdk.entity.session.Session
import net.pseudow.inworld.sdk.entity.session.SimpleSession
import net.pseudow.inworld.sdk.entity.user.User
import net.pseudow.inworld.sdk.request.SendTextRequest
import net.pseudow.inworld.sdk.request.SendTriggerRequest

/**
 * **Registries** are **end user objects** which allow them to reuse Sessions without having
 * to respecify information such as the Session, or the user and so on.
 */
sealed interface TalkingRegistry {
    val session: Session
}

class SimpleTalkingRegistry(val client: InworldClientFactory,
                            val user: User,
                            val characterLocation: Location,
                            override val session: SimpleSession): TalkingRegistry {
    fun sendMessage(message: String) = this.client.openSimpleSession(this.user, this.characterLocation, message, session).second
}

class FullTalkingRegistry(val client: InworldClientFactory,
                          val user: User,
                          override val session: FullSession): TalkingRegistry {
    fun sendMessage(sessionCharacterLocation: Location, message: String): Interaction {
        val sendTextRequest = SendTextRequest(
            sessionCharacterLocation,
            message
        )

        return this.client.request(sendTextRequest, SendTextRequest.serializer()).handleError(Interaction.serializer())
    }

    fun triggerEvent(sessionCharacterLocation: Location, event: TriggerEvent): Interaction {
        val sendTriggerRequest = SendTriggerRequest(
            sessionCharacterLocation,
            event,
            this.user
        )

        return this.client.request(sendTriggerRequest, SendTriggerRequest.serializer()).handleError(Interaction.serializer())
    }
}