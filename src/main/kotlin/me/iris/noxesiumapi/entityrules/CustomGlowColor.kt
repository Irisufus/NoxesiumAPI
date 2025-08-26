package me.iris.noxesiumapi.entityrules

import com.noxcrew.noxesium.api.protocol.rule.EntityRuleIndices
import com.noxcrew.noxesium.paper.api.rule.RemoteServerRule
import me.iris.noxesiumapi.NoxesiumAPI
import org.bukkit.entity.Entity
import java.awt.Color
import java.util.*

@Suppress("unused")
class CustomGlowColor(private var entity: Entity) {

    private val entityManager = NoxesiumAPI.entityRuleManager

    /**
     * Defines a custom color to use for glowing by this [Entity]
     */
    fun glowColor(color: Optional<Color>) {
        val rule: RemoteServerRule<Any>? = entityManager.getEntityRule(entity, EntityRuleIndices.CUSTOM_GLOW_COLOR)
        rule!!.setValue(color)
    }

    /**
     * Resets the glow color for an [Entity]
     */
    fun resetGlowColor() {
        val rule: RemoteServerRule<Any>? = entityManager.getEntityRule(entity, EntityRuleIndices.CUSTOM_GLOW_COLOR)
        rule!!.reset()
    }
}