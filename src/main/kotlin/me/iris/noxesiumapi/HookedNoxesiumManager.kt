package me.iris.noxesiumapi

import com.noxcrew.noxesium.api.protocol.ClientSettings
import com.noxcrew.noxesium.api.protocol.rule.ServerRuleIndices
import com.noxcrew.noxesium.paper.api.NoxesiumManager
import com.noxcrew.noxesium.paper.api.rule.RemoteServerRule
import me.iris.noxesiumapi.event.NoxesiumPlayerReadyEvent
import me.iris.noxesiumapi.event.NoxesiumPlayerSettingsReceivedEvent
import me.iris.noxesiumapi.event.NoxesiumPlayerVersionReceivedEvent
import me.iris.noxesiumapi.serverrules.CreativeItemsManager
import me.iris.noxesiumapi.serverrules.RestrictDebugOptionsManager
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import org.slf4j.Logger

class HookedNoxesiumManager(plugin: Plugin, logger: Logger) : NoxesiumManager(plugin, logger) {

    override fun onReady(player: Player) {
        NoxesiumPlayerReadyEvent(player).callEvent()
    }

    override fun onPlayerRegistered(player: Player) {
        // Send all Qib behaviors after a player gets registered as a Noxesium user
        val rule: RemoteServerRule<Any>? = getServerRule(player, ServerRuleIndices.QIB_BEHAVIORS)
        rule!!.value = NoxesiumAPI.qibDefinitions
        // Check if server rule managers exist. If not, create new ones
        if (!NoxesiumAPI.restrictDebugOptionsManagers.containsKey(player.uniqueId)) {
            val restrictDebugOptionsManager = RestrictDebugOptionsManager(player)
            NoxesiumAPI.restrictDebugOptionsManagers[player.uniqueId] = restrictDebugOptionsManager
        } else {
            NoxesiumAPI.restrictDebugOptionsManagers[player.uniqueId]!!.reinit()
        }
        if (!NoxesiumAPI.creativeItemsManagers.containsKey(player.uniqueId)) {
            val creativeItemsManager = CreativeItemsManager(player)
            NoxesiumAPI.creativeItemsManagers[player.uniqueId] = creativeItemsManager
        } else {
            NoxesiumAPI.creativeItemsManagers[player.uniqueId]!!.reinit()
        }
        // Resend server rule values in case of rejoining
        NoxesiumAPI.restrictDebugOptionsManagers[player.uniqueId]!!.update()
        NoxesiumAPI.creativeItemsManagers[player.uniqueId]!!.update()
        updateServerRules(player)
    }

    override fun onPlayerVersionReceived(player: Player, version: String, protocolVersion: Int) {
        NoxesiumPlayerVersionReceivedEvent(player, version, protocolVersion).callEvent()
    }

    override fun onPlayerSettingsReceived(player: Player, clientSettings: ClientSettings) {
        NoxesiumPlayerSettingsReceivedEvent(player, clientSettings).callEvent()
    }

}