package me.iris.noxesiumapi.commands

import com.noxcrew.noxesium.api.protocol.NoxesiumFeature
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.entitySelectorArgumentOnePlayer
import dev.jorel.commandapi.kotlindsl.subcommand
import me.iris.noxesiumapi.NoxesiumAPI.Companion.noxesiumManager
import org.bukkit.entity.Player

class NoxesiumCheckCommand {
    
    fun createCommand(): CommandAPICommand {
        return subcommand("check") {
            entitySelectorArgumentOnePlayer("player")
            anyExecutor { sender, commandArguments ->
                val player = commandArguments["player"] as Player
                if (noxesiumManager.isUsingNoxesium(player, NoxesiumFeature.API_V2)) {
                    sender.sendRichMessage("<dark_green>${player.name} <green>has Noxesium installed! ${getNoxesiumVersion(player)}")
                } else {
                    sender.sendRichMessage("<dark_red>${player.name} <red>does not have Noxesium installed!")
                }
            }
        }
    }

    fun getNoxesiumVersion(player: Player) : String {
        val protocol = noxesiumManager.getProtocolVersion(player)
        val version = noxesiumManager.getExactVersion(player) ?: when (protocol) {
                0 -> "v0.1.0"
                1 -> "v0.1.0"
                2 -> "v0.1.6"
                3 -> "v1.0.0"
                4 -> "v1.1.1"
                5 -> "v1.2.1"
                6 -> "v2.0.0"
                7 -> "v2.1.0"
                8 -> "v2.1.2"
                9 -> "v2.2.0"
                10 -> "v2.3.0"
                11 -> "v2.3.2"
                12 -> "v2.4.0"
                13 -> "v2.6.0"
                14 -> "v2.6.1"
                15 -> "v2.6.2"
                16 -> "v2.7.3"
                17 -> "v2.7.4"
                18 -> "v2.7.5"
                else -> "None"
        }
        return if (protocol != null) "$version ($protocol)" else "None"
    }
}