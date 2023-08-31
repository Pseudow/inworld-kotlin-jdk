package net.pseudow.inworld.sdk.client

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Inworld Auth Credentials Test")
class InworldAuthCredentialsTest {
    @Test
    fun testBase64() {
        val credentials = InworldAuthCredentials(
            "Pseudow",
            "est genant"
        )

        assertEquals(
            "UHNldWRvdzplc3QgZ2VuYW50",
            credentials.build().apiKey
        )
    }
}