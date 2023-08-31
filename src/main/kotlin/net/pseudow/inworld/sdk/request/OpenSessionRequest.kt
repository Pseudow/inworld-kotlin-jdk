package net.pseudow.inworld.sdk.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.pseudow.inworld.sdk.entity.Location
import net.pseudow.inworld.sdk.entity.user.User
import java.net.URL

@Serializable
data class OpenSessionRequest(
    @SerialName("name")
    val characterOrSceneLocation: Location,
    val user: User? = null
): Request {
    override fun buildUrl(base: URL) = URL(base, this.characterOrSceneLocation.path + ":openSession")
}
