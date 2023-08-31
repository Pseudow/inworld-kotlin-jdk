package net.pseudow.inworld.sdk.mapping.entity

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.pseudow.inworld.sdk.entity.Location
import net.pseudow.inworld.sdk.entity.session.SimpleSession

internal class SimpleSessionAsStringSerializer: KSerializer<SimpleSession> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("SimpleSession", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: SimpleSession) = encoder.encodeString(value.location.path)

    override fun deserialize(decoder: Decoder) = SimpleSession(Location(decoder.decodeString()))
}