package me.iris.noxesiumapi.util

import com.noxcrew.noxesium.api.protocol.skull.SkullStringFormatter
import net.kyori.adventure.text.Component

@Suppress("unused")
object SkullFormatter {

    /**
     * Creates a [Component] with a skull from the given data.
     * @param raw If `true` the value is a raw texture, otherwise it's an uuid.
     * @param value The data of this skull.
     * @param grayscale Whether to draw the skull as grayscale.
     * @param advance The advance to give to the glyph.
     * @param ascent The ascent to give to the glyph.
     * @param scale The scale of the glyph.
     * @return A [Component] created with [SkullStringFormatter].
     */
    fun formatComponent(
        raw: Boolean,
        value: String,
        grayscale: Boolean,
        advance: Int,
        ascent: Int,
        scale: Float
    ) : Component {
        return Component.translatable(SkullStringFormatter.write(SkullStringFormatter.SkullInfo(raw, value, grayscale, advance, ascent, scale)))
    }

    /**
     * Creates a [String] with a skull from the given data. Use a translatable component to display the skull.
     * @param raw If `true` the value is a raw texture, otherwise it's an uuid.
     * @param value The data of this skull.
     * @param grayscale Whether to draw the skull as grayscale.
     * @param advance The advance to give to the glyph.
     * @param ascent The ascent to give to the glyph.
     * @param scale The scale of the glyph.
     * @return A [String] created with [SkullStringFormatter].
     */
    fun formatString(
        raw: Boolean,
        value: String,
        grayscale: Boolean,
        advance: Int,
        ascent: Int,
        scale: Float
    ) : String {
        return SkullStringFormatter.write(SkullStringFormatter.SkullInfo(raw, value, grayscale, advance, ascent, scale))
    }

}