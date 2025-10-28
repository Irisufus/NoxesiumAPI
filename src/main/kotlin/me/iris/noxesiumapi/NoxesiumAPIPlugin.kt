package me.iris.noxesiumapi

import com.noxcrew.noxesium.api.qib.QibDefinition
import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIBukkitConfig
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.subcommand
import fr.skytasul.glowingentities.GlowingBlocks
import fr.skytasul.glowingentities.GlowingEntities
import me.iris.noxesiumapi.commands.*
import me.iris.noxesiumapi.serverrules.CreativeItemsManager
import me.iris.noxesiumapi.serverrules.RestrictDebugOptionsManager
import me.iris.noxesiumapi.util.ServerRules
import org.bukkit.plugin.java.JavaPlugin
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

@Suppress("unused")
class NoxesiumAPIPlugin : JavaPlugin() {

    companion object {
        val qibDefinitions: MutableMap<String, QibDefinition> = mutableMapOf()
        val creativeItemsManagers: MutableMap<UUID, CreativeItemsManager> = mutableMapOf()
        val restrictDebugOptionsManagers: MutableMap<UUID, RestrictDebugOptionsManager> = mutableMapOf()
        val Logger: Logger = LoggerFactory.getLogger("NoxesiumAPI")
        lateinit var instance: NoxesiumAPIPlugin
            private set
        lateinit var noxesiumAPI: NoxesiumAPI
            private set
        lateinit var glowingEntities: GlowingEntities
            private set
        lateinit var glowingBlocks: GlowingBlocks
            private set
    }

    override fun onLoad() {
        CommandAPI.onLoad(CommandAPIBukkitConfig(this)
            .setNamespace("noxesium")
            .shouldHookPaperReload(true)
        )
    }

    override fun onEnable() {
        instance = this

        noxesiumAPI = NoxesiumAPI(this, Logger)
        noxesiumAPI.register()

        glowingEntities = GlowingEntities(this)
        glowingBlocks = GlowingBlocks(this)

        CommandAPI.onEnable()
        ServerRules.createAllRules()
        registerCommands()

        Logger.info("NoxesiumAPI has been enabled!")
    }

    fun getInstance(): NoxesiumAPIPlugin {
        return instance
    }

    fun getEntityGlow(): GlowingEntities {
        return glowingEntities
    }

    fun getBlockGlow(): GlowingBlocks {
        return glowingBlocks
    }


    override fun onDisable() {
        noxesiumAPI.unregister()
        CommandAPI.unregister("noxesiumapi", true)
        CommandAPI.onDisable()
        glowingEntities.disable()
        glowingBlocks.disable()
        Logger.info("NoxesiumAPI has been disabled!")
    }

    private fun registerCommands() {
        CreativeItemsCommand().registerCommands()
        RestrictDebugOptionsCommand().registerCommands()

        val creativeItems = subcommand("creativeItems") {
            for (command in CreativeItemsCommand.creativeItemsCommands) {
                subcommand(command)
            }
        }

        val restrictDebugOptions = subcommand("restrictDebugOptions") {
            for (command in RestrictDebugOptionsCommand.restrictDebugOptionsCommands) {
                subcommand(command)
            }
        }

        val clientSettings = ClientSettingsCommand().createCommand()

        commandAPICommand("noxesiumapi", "noxesium") {
            withPermission("noxesiumapi.command")
            subcommand(creativeItems)
            subcommand(restrictDebugOptions)
            subcommand(clientSettings)
        }
        Logger.info("/noxesiumapi command loaded!")
    }

}
