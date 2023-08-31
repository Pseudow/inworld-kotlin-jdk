package net.pseudow.inworld.sdk.request

import net.pseudow.inworld.sdk.entity.Location
import java.net.URL

sealed interface Request {
    fun buildUrl(base: URL): URL
}

sealed interface SessionRequest: Request {
    fun grpcMetadataSessionId(): Location
}