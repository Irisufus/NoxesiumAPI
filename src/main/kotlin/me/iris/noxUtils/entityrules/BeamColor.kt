package me.iris.noxUtils.entityrules

import com.noxcrew.noxesium.api.protocol.rule.EntityRuleIndices
import com.noxcrew.noxesium.paper.api.rule.RemoteServerRule
import me.iris.noxUtils.NoxUtils
import org.bukkit.entity.Guardian
import java.awt.Color
import java.util.*

public class BeamColor {

    private val entityManager = NoxUtils.entityRuleManager

    // Preset colors for Skript
    public companion object {
        public val RED: Optional<Color> = Optional.of(Color.RED)
        public val ORANGE: Optional<Color> = Optional.of(Color(255, 157, 0))
        public val YELLOW: Optional<Color> = Optional.of(Color.YELLOW)
        public val LIME: Optional<Color> = Optional.of(Color.GREEN)
        public val GREEN: Optional<Color> = Optional.of(Color(0, 100, 0))
        public val CYAN: Optional<Color> = Optional.of(Color(0, 170, 170))
        public val AQUA: Optional<Color> = Optional.of(Color.CYAN)
        public val BLUE: Optional<Color> = Optional.of(Color.BLUE)
        public val PURPLE: Optional<Color> = Optional.of(Color(100, 0, 200))
        public val PINK: Optional<Color> = Optional.of(Color.MAGENTA)
        public val WHITE: Optional<Color> = Optional.of(Color.WHITE)
        public val BLACK: Optional<Color> = Optional.of(Color.BLACK)
        public val GRAY: Optional<Color> = Optional.of(Color.GRAY)
        public val LIGHT_GRAY: Optional<Color> = Optional.of(Color.LIGHT_GRAY)
        public val DARK_GRAY: Optional<Color> = Optional.of(Color.DARK_GRAY)
    }

    // Will be updated to support minecraft:end_crystal once Paper 1.21.2 comes out
    public fun setBeamColor(guardian: Guardian, color: Optional<Color>) {
        // Set the beam color
        var rule: RemoteServerRule<Any>? = entityManager.getEntityRule(guardian, EntityRuleIndices.BEAM_COLOR)
        rule!!.setValue(color)

        // Hide the beam bubbles
        rule = entityManager.getEntityRule(guardian, EntityRuleIndices.DISABLE_BUBBLES)
        rule!!.setValue(true)
    }

    public fun resetBeamColor(guardian: Guardian) {
        val rule: RemoteServerRule<Any>? = entityManager.getEntityRule(guardian, EntityRuleIndices.BEAM_COLOR)
        rule!!.reset()
    }

}