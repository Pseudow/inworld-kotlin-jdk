package net.pseudow.inworld.sdk.request

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import net.pseudow.inworld.sdk.entity.Location
import java.net.URL

@Serializable
data class SendTextRequest(
    @Transient // empty init value for compiler
    val sessionCharacterLocation: Location = Location.sessionCharacter("", "", ""),
    val text: String
): SessionRequest {
    override fun buildUrl(base: URL) = URL(base, this.sessionCharacterLocation.path + ":sendText")

    override fun grpcMetadataSessionId() = this.sessionCharacterLocation
}
