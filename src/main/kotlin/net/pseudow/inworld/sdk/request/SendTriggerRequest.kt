package net.pseudow.inworld.sdk.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import net.pseudow.inworld.sdk.entity.Location
import net.pseudow.inworld.sdk.entity.event.TriggerEvent
import net.pseudow.inworld.sdk.entity.user.User
import net.pseudow.inworld.sdk.mapping.entity.UserOnlyIdSerializer
import java.net.URL

@Serializable
data class SendTriggerRequest(
    @Transient // empty init value for compiler
    val sessionCharacterLocation: Location = Location.sessionCharacter("", "", ""),
    val triggerEvent: TriggerEvent,
    @SerialName("end_user_id")
    @Serializable(with = UserOnlyIdSerializer::class)
    val user: User? = null
): SessionRequest {
    override fun buildUrl(base: URL) = URL(base, this.sessionCharacterLocation.path + ":sendTrigger")

    override fun grpcMetadataSessionId() = this.sessionCharacterLocation
}