package me.iris.noxUtils.entityrules

import com.noxcrew.noxesium.paper.api.rule.RemoteServerRule
import me.iris.noxUtils.NoxUtils
import me.iris.noxUtils.NoxUtils.Companion.instance
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.entity.Guardian
import org.bukkit.entity.LivingEntity
import java.awt.Color
import java.util.*

public class BeamColor {

    private val entityManager = NoxUtils.entityRuleManager
    private val noxesiumManager = NoxUtils.noxesiumManager

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
    }


    /**
     * Spawns a Guardian at [location] with a [color] for [duration] targeting [target]
     */
    public fun spawnGuardian(location: Location, target: LivingEntity, color: Optional<Color>, duration: Long) {
        val world = location.world
        val guardian = world.spawnEntity(location, EntityType.GUARDIAN) as Guardian
        guardian.setGravity(false)
        guardian.isInvisible = true
        guardian.isInvulnerable = true
        guardian.isPersistent = true
        guardian.isSilent = true
        guardian.setAI(false)
        guardian.target = target
        guardian.setLaser(true)
        setBeamColor(guardian, color)
        Bukkit.getScheduler().runTaskLater(instance, Runnable {
            if (!guardian.isDead) {
                resetBeamColor(guardian)
                guardian.remove()
            }
        }, duration)
    }

    // Will be updated to support minecraft:end_crystal once Paper 1.21.2 comes out
    private fun setBeamColor(guardian: Guardian, color: Optional<Color>) {
        // Set the beam color
        var rule: RemoteServerRule<Any>? = entityManager.getEntityRule(guardian, 1)
        rule!!.setValue(color)

        // Hide the beam bubbles
        rule = entityManager.getEntityRule(guardian, 0)
        rule!!.setValue(true)
    }

    private fun resetBeamColor(guardian: Guardian) {
        val rule: RemoteServerRule<Any>? = entityManager.getEntityRule(guardian, 1)
        rule!!.reset()
    }

}