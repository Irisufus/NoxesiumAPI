package me.iris.noxesiumapi

import com.noxcrew.noxesium.api.protocol.ClientSettings
import com.noxcrew.noxesium.api.protocol.rule.ServerRuleIndices
import com.noxcrew.noxesium.paper.api.NoxesiumManager
import com.noxcrew.noxesium.paper.api.rule.RemoteServerRule
import me.iris.noxesiumapi.event.NoxesiumPlayerReadyEvent
import me.iris.noxesiumapi.event.NoxesiumPlayerRegisteredEvent
import me.iris.noxesiumapi.event.NoxesiumPlayerSettingsReceivedEvent
import me.iris.noxesiumapi.event.NoxesiumPlayerVersionReceivedEvent
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import org.slf4j.Logger

public class NoxesiumManager(plugin: Plugin, logger: Logger) : NoxesiumManager(plugin, logger) {

    override fun onReady(player: Player) {
        NoxesiumPlayerReadyEvent(player).callEvent()
    }

    override fun onPlayerRegistered(player: Player) {
        NoxesiumPlayerRegisteredEvent(player).callEvent()
        // Send all Qib behaviours after a player gets registered as a Noxesium user
        val rule: RemoteServerRule<Any>? = getServerRule(player, ServerRuleIndices.QIB_BEHAVIORS)
        rule!!.value = NoxesiumAPI.qibDefinitions
        updateServerRules(player)
    }

    override fun onPlayerVersionReceived(player: Player, version: String, protocolVersion: Int) {
        NoxesiumPlayerVersionReceivedEvent(player, version, protocolVersion).callEvent()
    }

    override fun onPlayerSettingsReceived(player: Player, clientSettings: ClientSettings) {
        NoxesiumPlayerSettingsReceivedEvent(player, clientSettings).callEvent()
    }

}