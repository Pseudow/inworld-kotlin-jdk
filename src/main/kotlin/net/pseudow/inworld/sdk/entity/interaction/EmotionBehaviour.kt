package net.pseudow.inworld.sdk.entity.interaction

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Emotion(
    @SerialName("behavior")
    val behaviour: EmotionBehaviour,
    val strength: EmotionStrength
)

enum class EmotionBehaviour(val number: Int, val code: String) {
    ERROR(0, "SPAFF_CODE_UNSPECIFIED"),
    NEUTRAL(1, "NEUTRAL"),
    DISGUST(2, "DISGUST"),
    CONTEMPT(3, "CONTEMPT"),
    BELLIGERENCE(4, "BELLIGERENCE"),
    DOMINEERING(5, "DOMINEERING"),
    CRITICISM(6, "CRITICISM"),
    ANGER(7, "ANGER"),
    TENSION(8, "TENSION"),
    TENSE_HUMOR(9, "TENSE_HUMOR"),
    DEFENSIVENESS(10, "DEFENSIVENESS"),
    WHINING(11, "WHINING"),
    SADNESS(12, "SADNESS"),
    STONEWALLING(13, "STONEWALLING"),
    INTEREST(14, "INTEREST"),
    VALIDATION(15, "VALIDATION"),
    AFFECTION(16, "AFFECTION"),
    HUMOR(17, "HUMOR"),
    SURPRISE(18, "SURPRISE"),
    JOY(19, "JOY")
}

enum class EmotionStrength(val number: Int, val code: String) {
    ERROR(0, "STRENGTH_UNSPECIFIED"),
    WEAK(1, "WEAK"),
    STRONG(2, "STRONG"),
    NORMAL(3, "NORMAL")
}