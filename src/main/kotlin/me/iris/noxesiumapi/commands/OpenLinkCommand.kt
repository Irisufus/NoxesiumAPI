package me.iris.noxesiumapi.commands

import com.noxcrew.noxesium.api.protocol.NoxesiumFeature
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.kotlindsl.*
import me.iris.noxesiumapi.NoxesiumAPI.Companion.noxesiumManager
import me.iris.noxesiumapi.packets.OpenLinkPacket
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player

class OpenLinkCommand {

    @Suppress("unchecked_cast")
    fun createCommand(): CommandAPICommand {
        return subcommand("link") {
            entitySelectorArgumentManyPlayers("players", allowEmpty = false, optional = false)
            textArgument("link")
            adventureChatComponentArgument("text")
            anyExecutor { _, commandArguments ->
                val players = commandArguments["players"] as Collection<Player>
                val url = commandArguments["link"] as String
                val text = commandArguments["text"] as Component
                for (player in players) {
                    if (noxesiumManager.isUsingNoxesium(player, NoxesiumFeature.OPEN_LINK_PACKET)) {
                        OpenLinkPacket.sendLink(player, url, text)
                    }
                }
            }
        }
    }

}