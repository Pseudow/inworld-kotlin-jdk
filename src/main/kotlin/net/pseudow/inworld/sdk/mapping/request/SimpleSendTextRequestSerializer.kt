package net.pseudow.inworld.sdk.mapping.request

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.pseudow.inworld.sdk.mapping.encodeEasyStructure
import net.pseudow.inworld.sdk.mapping.entity.LocationAsStringSerializer
import net.pseudow.inworld.sdk.request.SimpleSendTextRequest

internal class SimpleSendTextRequestSerializer: KSerializer<SimpleSendTextRequest> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("SimpleSendTextRequest") {
        element<String>("character")
        element<String>("text")
        element<String>("sessionId", isOptional = true)
        element<String>("endUserId", isOptional = true)
        element<String>("endUserFullname", isOptional = true)
    }

    override fun serialize(encoder: Encoder, value: SimpleSendTextRequest) {
        encoder.encodeEasyStructure(this.descriptor) {
            encodeSerializableElement(0, LocationAsStringSerializer(), value.characterLocation)
            encodeStringElement(1, value.text)

            value.session?.let { encodeStringElement(2, it.location.path) }
            value.user?.let {
                encodeStringElement(3, it.id)
                encodeStringElement(4, it.name)
            }
        }
    }

    /**
     * Requests are only sent by the client: no need to receive them, but we can still implement them using the default deserialization.
     */
    override fun deserialize(decoder: Decoder) = SimpleSendTextRequest.serializer().deserialize(decoder)
}