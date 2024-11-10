package me.iris.noxesiumapi

import com.noxcrew.noxesium.api.qib.QibDefinition
import com.noxcrew.noxesium.paper.api.EntityRuleManager
import com.noxcrew.noxesium.paper.api.NoxesiumManager
import com.noxcrew.noxesium.paper.api.rule.EntityRules
import com.noxcrew.noxesium.paper.api.rule.ServerRules
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.subcommand
import fr.skytasul.glowingentities.GlowingBlocks
import fr.skytasul.glowingentities.GlowingEntities
import me.iris.noxesiumapi.commands.CreativeItems
import me.iris.noxesiumapi.commands.Rules
import me.iris.noxesiumapi.commands.Sound
import me.iris.noxesiumapi.serverrules.CreativeItemsManager
import me.iris.noxesiumapi.util.SoundManager
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import org.slf4j.Logger
import org.slf4j.LoggerFactory

public class NoxesiumAPI : JavaPlugin() {

    public companion object {
        public val qibDefinitions: MutableMap<String, QibDefinition> = mutableMapOf()
        public var Logger: Logger = LoggerFactory.getLogger("NoxesiumAPI")
        public lateinit var instance: NoxesiumAPI
        public lateinit var noxesiumManager: NoxesiumManager
        public lateinit var entityRuleManager: EntityRuleManager
        public lateinit var soundManager: SoundManager
        public lateinit var creativeItemsManager: CreativeItemsManager
        public lateinit var glowingEntities: GlowingEntities
        public lateinit var glowingBlocks: GlowingBlocks

    }

    override fun onEnable() {
        instance = this

        // Enable glowing APIs
        glowingEntities = GlowingEntities(this)
        glowingBlocks = GlowingBlocks(this)

        // Register all managers
        noxesiumManager = Manager(this, Logger)
        noxesiumManager.register()
        entityRuleManager = EntityRuleManager(noxesiumManager)
        entityRuleManager.register()
        soundManager = SoundManager(noxesiumManager)
        creativeItemsManager = CreativeItemsManager()

        // Registers all rules
        ServerRules(noxesiumManager)
        EntityRules(noxesiumManager)

        if (Bukkit.getPluginManager().getPlugin("CommandAPI")?.isEnabled == true) {
            Logger.info("CommandAPI found! Attempting to load commands.")
            registerCommands()
        } else {
            Logger.warn("Could not find CommandAPI! Commands will not be loaded.")
        }

        Logger.info("NoxesiumAPI has been enabled!")
    }

    public fun getInstance(): NoxesiumAPI {
        return instance
    }

    public fun getEntityGlow(): GlowingEntities {
        return glowingEntities
    }

    public fun getBlockGlow(): GlowingBlocks {
        return glowingBlocks
    }

    public fun getManager(): NoxesiumManager {
        return noxesiumManager
    }

    public fun getSoundManager(): SoundManager {
        return soundManager
    }

    public fun getCreativeItemsManager(): CreativeItemsManager {
        return creativeItemsManager
    }

    override fun onDisable() {
        noxesiumManager.unregister()
        entityRuleManager.unregister()
        Logger.info("NoxUtils has been disabled!")
    }

    private fun registerCommands() {
        Rules().registerCommands()
        Sound().registerCommands()
        CreativeItems().registerCommands()
        val rules = subcommand("serverrules") {
            for (command in Rules.RuleCommands) {
                subcommand(command)
            }
        }
        val sound = subcommand("sound") {
            for (command in Sound.SoundCommands) {
                subcommand(command)
            }
        }
        val creativeItems = subcommand("creativeItems") {
            for (command in CreativeItems.creativeItemsCommands) {
                subcommand(command)
            }
        }
        commandAPICommand("noxesiumapi", "noxesiumapi") {
            withRequirement { sender: CommandSender -> sender.isOp }
            subcommand(rules)
            subcommand(sound)
            subcommand(creativeItems)
        }
        Logger.info("/noxesiumapi command loaded!")
    }

}
