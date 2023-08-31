package net.pseudow.inworld.sdk.mapping

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import net.pseudow.inworld.sdk.entity.Location
import net.pseudow.inworld.sdk.entity.event.CustomEvent
import net.pseudow.inworld.sdk.entity.interaction.*
import net.pseudow.inworld.sdk.entity.session.CharacterAssets
import net.pseudow.inworld.sdk.entity.session.FullSession
import net.pseudow.inworld.sdk.entity.session.SessionCharacter
import net.pseudow.inworld.sdk.entity.session.SimpleSession
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Response Deserializer Test")
class ResponseDeserializerTest {
    private val workspace = System.getenv("WORKSPACE")
    private val session = System.getenv("SESSION")

    @Test
    fun interactionTestMapping() {
        val location = Location.interaction(this.workspace, "06f80282-924d-4d39-8a03-0cd2b381015e")
        val text = "I am sorry, but I don't speak your language."
        val emotionBehaviour = EmotionBehaviour.AFFECTION
        val emotionStrength = EmotionStrength.STRONG
        val session = SimpleSession(Location.session(this.workspace, this.session))
        val relationshipUpdate = RelationshipUpdate(
            0,
            0,
            0,
            0,
            0
        )

        val interaction = Interaction(
            location,
            listOf(text),
            Emotion(
                emotionBehaviour,
                emotionStrength
            ),
            session,
            relationshipUpdate,
            listOf(),
            CustomEvent(Location(""), listOf()),
            JsonObject(mapOf())
        )

        assertEquals(
            interaction,
            Json.decodeFromString("""
                {
                    "name":"${location.path}", 
                    "textList":["$text"], 
                    "customEvent": {
                      "customEvent":"", 
                      "parameters":[]
                    }, 
                    "parameters":{}, 
                    "emotion": {
                      "behavior":"$emotionBehaviour", 
                      "strength":"$emotionStrength"
                    }, 
                    "sessionId":"${session.location.path}", 
                    "relationshipUpdate": {
                      "trust": ${relationshipUpdate.trust}, 
                      "respect": ${relationshipUpdate.respect}, 
                      "familiar": ${relationshipUpdate.familiar}, 
                      "flirtatious": ${relationshipUpdate.flirtatious}, 
                      "attraction": ${relationshipUpdate.attraction}
                    }, 
                    "activeTriggers":[]
                }
            """.trimIndent())
        )
    }

    @Test
    fun fullSessionTestMapping() {
        val location = Location.session(this.workspace, this.session)
        val scene = Location.scene(this.workspace, "test")
        val character = SessionCharacter(
            Location.character(this.workspace, "tony"),
            Location.sessionCharacter(this.workspace, this.session, "45"),
            "Tony",
            CharacterAssets(
                "",
                ""
            )
        )

        val fullSession = FullSession(
            location,
            listOf(character),
            scene
        )

        assertEquals(
            fullSession,
            Json.decodeFromString("""
                {
                    "name": "${location.path}",
                    "sessionCharacters": [
                        {
                            "name": "${character.characterLocation.path}",
                            "character": "${character.location.path}",
                            "displayName": "${character.name}",
                            "characterAssets": {
                                "avatarImg": "${character.assets.avatarImage}",
                                "avatarImgOriginal": "${character.assets.avatarImageOriginal}"
                            }
                        }
                    ],
                    "loadedScene": "${scene.path}"
                }
            """.trimIndent())
        )
    }
}