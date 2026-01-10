package me.iris.noxesiumapi.util

import com.noxcrew.noxesium.api.feature.sprite.CustomSkullSprite
import net.kyori.adventure.text.Component

@Suppress("unused")
object SkullFormatter {

    /**
     * Creates a [Component] with a skull from the given data.
     * @param raw If `true` the value is a raw texture, otherwise it's an uuid.
     * @param value The data of this skull.
     * @param advance The advance to give to the glyph.
     * @param ascent The ascent to give to the glyph.
     * @param scale The scale of the glyph.
     * @param hat Whether to include a hat.
     * @return [Component]
     */
    fun formatComponent(
        raw: Boolean,
        value: String,
        advance: Int,
        ascent: Int,
        scale: Float,
        hat: Boolean
    ) : Component {

        return Component.translatable(CustomSkullSprite(raw, value, advance, ascent, scale, hat).serialize())
    }

    /**
     * Creates a [String] with a skull from the given data. Use a translatable component to display the skull.
     * @param raw If `true` the value is a raw texture, otherwise it's an uuid.
     * @param value The data of this skull.
     * @param advance The advance to give to the glyph.
     * @param ascent The ascent to give to the glyph.
     * @param scale The scale of the glyph.
     * @param hat Whether to include a hat.
     * @return [String]
     */
    fun formatString(
        raw: Boolean,
        value: String,
        advance: Int,
        ascent: Int,
        scale: Float,
        hat: Boolean
    ) : String {
        return CustomSkullSprite(raw, value, advance, ascent, scale, hat).serialize()
    }

}