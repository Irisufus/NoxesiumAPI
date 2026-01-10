package me.iris.noxesiumapi.listeners

import com.noxcrew.noxesium.paper.event.NoxesiumPlayerAddedToWorldEvent
import me.iris.noxesiumapi.components.CustomCreativeItemsManager
import me.iris.noxesiumapi.components.GuiConstraintsManager
import me.iris.noxesiumapi.components.RestrictDebugOptionsManager
import me.iris.noxesiumapi.util.extensions.creativeItemsManager
import me.iris.noxesiumapi.util.extensions.guiConstraintsManager
import me.iris.noxesiumapi.util.extensions.restrictDebugOptionsManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class NoxesiumPlayerAddedToWorldListener : Listener {

    @EventHandler
    fun onNoxesiumPlayerAddedToWorld(e: NoxesiumPlayerAddedToWorldEvent) {
        e.noxesiumPlayer.creativeItemsManager?.reinit() ?: CustomCreativeItemsManager(e.player)
        e.noxesiumPlayer.restrictDebugOptionsManager?.reinit() ?: RestrictDebugOptionsManager(e.player)
        e.noxesiumPlayer.guiConstraintsManager?.reinit() ?: GuiConstraintsManager(e.player)
    }
}