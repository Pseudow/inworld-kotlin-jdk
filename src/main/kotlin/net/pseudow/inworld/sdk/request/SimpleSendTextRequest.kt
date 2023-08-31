package net.pseudow.inworld.sdk.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.pseudow.inworld.sdk.entity.Location
import net.pseudow.inworld.sdk.entity.session.SimpleSession
import net.pseudow.inworld.sdk.entity.user.User
import net.pseudow.inworld.sdk.mapping.request.SimpleSendTextRequestSerializer
import java.net.URL

@Serializable(with = SimpleSendTextRequestSerializer::class)
data class SimpleSendTextRequest(
    val characterLocation: Location,
    val text: String,
    val user: User? = null,
    @SerialName("sessionId") val session: SimpleSession? = null,
): Request {
    override fun buildUrl(base: URL) = URL(base, this.characterLocation.path + ":simpleSendText")
}