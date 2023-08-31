package net.pseudow.inworld.sdk.mapping.entity

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.pseudow.inworld.sdk.entity.Location

internal class LocationAsStringSerializer: KSerializer<Location> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Location", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Location) {
        encoder.encodeString(value.path)
    }

    override fun deserialize(decoder: Decoder): Location {
        return Location(decoder.decodeString())
    }
}