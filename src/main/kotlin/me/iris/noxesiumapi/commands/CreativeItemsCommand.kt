package me.iris.noxesiumapi.commands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.kotlindsl.*
import me.iris.noxesiumapi.NoxesiumAPI
import me.iris.noxesiumapi.NoxesiumAPIPlugin.Companion.Logger
import me.iris.noxesiumapi.serverrules.CreativeItemsManager
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

@Suppress("unchecked_cast")
class CreativeItemsCommand {

    companion object {
        var creativeItemsCommands: MutableList<CommandAPICommand> = mutableListOf()
    }

    private fun add(): CommandAPICommand {
        return subcommand("add") {
            entitySelectorArgumentManyPlayers("players", allowEmpty = false, optional = false)
            itemStackArgument("item", optional = false)
            anyExecutor { sender, commandArguments ->
                val players = commandArguments["players"] as Collection<Player>
                val value = commandArguments["item"] as ItemStack
                var affected = 0
                for (player in players) {
                    val creativeItemsManager: CreativeItemsManager = NoxesiumAPI.creativeItemsManagers[player.uniqueId] ?: continue
                    if (!creativeItemsManager.list().contains(value)) {
                        creativeItemsManager.addItem(value)
                        affected++
                    } else {
                        continue
                    }
                    creativeItemsManager.update()
                }
                sender.sendMessage(Component.text("Added ", NamedTextColor.GREEN)
                    .append(value.displayName())
                    .append(Component.text(" to the custom creative tab!", NamedTextColor.GREEN))
                )
            }
        }
    }

    private fun remove(): CommandAPICommand {
        return subcommand("remove") {
            entitySelectorArgumentManyPlayers("players", allowEmpty = false, optional = false)
            itemStackArgument("item")
            anyExecutor { sender, commandArguments ->
                val players = commandArguments["players"] as Collection<Player>
                val value = commandArguments["item"] as ItemStack
                var affected = 0
                for (player in players) {
                    val creativeItemsManager: CreativeItemsManager = NoxesiumAPI.creativeItemsManagers[player.uniqueId] ?: continue
                    if (creativeItemsManager.list().contains(value)) {
                        creativeItemsManager.removeItem(value)
                        affected++
                    } else {
                        continue
                    }
                    creativeItemsManager.update()
                }
                sender.sendMessage(Component.text("Removed ", NamedTextColor.GREEN)
                    .append(value.displayName())
                    .append(Component.text(" from the custom creative tab!", NamedTextColor.GREEN))
                )
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
                    val creativeItemsManager: CreativeItemsManager = NoxesiumAPI.creativeItemsManagers[player.uniqueId] ?: continue
                    creativeItemsManager.clear()
                    affected++
                    creativeItemsManager.update()
                }
                sender.sendRichMessage("<green>Cleared the custom creative tab for $affected players!")
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
                    val creativeItemsManager: CreativeItemsManager = NoxesiumAPI.creativeItemsManagers[player.uniqueId] ?: continue
                    creativeItemsManager.update()
                    affected++
                }
                sender.sendRichMessage("<green>Updated the custom creative tab for $affected players!")
            }
        }
    }

    private fun list(): CommandAPICommand {
        return subcommand("list") {
            entitySelectorArgumentOnePlayer("player")
            anyExecutor { sender, commandArguments ->
                val player = commandArguments["player"] as Player
                val creativeItemsManager = NoxesiumAPI.creativeItemsManagers[player.uniqueId]
                if (creativeItemsManager == null) {
                    sender.sendRichMessage("<red>Couldn't find creative item manager for ${player.name}!")
                    return@anyExecutor
                }
                if (creativeItemsManager.list().isNotEmpty()) {
                    creativeItemsManager.list().forEach { item ->
                        sender.sendMessage(Component.text("Id: ", NamedTextColor.AQUA).append(item.displayName()))
                    }
                } else {
                    sender.sendRichMessage("<red>No items have been registered! Use <dark_red>'/noxesiumapi creativeItems add'<red> to add one.")
                }
            }
        }
    }

    fun registerCommands() {
        Logger.info("Creating creativeItems subcommands...")
        creativeItemsCommands.add(add())
        creativeItemsCommands.add(remove())
        creativeItemsCommands.add(clear())
        creativeItemsCommands.add(update())
        creativeItemsCommands.add(list())
        Logger.info("Created creativeItems subcommands!")
    }

}