package me.iris.noxesiumapi

import com.noxcrew.noxesium.api.feature.qib.QibDefinition
import com.noxcrew.noxesium.paper.NoxesiumPaper
import me.iris.noxesiumapi.components.CustomCreativeItemsManager
import me.iris.noxesiumapi.components.GuiConstraintsManager
import me.iris.noxesiumapi.components.RestrictDebugOptionsManager
import java.util.*

/**
 * If you depend on the NoxesiumAPI plugin use [NoxesiumAPIPlugin.getInstance]
 *
 * If you're shading NoxesiumAPI you need to initialize this class in your plugin.
 * @sample NoxesiumAPIPlugin.onEnable
 */
@Suppress("unused")
object NoxesiumAPI {

    val qibDefinitions: MutableMap<String, QibDefinition> = mutableMapOf()
    val creativeItemsManagers: MutableMap<UUID, CustomCreativeItemsManager> = mutableMapOf()
    val restrictDebugOptionsManagers: MutableMap<UUID, RestrictDebugOptionsManager> = mutableMapOf()
    val guiConstraintsManagers: MutableMap<UUID, GuiConstraintsManager> = mutableMapOf()

    lateinit var noxesiumPaper: NoxesiumPaper
        private set

    fun load() {
        noxesiumPaper = NoxesiumPaper()
        noxesiumPaper.onLoad()
    }

    fun enable() {
        noxesiumPaper.onEnable()
    }

    fun disable() {
        noxesiumPaper.onDisable()
    }

}
