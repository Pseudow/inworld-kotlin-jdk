package net.pseudow.inworld.sdk.mapping.type

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

internal class IntAsStringSerializer: KSerializer<Int> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("IntAsString", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Int) = encoder.encodeString(value.toString())

    override fun deserialize(decoder: Decoder) = decoder.decodeString().toInt()
}