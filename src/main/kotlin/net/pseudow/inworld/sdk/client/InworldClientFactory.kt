package net.pseudow.inworld.sdk.client

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.ResponseResultHandler
import com.github.kittinunf.fuel.core.ResponseResultOf
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import net.pseudow.inworld.sdk.entity.Location
import net.pseudow.inworld.sdk.entity.interaction.Interaction
import net.pseudow.inworld.sdk.entity.session.FullSession
import net.pseudow.inworld.sdk.entity.session.SimpleSession
import net.pseudow.inworld.sdk.entity.user.User
import net.pseudow.inworld.sdk.request.OpenSessionRequest
import net.pseudow.inworld.sdk.request.Request
import net.pseudow.inworld.sdk.request.SessionRequest
import net.pseudow.inworld.sdk.request.SimpleSendTextRequest

class InworldClientFactory(private val config: InworldClientConfig) {
    init {
        FuelManager.instance.timeoutInMillisecond = this.config.milliSecondResponseTimeout
        FuelManager.instance.baseHeaders = mapOf("Content-Type" to "application/json")
    }

    fun openSimpleSession(user: User,
                          characterSimpleName: String,
                          message: String,
                          session: SimpleSession? = null,
                          workspace: String? = null) = this.openSimpleSession(
        user,
        Location.character(workspace ?: this.config.defaultWorkspace, characterSimpleName),
        message,
        session)


    fun openSimpleSession(user: User,
                          characterLocation: Location,
                          message: String,
                          session: SimpleSession? = null): Pair<SimpleTalkingRegistry, Interaction> {
        val simpleSendTextRequest = SimpleSendTextRequest(
            characterLocation,
            message,
            user,
            session
        )
        val interaction = this.request(simpleSendTextRequest, SimpleSendTextRequest.serializer()).handleError(Interaction.serializer())

        return Pair(SimpleTalkingRegistry(this, user, characterLocation, interaction.session), interaction)
    }

    fun openFullCharacterSession(user: User, characterSimpleName: String, workspace: String?) = this.openFullSession(
        user,
        Location.character(workspace ?: this.config.defaultWorkspace, characterSimpleName)
    )

    fun openFullSceneSession(user: User, sceneSimpleName: String, workspace: String?) = this.openFullSession(
        user,
        Location.scene(workspace ?: this.config.defaultWorkspace, sceneSimpleName)
    )

    fun openFullSession(user: User, characterOrSceneLocation: Location): FullTalkingRegistry {
        val openSessionRegistry = OpenSessionRequest(
            characterOrSceneLocation,
            user
        )
        val fullSession = this.request(openSessionRegistry, OpenSessionRequest.serializer()).handleError(FullSession.serializer())

        return FullTalkingRegistry(this, user, fullSession)
    }

    internal fun<T: Request> request(request: T, serializer: KSerializer<T>, handler: ResponseResultHandler<String>? = null): ResponseResultOf<String>? {
        val url = request.buildUrl(this.config.serviceBaseUrl)
        val headers = mutableMapOf("authorization" to "Basic ${this.config.auth.apiKey}")

        if(request is SessionRequest)
            headers["Grpc-Metadata-session-id"] = request.grpcMetadataSessionId().path

        val httpObject = url.toString().httpPost()
                .header(headers)
                .body(Json.encodeToString(serializer, request))

        return if(handler != null) {
            httpObject.responseString(handler)
            null
        } else httpObject.responseString()
    }
}

internal fun <T>ResponseResultOf<String>?.handleError(serializer: KSerializer<T>): T {
    if(this == null)
        throw NullPointerException("Response result is null!")

    if(this.third is Result.Failure<FuelError>)
        throw (this.third as Result.Failure<FuelError>).error

    return Json.decodeFromString(serializer, this.third.get())
}