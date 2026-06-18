package me.iris.noxesiumapi.packets

import com.noxcrew.noxesium.core.feature.EasingType
import com.noxcrew.noxesium.paper.component.noxesiumPlayer
import org.bukkit.entity.Player

@Suppress("unused")
object ZoomPacket {

    /**
     * Sets the zoom level of this player.
     *
     * @param zoom               The target zoom level (multiplier). (1.0 = normal FOV, <1.0 = zoomed in, >1.0 = zoomed out)
     * @param transitionTicks    Duration of the zoom transition in ticks (20 ticks = 1 second). If `0` the transition is instant.
     * @param easingType         The easing function to use for the transition.
     * @param keepHandStationary Whether the hand should follow the zoom level.
     * @param fov                The FOV to target when zooming, if not given the zoom level is relative to the user's FOV, if it is given
     *                           the zoom adapts to end at the given zoom level given to this FOV setting.
     */
    fun setZoom(
        player: Player,
        zoom: Float,
        transitionTicks: Int,
        easingType: EasingType,
        keepHandStationary: Boolean,
        fov: Int? = null,
    ) {
        player.noxesiumPlayer?.setZoom(zoom, transitionTicks, easingType, keepHandStationary, fov)
    }

    /**
     * Resets the current zoom override for this player.
     */
    fun resetZoom(player: Player) {
        player.noxesiumPlayer?.resetZoom()
    }

    /**
     * Resets the current zoom override for this player over the given
     * amount of time using the given easing time.
     *
     * @param transitionTicks The amount of ticks to take to go back to default values.
     * @param easingType      The easing function to use for the transition (only used if ticks is set and positive).
     */
    fun resetZoom(player: Player, transitionTicks: Int, easingType: EasingType) {
        player.noxesiumPlayer?.resetZoom(transitionTicks, easingType)
    }
}