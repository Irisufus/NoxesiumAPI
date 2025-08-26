package me.iris.noxesiumapi.commands

import com.noxcrew.noxesium.api.util.DebugOption
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.kotlindsl.*
import me.iris.noxesiumapi.NoxesiumAPI
import me.iris.noxesiumapi.NoxesiumAPIPlugin.Companion.Logger
import me.iris.noxesiumapi.serverrules.RestrictDebugOptionsManager
import org.bukkit.entity.Player

@Suppress("unchecked_cast")
class RestrictDebugOptionsCommand {

    companion object {
        var restrictDebugOptionsCommands: MutableList<CommandAPICommand> = mutableListOf()
    }

    private fun add(): CommandAPICommand {
        return subcommand("add") {
            entitySelectorArgumentManyPlayers("players", allowEmpty = false, optional = false)
            stringArgument("option") {
                replaceSuggestions(
                    ArgumentSuggestions.strings { _ ->
                        DebugOption.entries.map { it.name }.toTypedArray()
                    }
                )
            }
            anyExecutor { sender, commandArguments ->
                val players = commandArguments["players"] as Collection<Player>
                val value = commandArguments["option"] as String
                val debugOption = DebugOption.valueOf(value).keyCode
                var affected = 0
                for (player in players) {
                    val restrictDebugOptionsManager: RestrictDebugOptionsManager = NoxesiumAPI.restrictDebugOptionsManagers[player.uniqueId] ?: continue
                    if (!restrictDebugOptionsManager.list().contains(debugOption)) {
                        restrictDebugOptionsManager.addOption(debugOption)
                        affected++
                    } else {
                        continue
                    }
                    restrictDebugOptionsManager.update()
                }
                sender.sendRichMessage("<green>Added <yellow>${DebugOption.getByKeyCode(debugOption)!!.name} <green>to the restricted debug options for $affected players!")
            }
        }
    }

    private fun remove(): CommandAPICommand {
        return subcommand("remove") {
            entitySelectorArgumentManyPlayers("players", allowEmpty = false, optional = false)
            stringArgument("option") {
                replaceSuggestions(
                    ArgumentSuggestions.strings { _ ->
                        DebugOption.entries.map { it.name }.toTypedArray()
                    }
                )
            }
            anyExecutor { sender, commandArguments ->
                val players = commandArguments["players"] as Collection<Player>
                val value = commandArguments["option"] as String
                val debugOption = DebugOption.valueOf(value).keyCode
                var affected = 0
                for (player in players) {
                    val restrictDebugOptionsManager: RestrictDebugOptionsManager = NoxesiumAPI.restrictDebugOptionsManagers[player.uniqueId] ?: continue
                    if (restrictDebugOptionsManager.list().contains(debugOption)) {
                        restrictDebugOptionsManager.removeOption(debugOption)
                        affected++
                    } else {
                        continue
                    }
                    restrictDebugOptionsManager.update()
                }
                sender.sendRichMessage("<green>Removed <yellow>${DebugOption.getByKeyCode(debugOption)!!.name} <green>from the restricted debug options for $affected players!")
            }
        }
    }

    private fun clear(): CommandAPICommand {
        return subcommand("clear") {
            entitySelectorArgumentManyPlayers("players", allowEmpty = false, optional = false)
            anyExecutor { sender, commandArguments ->
                val players = commandArguments["players"] as Collection<Player>
                var affected = 0
                for (player in players) {
                    val restrictDebugOptionsManager: RestrictDebugOptionsManager = NoxesiumAPI.restrictDebugOptionsManagers[player.uniqueId] ?: continue
                    restrictDebugOptionsManager.clear()
                    affected++
                    restrictDebugOptionsManager.update()
                }
                sender.sendRichMessage("<green>Cleared the restricted debug options for $affected players!")
            }
        }
    }

    private fun update(): CommandAPICommand {
        return subcommand("update") {
            entitySelectorArgumentManyPlayers("players", allowEmpty = false, optional = false)
            anyExecutor { sender, commandArguments ->
                val players = commandArguments["players"] as Collection<Player>
                var affected = 0
                for (player in players) {
                    val restrictDebugOptionsManager: RestrictDebugOptionsManager = NoxesiumAPI.restrictDebugOptionsManagers[player.uniqueId] ?: continue
                    restrictDebugOptionsManager.update()
                    affected++
                }
                sender.sendRichMessage("<green>Updated the restricted debug options for $affected players!")
            }
        }
    }

    private fun list(): CommandAPICommand {
        return subcommand("list") {
            playerArgument("player")
            anyExecutor { sender, commandArguments ->
                val player = commandArguments["player"] as Player
                val restrictDebugOptionsManager = NoxesiumAPI.restrictDebugOptionsManagers[player.uniqueId]
                if (restrictDebugOptionsManager == null) {
                    sender.sendRichMessage("<red>Couldn't find restrict debug option manager for ${player.name}!")
                    return@anyExecutor
                }
                if (restrictDebugOptionsManager.list().isNotEmpty()) {
                    restrictDebugOptionsManager.list().forEach { option ->
                        sender.sendRichMessage("<aqua>Name: <blue>${DebugOption.getByKeyCode(option)!!.name}")
                    }
                } else {
                    sender.sendRichMessage("<red>No debug options have been restricted! Use <dark_red>'/noxesiumapi restrictDebugOptions add'<red> to add one.")
                }
            }
        }
    }

    fun registerCommands() {
        Logger.info("Creating restrictDebugOptions subcommands...")
        restrictDebugOptionsCommands.add(add())
        restrictDebugOptionsCommands.add(remove())
        restrictDebugOptionsCommands.add(clear())
        restrictDebugOptionsCommands.add(update())
        restrictDebugOptionsCommands.add(list())
        Logger.info("Created restrictDebugOptions subcommands!")
    }

}