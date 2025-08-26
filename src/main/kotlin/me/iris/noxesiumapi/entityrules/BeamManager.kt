package me.iris.noxesiumapi.entityrules

import com.noxcrew.noxesium.api.protocol.rule.EntityRuleIndices
import com.noxcrew.noxesium.paper.api.rule.RemoteServerRule
import me.iris.noxesiumapi.NoxesiumAPI
import org.bukkit.entity.EnderCrystal
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Guardian
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
     * If `true` bubbles are removed from [Guardian] beams shot by this entity.
     */
    fun disableBubbles(mode: Boolean) {
        val rule: RemoteServerRule<Any>? = entityManager.getEntityRule(entity, EntityRuleIndices.DISABLE_BUBBLES)
        rule!!.setValue(mode)
    }

    /**
     * Defines a color to use for a beam created by a [Guardian] or [EnderCrystal]
     */
    fun beamColor(color: Optional<Color>) {
        val rule: RemoteServerRule<Any>? = entityManager.getEntityRule(entity, EntityRuleIndices.BEAM_COLOR)
        rule!!.setValue(color)
    }

    /**
     * Defines a color used in combination with the beam color entity rule to create a linear fade.
     */
    fun beamFade(color: Optional<Color>) {
        val rule: RemoteServerRule<Any>? = entityManager.getEntityRule(entity, EntityRuleIndices.BEAM_COLOR_FADE)
        rule!!.setValue(color)
    }

    /**
     * Resets the beam color for a [Guardian] or [EnderCrystal]
     */
    fun resetBeamColor() {
        val rule: RemoteServerRule<Any>? = entityManager.getEntityRule(entity, EntityRuleIndices.BEAM_COLOR)
        rule!!.reset()
    }

    /**
     * Resets the beam fade/gradient for a [Guardian] or [EnderCrystal]
     */
    fun resetBeamFade() {
        val rule: RemoteServerRule<Any>? = entityManager.getEntityRule(entity, EntityRuleIndices.BEAM_COLOR_FADE)
        rule!!.reset()
    }

}