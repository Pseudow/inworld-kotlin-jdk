package net.pseudow.inworld.sdk.client

import java.nio.charset.StandardCharsets
import java.util.*

data class InworldAuthCredentials(
    val key: String,
    val secret: String
) {
    fun build() = InworldAuth(
        Base64.getEncoder()
            .encode("$key:$secret".toByteArray(StandardCharsets.UTF_8))
            .decodeToString())
}

data class InworldAuth(val apiKey: String)