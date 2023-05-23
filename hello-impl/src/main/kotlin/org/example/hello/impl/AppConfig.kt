package org.example.hello.impl

import com.typesafe.config.ConfigMemorySize
import kotlinx.datetime.LocalDate
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.hocon.HoconDecoder
import kotlinx.serialization.hocon.HoconEncoder
import kotlinx.serialization.hocon.serializers.ConfigMemorySizeSerializer
import kotlinx.serialization.hocon.serializers.JavaDurationSerializer
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter.ISO_DATE_TIME
import kotlin.time.Duration

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class AppConfig(
    val intValue: Int,
    val doubleValue: Double,
    val stringValue: String,
    @Serializable(JavaDurationSerializer::class)
    val javaDuration: java.time.Duration,
    val kotlinDuration: Duration,
    @Serializable(ConfigMemorySizeSerializer::class)
    val memorySize: ConfigMemorySize,
    val kotlinLocalDate: LocalDate,
    @Serializable(ZonedDateTimeConfigSerializer::class)
    val javaZonedDateTime: ZonedDateTime
)

@OptIn(ExperimentalSerializationApi::class)
object ZonedDateTimeConfigSerializer : KSerializer<ZonedDateTime> {

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("hocon.ZonedDateTime", PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder): ZonedDateTime =
        if (decoder is HoconDecoder) decoder.decodeConfigValue { conf, path -> ZonedDateTime.parse(conf.getString(path), ISO_DATE_TIME) }
        else throw SerializationException("This class can be decoded only by Hocon format")

    override fun serialize(encoder: Encoder, value: ZonedDateTime) {
        if (encoder is HoconEncoder) encoder.encodeString(value.format(ISO_DATE_TIME))
    }
}
