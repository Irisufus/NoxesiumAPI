package me.iris.noxesiumapi.util

import java.awt.Color
import java.util.*

@Suppress("unused")
object Colors {

    val RED: Optional<Color> = Optional.of(Color.RED)
    val ORANGE: Optional<Color> = Optional.of(Color(255, 157, 0))
    val YELLOW: Optional<Color> = Optional.of(Color.YELLOW)
    val LIME: Optional<Color> = Optional.of(Color.GREEN)
    val GREEN: Optional<Color> = Optional.of(Color(0, 100, 0))
    val CYAN: Optional<Color> = Optional.of(Color(0, 170, 170))
    val AQUA: Optional<Color> = Optional.of(Color.CYAN)
    val BLUE: Optional<Color> = Optional.of(Color.BLUE)
    val PURPLE: Optional<Color> = Optional.of(Color(100, 0, 200))
    val PINK: Optional<Color> = Optional.of(Color.MAGENTA)
    val WHITE: Optional<Color> = Optional.of(Color.WHITE)
    val BLACK: Optional<Color> = Optional.of(Color.BLACK)
    val GRAY: Optional<Color> = Optional.of(Color.GRAY)
    val LIGHT_GRAY: Optional<Color> = Optional.of(Color.LIGHT_GRAY)
    val DARK_GRAY: Optional<Color> = Optional.of(Color.DARK_GRAY)

    /**
     * Returns an [Optional] containing a [Color] from the specified RGB values.
     * This is mostly for Skript because it doesn't work there for some reason.
     */
    fun getColor(r: Int, g: Int, b: Int): Optional<Color> {
        return Optional.of(Color(r, g, b))
    }

}