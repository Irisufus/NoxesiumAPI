package me.iris.noxUtils.entityrules

import com.noxcrew.noxesium.api.protocol.rule.EntityRuleIndices
import com.noxcrew.noxesium.api.protocol.rule.ServerRuleIndices
import com.noxcrew.noxesium.api.qib.QibDefinition
import com.noxcrew.noxesium.api.qib.QibEffect
import com.noxcrew.noxesium.paper.api.rule.RemoteServerRule
import me.iris.noxUtils.NoxUtils
import me.iris.noxUtils.NoxUtils.Companion.qibDefinitions
import org.bukkit.entity.Interaction
import org.bukkit.entity.Player

public class Qibs {

    private val manager = NoxUtils.noxesiumManager
    private val entityManager = NoxUtils.entityRuleManager

    public fun addDefinition(id: String, effect: QibEffect, onEnter: Boolean?, onLeave: Boolean?, whileInside: Boolean?, onJump: Boolean?, trigger: Boolean) {
        var enter: QibEffect? = null
        var leave: QibEffect? = null
        var inside: QibEffect? = null
        var jump: QibEffect? = null

        if (onEnter == true) enter = effect
        if (onLeave == true) leave = effect
        if (whileInside == true) inside = effect
        if (onJump == true) jump = effect
        val definition = QibDefinition(enter, leave, inside, jump, trigger)

        qibDefinitions[id] = definition
    }

    public fun removeDefinition(id: String) {
        qibDefinitions.remove(id)
    }

    public fun sendDefinitions(player: Player) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.QIB_BEHAVIORS)
        rule!!.reset()
        rule!!.value = qibDefinitions
        manager.updateServerRules(player)

    }

    public fun setBehavior(entity: Interaction, id: String) {
        val rule: RemoteServerRule<Any>? = entityManager.getEntityRule(entity, EntityRuleIndices.QIB_BEHAVIOR)
        rule!!.value = id
    }

    public fun setWidthZ(entity: Interaction, value: Double) {
        val rule: RemoteServerRule<Any>? = entityManager.getEntityRule(entity, EntityRuleIndices.QIB_WIDTH_Z)
        rule!!.value = value
    }

}