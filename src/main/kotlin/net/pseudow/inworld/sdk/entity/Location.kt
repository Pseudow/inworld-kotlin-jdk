package net.pseudow.inworld.sdk.entity

import kotlinx.serialization.Serializable
import net.pseudow.inworld.sdk.mapping.entity.LocationAsStringSerializer

/**
 * This class represents the location of all the different **data types** in the Inworld API.
 * For example workspace are described using this path: *"worskspaces/$workspaceId"*.
 * Creating a **Location** class allow using the good path and reducing errors.
 */
@Serializable(with = LocationAsStringSerializer::class)
data class Location(val path: String) {
    internal constructor(name: String, namespace: String, parent: Location? = null) : this(
        arrayOf(parent?.path, namespace, name)
            .filterNotNull()
            .joinToString("/")
    )

    fun sub(name: String, namespace: String) = Location(name, namespace, this)

    companion object {
        fun workspace(workspace: String) = Location(workspace, "workspaces")

        fun session(workspace: String, session: String) = this.workspace(workspace).sub(session, "sessions")

        fun sessionCharacter(workspace: String, session: String, sessionCharacter: String) = this.session(workspace, session).sub(sessionCharacter, "sessionCharacters")

        fun scene(workspace: String, scene: String) = this.workspace(workspace).sub(scene, "scenes")

        fun character(workspace: String, character: String) = this.workspace(workspace).sub(character, "characters")

        fun trigger(workspace: String, event: String) = this.workspace(workspace).sub(event, "triggers")

        fun interaction(workspace: String, interaction: String) = this.workspace(workspace).sub(interaction, "interactions")
    }
}