package net.pseudow.inworld.sdk.client

import java.net.URL

data class InworldClientConfig(
    val auth: InworldAuth,
    val defaultWorkspace: String,
    val milliSecondResponseTimeout: Int = 1_000,
    val serviceBaseUrl: URL = URL("https://studio.inworld.ai/v1/"),
    val capabilities: Capabilities? = null
)

class Capabilities