package net.pseudow.inworld.sdk.mapping

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder


internal class CompositeEncoderEasier(private val descriptor: SerialDescriptor,
                             private val composite: CompositeEncoder) {
    fun encodeBooleanElement(index: Int, value: Boolean) = this.composite.encodeBooleanElement(this.descriptor, index, value)

    fun encodeByteElement(index: Int, value: Byte) = this.composite.encodeByteElement(this.descriptor, index, value)

    fun encodeShortElement(index: Int, value: Short) = this.composite.encodeShortElement(this.descriptor, index, value)

    fun encodeCharElement(index: Int, value: Char) = this.composite.encodeCharElement(this.descriptor, index, value)

    fun encodeIntElement(index: Int, value: Int) = this.composite.encodeIntElement(this.descriptor, index, value)

    fun encodeLongElement(index: Int, value: Long) = this.composite.encodeLongElement(this.descriptor, index, value)

    fun encodeFloatElement(index: Int, value: Float) = this.composite.encodeFloatElement(this.descriptor, index, value)

    fun encodeDoubleElement(index: Int, value: Double) = this.composite.encodeDoubleElement(this.descriptor, index, value)

    fun encodeStringElement(index: Int, value: String) = this.composite.encodeStringElement(this.descriptor, index, value)

    fun encodeInlineElement(index: Int) = this.composite.encodeInlineElement(this.descriptor, index)

    fun <T : Any?> encodeSerializableElement(index: Int, serializer: SerializationStrategy<T>, value: T) =
        this.composite.encodeSerializableElement(this.descriptor, index, serializer, value)

    @OptIn(ExperimentalSerializationApi::class)
    fun <T : Any> encodeNullableSerializableElement(index: Int, serializer: SerializationStrategy<T>, value: T?) =
        this.composite.encodeNullableSerializableElement(this.descriptor, index, serializer, value)
}

internal inline fun Encoder.encodeEasyStructure(descriptor: SerialDescriptor, block: CompositeEncoderEasier.() -> Unit) {
    val composite = beginStructure(descriptor)
    var ex: Throwable? = null

    try {
        return CompositeEncoderEasier(descriptor, composite).run(block)
    } catch (e: Throwable) {
        ex = e
        throw e
    } finally {
        if (ex == null)
            composite.endStructure(descriptor)
    }
}

internal class CompositeDecoderEasier(private val descriptor: SerialDescriptor,
                             private val composite: CompositeDecoder) {
    fun decodeElementIndex() = this.composite.decodeElementIndex(this.descriptor)

    fun decodeCollectionSize() = this.composite.decodeCollectionSize(this.descriptor)

    fun decodeBooleanElement(index: Int) = this.composite.decodeBooleanElement(this.descriptor, index)

    fun decodeByteElement(index: Int) = this.composite.decodeByteElement(this.descriptor, index)

    fun decodeCharElement(index: Int) = this.composite.decodeCharElement(this.descriptor, index)

    fun decodeShortElement(index: Int) = this.composite.decodeShortElement(this.descriptor, index)

    fun decodeIntElement(index: Int) = this.composite.decodeIntElement(this.descriptor, index)

    fun decodeLongElement(index: Int) = this.composite.decodeLongElement(this.descriptor, index)

    fun decodeFloatElement(index: Int) = this.composite.decodeFloatElement(this.descriptor, index)

    fun decodeDoubleElement(index: Int) = this.composite.decodeDoubleElement(this.descriptor, index)

    fun decodeStringElement(index: Int) = this.composite.decodeStringElement(this.descriptor, index)

    @ExperimentalSerializationApi
    fun decodeInlineElement(index: Int) = this.composite.decodeInlineElement(this.descriptor, index)

    fun <T : Any?> decodeSerializableElement(index: Int, deserializer: DeserializationStrategy<T>, previousValue: T? = null) =
        this.composite.decodeSerializableElement(this.descriptor, index, deserializer, previousValue)

    @ExperimentalSerializationApi
    fun <T : Any> decodeNullableSerializableElement(index: Int, deserializer: DeserializationStrategy<T?>, previousValue: T? = null) =
        this.composite.decodeNullableSerializableElement(this.descriptor, index, deserializer, previousValue)
}

internal inline fun <T> Decoder.decodeEasyStructure(descriptor: SerialDescriptor, block: CompositeDecoderEasier.() -> T): T {
    val composite = beginStructure(descriptor)
    var ex: Throwable? = null
    try {
        return CompositeDecoderEasier(descriptor, composite).run(block)
    } catch (e: Throwable) {
        ex = e
        throw e
    } finally {
        // End structure only if there is no exception, otherwise it can be swallowed
        if (ex == null) composite.endStructure(descriptor)
    }
}