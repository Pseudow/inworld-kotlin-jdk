package net.pseudow.inworld.sdk.mapping.entity

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.pseudow.inworld.sdk.entity.user.User

internal class UserOnlyIdSerializer: KSerializer<User> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("User", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: User) = encoder.encodeString(value.id)

    override fun deserialize(decoder: Decoder) = User.serializer().deserialize(decoder)
}