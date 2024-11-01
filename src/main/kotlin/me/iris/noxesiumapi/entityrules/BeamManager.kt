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

public class BeamManager(private var entity: Entity) {
    // Only works for minecraft:guardian, minecraft:elder_guardian and minecraft:end_crystal
    private val entityManager = NoxesiumAPI.entityRuleManager

    /**
     * Set the beam color for a [Guardian] or [EnderCrystal] (also disables the bubbles on guardian beams)
     */
    public fun setBeamColor(color: Optional<Color>) {
        if (entity.type != EntityType.GUARDIAN && entity.type != EntityType.END_CRYSTAL) return
        var rule: RemoteServerRule<Any>? = entityManager.getEntityRule(entity, EntityRuleIndices.BEAM_COLOR)
        rule!!.setValue(color)

        // Hide the beam bubbles for Guardians
        if (entity.type != EntityType.GUARDIAN) return
        rule = entityManager.getEntityRule(entity, EntityRuleIndices.DISABLE_BUBBLES)
        rule!!.setValue(true)
    }

    /**
     * Set the beam fade/gradient for a [Guardian] or [EnderCrystal]
     */
    public fun setBeamFade(color: Optional<Color>) {
        if (entity.type != EntityType.GUARDIAN && entity.type != EntityType.END_CRYSTAL) return
        val rule: RemoteServerRule<Any>? = entityManager.getEntityRule(entity, EntityRuleIndices.BEAM_COLOR_FADE)
        rule!!.setValue(color)
    }

    /**
     * Reset the beam color for a [Guardian] or [EnderCrystal]
     */
    public fun resetBeamColor() {
        if (entity.type != EntityType.GUARDIAN && entity.type != EntityType.END_CRYSTAL) return
        val rule: RemoteServerRule<Any>? = entityManager.getEntityRule(entity, EntityRuleIndices.BEAM_COLOR)
        rule!!.reset()
    }

    /**
     * Reset the beam fade/gradient for a [Guardian] or [EnderCrystal]
     */
    public fun resetBeamFade() {
        if (entity.type != EntityType.GUARDIAN && entity.type != EntityType.END_CRYSTAL) return
        val rule: RemoteServerRule<Any>? = entityManager.getEntityRule(entity, EntityRuleIndices.BEAM_COLOR_FADE)
        rule!!.reset()
    }

}