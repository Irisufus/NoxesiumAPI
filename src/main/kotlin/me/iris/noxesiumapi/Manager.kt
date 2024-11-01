package me.iris.noxesiumapi

import com.noxcrew.noxesium.api.protocol.rule.ServerRuleIndices
import com.noxcrew.noxesium.paper.api.NoxesiumManager
import com.noxcrew.noxesium.paper.api.rule.RemoteServerRule
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import org.slf4j.Logger

public class Manager(plugin: Plugin, logger: Logger) : NoxesiumManager(plugin, logger) {

    override fun onPlayerRegistered(player: Player) {
        // Send all Qib behaviours after a player gets registered as a Noxesium user
        val rule: RemoteServerRule<Any>? = getServerRule(player, ServerRuleIndices.QIB_BEHAVIORS)
        rule!!.value = NoxesiumAPI.qibDefinitions
        updateServerRules(player)
    }
}