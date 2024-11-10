package me.iris.noxesiumapi.commands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.kotlindsl.itemStackArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.jorel.commandapi.kotlindsl.subcommand
import me.iris.noxesiumapi.NoxesiumAPI.Companion.Logger
import me.iris.noxesiumapi.NoxesiumAPI.Companion.creativeItemsManager
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.inventory.ItemStack

public class CreativeItems {

    public companion object {
        public var creativeItemsCommands: MutableList<CommandAPICommand> = mutableListOf()
    }

    private fun add(): CommandAPICommand {
        return subcommand("add") {
            itemStackArgument("value")
            playerExecutor { sender, commandArguments ->
                val value = commandArguments["value"] as ItemStack
                if (!creativeItemsManager.list().contains(value)) {
                    creativeItemsManager.addItem(value)
                } else {
                    sender.sendRichMessage("<red>This item has already been added!")
                    return@playerExecutor
                }
                creativeItemsManager.update()
                sender.sendMessage(Component.text("Added ", NamedTextColor.GREEN)
                    .append(value.displayName())
                    .append(Component.text(" to the custom creative tab!", NamedTextColor.GREEN))
                )
            }
        }
    }

    private fun remove(): CommandAPICommand {
        return subcommand("remove") {
            itemStackArgument("value")
            playerExecutor { sender, commandArguments ->
                val value = commandArguments["value"] as ItemStack
                if (creativeItemsManager.list().contains(value)) {
                    creativeItemsManager.removeItem(value)
                } else {
                    sender.sendRichMessage("<red>This item wasn't registered!")
                    return@playerExecutor
                }
                creativeItemsManager.update()
                sender.sendMessage(Component.text("Removed ", NamedTextColor.GREEN)
                    .append(value.displayName())
                    .append(Component.text(" from the custom creative tab!", NamedTextColor.GREEN))
                )
            }
        }
    }

    private fun update(): CommandAPICommand {
        return subcommand("update") {
            playerExecutor { sender, _ ->
                creativeItemsManager.update()
                sender.sendRichMessage("<green>Updated the custom creative tab!")
            }
        }
    }

    private fun list(): CommandAPICommand {
        return subcommand("list") {
            playerExecutor { sender, _ ->
                if (creativeItemsManager.list().isNotEmpty()) {
                    creativeItemsManager.list().forEach { item ->
                        sender.sendMessage(Component.text("<aqua>Id: <blue>").append(item.displayName()))
                    }
                } else {
                    sender.sendRichMessage("<red>No items have been registered! Use <dark_red>'/noxesiumapi creativeItems add'<red> to add one.")
                }
            }
        }
    }

    public fun registerCommands() {
        Logger.info("Creating creativeItems subcommands...")
        creativeItemsCommands.add(add())
        creativeItemsCommands.add(remove())
        creativeItemsCommands.add(update())
        creativeItemsCommands.add(list())
        Logger.info("Created creativeItems subcommands!")
    }

}