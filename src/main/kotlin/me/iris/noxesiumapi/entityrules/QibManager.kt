package me.iris.noxesiumapi.entityrules

import com.noxcrew.noxesium.api.protocol.rule.EntityRuleIndices
import com.noxcrew.noxesium.api.protocol.rule.ServerRuleIndices
import com.noxcrew.noxesium.api.qib.QibDefinition
import com.noxcrew.noxesium.api.qib.QibEffect
import com.noxcrew.noxesium.paper.api.rule.RemoteServerRule
import me.iris.noxesiumapi.NoxesiumAPI
import me.iris.noxesiumapi.NoxesiumAPI.Companion.qibDefinitions
import org.bukkit.entity.Interaction
import org.bukkit.entity.Player

@Suppress("unused")
object QibManager {
    // Qibs are only usable on minecraft:interaction entities.
    private val manager = NoxesiumAPI.noxesiumManager
    private val entityManager = NoxesiumAPI.entityRuleManager

    /**
     * Define and add a definition to a list of qib definitions.
     * @param id the id of the qib definition.
     * @param onEnter An effect triggered when the player enters a qib.
     * @param onLeave An effect triggered when the player leaves a qib.
     * @param whileInside An effect triggered each client tick while inside a qib.
     * @param onJump An effect triggered when a player jumps while inside a qib.
     * @param triggerEnterLeaveOnSwitch Whether to trigger the enter & leave effects when moving to a different instance of the same qib definition.
     */
    fun addDefinition(
        id: String,
        onEnter: QibEffect?,
        onLeave: QibEffect?,
        whileInside: QibEffect?,
        onJump: QibEffect?,
        triggerEnterLeaveOnSwitch: Boolean
    ) {
        val definition = QibDefinition(onEnter, onLeave, whileInside, onJump, triggerEnterLeaveOnSwitch)
        qibDefinitions[id] = definition
    }

    /**
     * Remove a definition from the list of qib definitions.
     */
    fun removeDefinition(id: String) {
        qibDefinitions.remove(id)
    }

    /**
     * Send all qib definitions to a [Player]
     */
    fun sendDefinitions(player: Player) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.QIB_BEHAVIORS)
        rule!!.reset()
        rule.value = qibDefinitions
        manager.updateServerRules(player)

    }

    /**
     * Allows defining the qib behavior for an [Interaction] entity.
     */
    fun setBehavior(entity: Interaction, id: String) {
        val rule: RemoteServerRule<Any>? = entityManager.getEntityRule(entity, EntityRuleIndices.QIB_BEHAVIOR)
        rule!!.value = id
    }

    /**
     * Allows defining the width of an [Interaction] entity on the Z-axis for the context of qib collisions.
     * The regular width is seen as its width on the X-axis.
     */
    fun setWidthZ(entity: Interaction, value: Double) {
        val rule: RemoteServerRule<Any>? = entityManager.getEntityRule(entity, EntityRuleIndices.QIB_WIDTH_Z)
        rule!!.value = value
    }

    /**
     * Resets the qib behavior of an [Interaction]
     */
    fun resetBehavior(entity: Interaction) {
        val rule: RemoteServerRule<Any>? = entityManager.getEntityRule(entity, EntityRuleIndices.QIB_BEHAVIOR)
        rule!!.reset()
    }

    /**
     * Resets the width of an [Interaction] entity on the Z-axis for the context of qib collisions
     * to the width on the X-axis.
     */
    fun resetWidthZ(entity: Interaction) {
        val rule: RemoteServerRule<Any>? = entityManager.getEntityRule(entity, EntityRuleIndices.QIB_WIDTH_Z)
        rule!!.reset()
    }

}