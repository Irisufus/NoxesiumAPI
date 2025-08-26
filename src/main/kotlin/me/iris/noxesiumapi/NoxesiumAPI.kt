package me.iris.noxesiumapi

import com.noxcrew.noxesium.api.qib.QibDefinition
import com.noxcrew.noxesium.paper.api.EntityRuleManager
import com.noxcrew.noxesium.paper.api.NoxesiumManager
import com.noxcrew.noxesium.paper.api.network.NoxesiumPackets
import com.noxcrew.noxesium.paper.api.rule.EntityRules
import me.iris.noxesiumapi.event.NoxesiumPlayerRiptideEvent
import me.iris.noxesiumapi.event.NoxesiumQibTriggeredEvent
import me.iris.noxesiumapi.packets.SoundManager
import me.iris.noxesiumapi.serverrules.CreativeItemsManager
import me.iris.noxesiumapi.serverrules.RestrictDebugOptionsManager
import org.bukkit.plugin.java.JavaPlugin
import org.slf4j.Logger
import java.util.*
import com.noxcrew.noxesium.paper.api.rule.ServerRules as NoxesiumServerRules

/**
 * If you depend on the NoxesiumAPI plugin use [NoxesiumAPIPlugin.getInstance]
 *
 * If you're shading NoxesiumAPI you need to initialize this class in your plugin.
 * @sample NoxesiumAPIPlugin.onEnable
 */
@Suppress("unused")
class NoxesiumAPI(
    private val plugin: JavaPlugin,
    private val logger: Logger
) {

    companion object {
        val qibDefinitions: MutableMap<String, QibDefinition> = mutableMapOf()
        val creativeItemsManagers: MutableMap<UUID, CreativeItemsManager> = mutableMapOf()
        val restrictDebugOptionsManagers: MutableMap<UUID, RestrictDebugOptionsManager> = mutableMapOf()
        lateinit var noxesiumManager: NoxesiumManager
            private set
        lateinit var entityRuleManager: EntityRuleManager
            private set
        lateinit var soundManager: SoundManager
            private set
    }

    fun register() {
        // Register all managers
        noxesiumManager = HookedNoxesiumManager(plugin, logger)
        noxesiumManager.register()
        entityRuleManager = EntityRuleManager(noxesiumManager)
        entityRuleManager.register()
        soundManager = SoundManager(noxesiumManager)

        // Registers all rules
        NoxesiumServerRules(noxesiumManager)
        EntityRules(noxesiumManager)

        NoxesiumPackets.SERVER_QIB_TRIGGERED.addListener(noxesiumManager) { packet, player ->
            NoxesiumQibTriggeredEvent(player, packet.behavior, packet.qibType, packet.entityId).callEvent()
        }

        NoxesiumPackets.SERVER_RIPTIDE.addListener(noxesiumManager) { packet, player ->
            NoxesiumPlayerRiptideEvent(player, packet.slot).callEvent()
        }
    }

    fun getManager(): NoxesiumManager {
        return noxesiumManager
    }

    fun getEntityRuleManager(): EntityRuleManager {
        return entityRuleManager
    }

    fun getSoundManager(): SoundManager {
        return soundManager
    }

    fun unregister() {
        noxesiumManager.unregister()
        entityRuleManager.unregister()
    }

}
