package me.iris.noxesiumapi

import fr.skytasul.glowingentities.GlowingBlocks
import fr.skytasul.glowingentities.GlowingEntities
import me.iris.noxesiumapi.listeners.NoxesiumPlayerAddedToWorldListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Suppress("unused")
class NoxesiumAPIPlugin : JavaPlugin() {

    companion object {
        val Logger: Logger = LoggerFactory.getLogger("NoxesiumAPI")
        lateinit var instance: NoxesiumAPIPlugin
            private set
        lateinit var glowingEntities: GlowingEntities
            private set
        lateinit var glowingBlocks: GlowingBlocks
            private set
    }

    override fun onLoad() {
        NoxesiumAPI.load()
    }

    override fun onEnable() {
        instance = this

        NoxesiumAPI.load()
        glowingEntities = GlowingEntities(this)
        glowingBlocks = GlowingBlocks(this)

        Bukkit.getPluginManager().registerEvents(NoxesiumPlayerAddedToWorldListener(), this)

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
        NoxesiumAPI.disable()
        glowingEntities.disable()
        glowingBlocks.disable()
        Logger.info("NoxesiumAPI has been disabled!")
    }

}
