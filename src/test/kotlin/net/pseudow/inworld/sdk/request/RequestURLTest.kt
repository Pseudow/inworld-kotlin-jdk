package net.pseudow.inworld.sdk.request

import net.pseudow.inworld.sdk.entity.Location
import net.pseudow.inworld.sdk.entity.event.TriggerEvent
import net.pseudow.inworld.sdk.entity.user.User
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.net.URL
import java.util.*
import kotlin.test.assertEquals

/**
 * This Test takes as example URL taken from the Inworld documentation.
 * @see <a href="https://docs.inworld.ai/docs/tutorial-api/reference">Inworld API References</a>
 */
@DisplayName("Request URL Test")
class RequestURLTest {
    private val baseURL = URL("https://studio.inworld.ai/v1/")
    private val workspace = System.getenv("WORKSPACE")
    private val session = System.getenv("SESSION")
    private val character = System.getenv("CHARACTER")
    private val sessionCharacter = System.getenv("SESSION_CHARACTER")

    /**
     * @see <a href="https://docs.inworld.ai/docs/tutorial-api/reference#simplesendtextrequest">Simple Send Text Request Example</a>
     */
    @Test
    fun simpleSendTextRequestTestURL() {
        val simpleSendTextRequest = SimpleSendTextRequest(
            Location.character(this.workspace, this.character),
            "hello there!",
            this.buildUser()
        )

        assertEquals(
            "${this.baseURL}workspaces/${this.workspace}/characters/${this.character}:simpleSendText",
            simpleSendTextRequest.buildUrl(this.baseURL).toString()
        )
    }

    /**
     * @see <a href="https://docs.inworld.ai/docs/tutorial-api/reference#opensessionrequest">Open Session Request Example</a>
     */
    @Test
    fun openSessionRequestTestURL() {
        val openSessionRequest = OpenSessionRequest(
            Location.character(this.workspace, this.character),
            this.buildUser()
        )

        assertEquals(
            "${this.baseURL}workspaces/${this.workspace}/characters/${this.character}:openSession",
            openSessionRequest.buildUrl(this.baseURL).toString()
        )
    }

    /**
     * @see <a href="https://docs.inworld.ai/docs/tutorial-api/reference#sendtextrequest">Send Text Request Example</a>
     */
    @Test
    fun sendTextRequestTestURL() {
        val sendTextRequest = SendTextRequest(
            Location.sessionCharacter(this.workspace, this.session, this.sessionCharacter),
            "hello there"
        )

        assertEquals(
            "${this.baseURL}workspaces/${this.workspace}/sessions/${this.session}/sessionCharacters/${this.sessionCharacter}:sendText",
            sendTextRequest.buildUrl(this.baseURL).toString()
        )
    }

    /**
     * @see <a href="https://docs.inworld.ai/docs/tutorial-api/reference#sendtriggerrequest">Send Trigger Request Example</a>
     */
    @Test
    fun sendTriggerRequestTestURL() {
        val sendTriggerRequest = SendTriggerRequest(
            Location.sessionCharacter(this.workspace, this.session, this.sessionCharacter),
            TriggerEvent(
                Location.trigger(this.workspace, "t"),
                Collections.emptyList()
            ),
            this.buildUser()
        )

        assertEquals(
            "${this.baseURL}workspaces/${this.workspace}/sessions/${this.session}/sessionCharacters/${this.sessionCharacter}:sendTrigger",
            sendTriggerRequest.buildUrl(this.baseURL).toString()
        )
    }

    private fun buildUser() = User(
        "12345",
        "Pseudow",
        "Male",
        "Developer",
        17
    )
}