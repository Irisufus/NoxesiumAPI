package me.iris.noxesiumapi.util

import com.noxcrew.noxesium.core.util.OffsetStringFormatter
import net.kyori.adventure.text.Component

@Suppress("unused")
object OffsetFormatter {

    /**
     * Returns a [Component] from a [String] that is offset by the given x and y values.
     */
    fun formatString(text: String, x: Float, y: Float) : Component {
        return Component.text(text).insertion(OffsetStringFormatter.write(
            OffsetStringFormatter.ComponentOffset(x, y)
        ))
    }

    /**
     * Returns a [Component] from a [Component] that is offset by the given x and y values.
     */
    fun formatComponent(text: Component, x: Float, y: Float) : Component {
        return text.insertion(OffsetStringFormatter.write(
            OffsetStringFormatter.ComponentOffset(x, y)
        ))
    }
}