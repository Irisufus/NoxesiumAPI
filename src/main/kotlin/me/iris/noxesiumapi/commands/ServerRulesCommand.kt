package me.iris.noxesiumapi.commands

import com.noxcrew.noxesium.api.protocol.NoxesiumFeature
import com.noxcrew.noxesium.paper.api.rule.GraphicsType
import com.noxcrew.noxesium.paper.api.rule.RemoteServerRule
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.kotlindsl.*
import me.iris.noxesiumapi.NoxesiumAPI
import me.iris.noxesiumapi.NoxesiumAPI.Companion.noxesiumManager
import me.iris.noxesiumapi.NoxesiumAPIPlugin
import me.iris.noxesiumapi.util.ServerRules.allRules
import me.iris.noxesiumapi.util.ServerRules.booleanServerRules
import me.iris.noxesiumapi.util.ServerRules.integerServerRules
import me.iris.noxesiumapi.util.ServerRules.itemStackServerRules
import me.iris.noxesiumapi.util.ServerRules.optionalEnumServerRules
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*

@Suppress("unchecked_cast")
class ServerRulesCommand {

    companion object {
        var RuleCommands: MutableList<CommandAPICommand> = mutableListOf()
    }

    fun registerCommands() {
        NoxesiumAPIPlugin.Logger.info("Creating server rules subcommands...")

        // Boolean server rules
        for (rules in booleanServerRules) {
            RuleCommands.add(
                subcommand(rules.key) {
                    entitySelectorArgumentManyPlayers("players", allowEmpty = false, optional = false)
                    booleanArgument("enabled", false)
                    anyExecutor { sender, commandArguments ->
                        val players = commandArguments["players"] as Collection<Player>
                        val value = commandArguments["enabled"] as Boolean
                        var affected = 0
                        for (player in players) {
                            if (!noxesiumManager.isUsingNoxesium(player, NoxesiumFeature.API_V2)) continue
                            val rule: RemoteServerRule<Any>? = noxesiumManager.getServerRule(player, rules.value)
                            rule!!.value = value
                            affected++
                        }
                        sender.sendRichMessage("<dark_green>$affected <green>player(s) affected")
                    }
                }
            )
        }

        // Integer server rules
        for (rules in integerServerRules) {
            RuleCommands.add(
                subcommand(rules.key) {
                    entitySelectorArgumentManyPlayers("players", allowEmpty = false, optional = false)
                    integerArgument("value")
                    anyExecutor { sender, commandArguments ->
                        val players = commandArguments["players"] as Collection<Player>
                        val value = commandArguments["value"] as Int
                        var affected = 0
                        for (player in players) {
                            if (!noxesiumManager.isUsingNoxesium(player, NoxesiumFeature.API_V2)) continue
                            val rule: RemoteServerRule<Any>? = noxesiumManager.getServerRule(player, rules.value)
                            rule!!.value = value
                            affected++
                        }
                        sender.sendRichMessage("<dark_green>$affected <green>player(s) affected")
                    }
                }
            )
        }

        // Item stack server rules
        for (rules in itemStackServerRules) {
            RuleCommands.add(
                subcommand(rules.key) {
                    entitySelectorArgumentManyPlayers("players", allowEmpty = false, optional = false)
                    itemStackArgument("item")
                    anyExecutor { sender, commandArguments ->
                        val players = commandArguments["players"] as Collection<Player>
                        val value = commandArguments["item"] as ItemStack
                        var affected = 0
                        for (player in players) {
                            if (!noxesiumManager.isUsingNoxesium(player, NoxesiumFeature.API_V2)) continue
                            val rule: RemoteServerRule<Any>? = noxesiumManager.getServerRule(player, rules.value)
                            rule!!.value = value
                            affected++
                        }
                        sender.sendRichMessage("<dark_green>$affected <green>player(s) affected")
                    }
                }
            )
        }

        // Optional enum server rules
        for (rules in optionalEnumServerRules) {
            RuleCommands.add(
                subcommand(rules.key) {
                    entitySelectorArgumentManyPlayers("players", allowEmpty = false, optional = false)
                    if (rules.key == "overrideGraphicsMode") {
                        stringArgument("type") {
                            replaceSuggestions(
                                ArgumentSuggestions.strings { _ ->
                                    GraphicsType.entries.map { it.name }.toTypedArray()
                                }
                            )
                        }
                    }
                    anyExecutor { sender, commandArguments ->
                        val players = commandArguments["players"] as Collection<Player>
                        val value = commandArguments["type"] as String
                        var affected = 0
                        val type = Optional.of<GraphicsType>(GraphicsType.valueOf(value))
                        for (player in players) {
                            if (!noxesiumManager.isUsingNoxesium(player, NoxesiumFeature.API_V2)) continue
                            val rule: RemoteServerRule<Any>? = noxesiumManager.getServerRule(player, rules.value)
                            rule!!.value = type
                            affected++
                        }
                        sender.sendRichMessage("<dark_green>$affected <green>player(s) affected")
                    }
                }
            )
        }

        // Reset server rules
        RuleCommands.add(
            subcommand("reset") {
                entitySelectorArgumentManyPlayers("players", allowEmpty = false, optional = false)
                stringArgument("option") {
                    replaceSuggestions(
                        ArgumentSuggestions.strings { _ ->
                            allRules.keys.toTypedArray()
                        }
                    )
                }
                anyExecutor { sender, commandArguments ->
                    val players = commandArguments["players"] as Collection<Player>
                    val ruleString = commandArguments["option"] as String
                    val rule = allRules[ruleString]
                    if (rule == null) {
                        sender.sendRichMessage("<red>Couldn't find rule $ruleString!")
                        return@anyExecutor
                    }
                    var affected = 0
                    for (player in players) {
                        if (!noxesiumManager.isUsingNoxesium(player, NoxesiumFeature.API_V2)) continue
                        if (rule == -1) {
                            for (rules in allRules) {
                                if (rules.value == -1) continue
                                val rule: RemoteServerRule<Any>? = noxesiumManager.getServerRule(player, rules.value)
                                rule!!.reset()
                            }
                        } else {
                            val rule: RemoteServerRule<Any>? = noxesiumManager.getServerRule(player, rule)
                            rule!!.reset()
                        }
                        NoxesiumAPI.restrictDebugOptionsManagers[player.uniqueId]!!.clear()
                        NoxesiumAPI.creativeItemsManagers[player.uniqueId]!!.clear()
                        affected++
                    }
                    sender.sendRichMessage("<dark_green>$affected <green>player(s) affected")
                }
            }
        )

        NoxesiumAPIPlugin.Logger.info("Created server rules subcommands!")

    }

}