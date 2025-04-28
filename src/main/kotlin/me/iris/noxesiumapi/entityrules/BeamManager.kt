package me.iris.noxesiumapi.entityrules

import com.noxcrew.noxesium.api.protocol.rule.EntityRuleIndices
import com.noxcrew.noxesium.paper.api.rule.RemoteServerRule
import me.iris.noxesiumapi.NoxesiumAPI
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Guardian
import org.bukkit.entity.EnderCrystal
import java.awt.Color
import java.util.*

@Suppress("unused")
class BeamManager(private var entity: Entity) {
    // Only works for minecraft:guardian, minecraft:elder_guardian and minecraft:end_crystal
    init {
        if (entity.type != EntityType.GUARDIAN && entity.type != EntityType.END_CRYSTAL) {
            throw IllegalArgumentException("Entity must be a (Elder) Guardian or End Crystal")
        }
    }

    private val entityManager = NoxesiumAPI.entityRuleManager

    /**
     * Set the beam color for a [Guardian] or [EnderCrystal] (also disables the bubbles on guardian beams)
     */
    fun setBeamColor(color: Optional<Color>) {
        var rule: RemoteServerRule<Any>? = entityManager.getEntityRule(entity, EntityRuleIndices.BEAM_COLOR)
        rule!!.setValue(color)

        // Hide the beam bubbles for Guardians
        if (entity.type != EntityType.GUARDIAN && entity.type != EntityType.ELDER_GUARDIAN) return
        rule = entityManager.getEntityRule(entity, EntityRuleIndices.DISABLE_BUBBLES)
        rule!!.setValue(true)
    }

    /**
     * Set the beam fade/gradient for a [Guardian] or [EnderCrystal]
     */
    fun setBeamFade(color: Optional<Color>) {
        val rule: RemoteServerRule<Any>? = entityManager.getEntityRule(entity, EntityRuleIndices.BEAM_COLOR_FADE)
        rule!!.setValue(color)
    }

    /**
     * Reset the beam color for a [Guardian] or [EnderCrystal]
     */
    fun resetBeamColor() {
        val rule: RemoteServerRule<Any>? = entityManager.getEntityRule(entity, EntityRuleIndices.BEAM_COLOR)
        rule!!.reset()
    }

    /**
     * Reset the beam fade/gradient for a [Guardian] or [EnderCrystal]
     */
    fun resetBeamFade() {
        val rule: RemoteServerRule<Any>? = entityManager.getEntityRule(entity, EntityRuleIndices.BEAM_COLOR_FADE)
        rule!!.reset()
    }

}