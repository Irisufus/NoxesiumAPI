package me.iris.noxesiumapi.commands

import com.google.gson.GsonBuilder
import com.noxcrew.noxesium.api.protocol.NoxesiumFeature
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.entitySelectorArgumentOnePlayer
import dev.jorel.commandapi.kotlindsl.subcommand
import me.iris.noxesiumapi.NoxesiumAPI.Companion.noxesiumManager
import org.bukkit.entity.Player

class ClientSettingsCommand {

    fun createCommand(): CommandAPICommand {
        return subcommand("clientsettings") {
            entitySelectorArgumentOnePlayer("player", optional = false)
            anyExecutor { sender, commandArguments ->
                val player = commandArguments["player"] as Player
                if (noxesiumManager.isUsingNoxesium(player, NoxesiumFeature.API_V2)) {
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val settings = gson.toJson(noxesiumManager.getClientSettings(player))
                    sender.sendRichMessage("<dark_green>${player.name}'s client settings")
                    sender.sendMessage(settings)
                } else {
                    sender.sendRichMessage("<dark_red>${player.name} <red>does not have Noxesium installed!")
                }
            }
        }
    }

}