package me.iris.noxesiumapi.util

import com.noxcrew.noxesium.api.protocol.skull.SkullStringFormatter
import net.kyori.adventure.text.Component
import java.util.UUID

@Suppress("unused")
class SkullFormatter {
    companion object {
        /**
         * Returns a [Component] that can be used to display a player skull with the given [UUID].
         */
        @JvmStatic
        fun formatSkullComponent(uuid: UUID, grayscale: Boolean, advance: Int, ascent: Int, scale: Float) : Component {
            return Component.translatable(SkullStringFormatter.write(SkullStringFormatter.SkullInfo(false, uuid.toString(), grayscale, advance, ascent, scale)))
        }

        /**
         * Returns a [String] that can be used to display a player skull with the given [UUID].
         */
        @JvmStatic
        fun formatSkullString(uuid: UUID, grayscale: Boolean, advance: Int, ascent: Int, scale: Float) : String {
            return SkullStringFormatter.write(SkullStringFormatter.SkullInfo(false, uuid.toString(), grayscale, advance, ascent, scale))
        }

    }
}