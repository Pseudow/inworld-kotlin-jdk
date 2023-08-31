package net.pseudow.inworld.sdk.mapping

import kotlinx.serialization.json.Json
import net.pseudow.inworld.sdk.entity.Location
import net.pseudow.inworld.sdk.entity.Parameter
import net.pseudow.inworld.sdk.entity.event.TriggerEvent
import net.pseudow.inworld.sdk.entity.user.User
import net.pseudow.inworld.sdk.mapping.request.SimpleSendTextRequestSerializer
import net.pseudow.inworld.sdk.request.OpenSessionRequest
import net.pseudow.inworld.sdk.request.SendTextRequest
import net.pseudow.inworld.sdk.request.SendTriggerRequest
import net.pseudow.inworld.sdk.request.SimpleSendTextRequest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * This Test takes as example samples taken from the Inworld documentation.
 * @see <a href="https://docs.inworld.ai/docs/tutorial-api/getting-started">Getting Started</a>
 */
@DisplayName("Request Serializer Test")
class RequestSerializerTest {
    private val workspace = System.getenv("WORKSPACE")
    private val session = System.getenv("SESSION")
    private val character = System.getenv("CHARACTER")
    private val sessionCharacter = System.getenv("SESSION_CHARACTER")

    /**
     * @see <a href="https://docs.inworld.ai/docs/tutorial-api/getting-started#sample-request">Simple Integration Sample Request</a>
     */
    @Test
    fun simpleSendTextRequestTestMapping() {
        val text = "hello there!"
        val user = this.buildTomUser()
        val simpleSendTextRequest = SimpleSendTextRequest(
            Location.character(this.workspace, this.character),
            text,
            user
        )

        assertEquals(
            Json.parseToJsonElement("""
                {
                    "character": "workspaces/${this.workspace}/characters/${this.character}",
                    "text": "$text",
                    "endUserFullname": "${user.name}", 
                    "endUserId": "${user.id}"
                }
            """.trimIndent()),
            Json.encodeToJsonElement(SimpleSendTextRequestSerializer(), simpleSendTextRequest)
        )
    }

    /**
     * @see <a href="https://docs.inworld.ai/docs/tutorial-api/getting-started#sample-request-1">Fully Managed Integration Sample Request</a>
     */
    @Test
    fun openSessionRequestTestMapping() {
        val user = this.buildSherlockUser()
        val openSessionRequest = OpenSessionRequest(
            Location.character(this.workspace, this.character),
            user
        )

        assertEquals(
            Json.parseToJsonElement("""
                {
                    "name": "workspaces/${this.workspace}/characters/${this.character}", 
                    "user": {
                        "endUserId": "${user.id}", 
                        "givenName": "${user.name}", 
                        "gender": "${user.gender}", 
                        "age": "${user.age}", 
                        "role": "${user.role}"
                    }
                }
            """.trimIndent()),
            Json.encodeToJsonElement(OpenSessionRequest.serializer(), openSessionRequest)
        )
    }

    /**
     * @see <a href="https://docs.inworld.ai/docs/tutorial-api/getting-started/#sample-request-2">Fully Managed Integration Sample Request</a>
     */
    @Test
    fun sendTextRequestTestMapping() {
        val text = "hello there"
        val sendTextRequest = SendTextRequest(
            Location.sessionCharacter(this.workspace, this.session, this.sessionCharacter),
            text
        )

        assertEquals(
            Json.parseToJsonElement("""
                {
                    "text":"$text"
                }
            """.trimIndent()),
            Json.encodeToJsonElement(SendTextRequest.serializer(), sendTextRequest)
        )
    }

    /**
     * @see <a href="https://docs.inworld.ai/docs/tutorial-api/getting-started/#sample-request-3">Fully Managed Integration Sample Request</a>
     */
    @Test
    fun sendTriggerRequestTestMapping() {
        val trigger = "test"
        val name = "place"
        val value = "California"
        val sendTriggerRequest = SendTriggerRequest(
            Location.sessionCharacter(this.workspace, this.session, this.sessionCharacter),
            TriggerEvent(
                Location.trigger(this.workspace, trigger),
                listOf(
                    Parameter(name, value)
                )
            )
        )

        assertEquals(
            Json.parseToJsonElement("""
                {
                    "triggerEvent": { 
                        "trigger": "workspaces/${this.workspace}/triggers/$trigger",
                        "parameters": [
                            {"name":"$name", "value":"$value"}
                        ]
                    }
                }
            """.trimIndent()),
            Json.encodeToJsonElement(SendTriggerRequest.serializer(), sendTriggerRequest)
        )
    }

    private fun buildSherlockUser() = User(
        "12345",
        "Sherlock",
        "female",
        "detective",
        27
    )

    private fun buildTomUser() = User(
        "12345",
        "Tom",
        "Unknown",
        "Inworld AI Test",
        -1
    )
}