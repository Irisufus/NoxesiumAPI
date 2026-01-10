package me.iris.noxesiumapi.util.extensions

import com.noxcrew.noxesium.api.player.NoxesiumServerPlayer
import me.iris.noxesiumapi.NoxesiumAPI
import me.iris.noxesiumapi.components.CustomCreativeItemsManager
import me.iris.noxesiumapi.components.GuiConstraintsManager
import me.iris.noxesiumapi.components.RestrictDebugOptionsManager

val NoxesiumServerPlayer.creativeItemsManager: CustomCreativeItemsManager?
    get() = NoxesiumAPI.creativeItemsManagers[uniqueId]

val NoxesiumServerPlayer.restrictDebugOptionsManager: RestrictDebugOptionsManager?
    get() = NoxesiumAPI.restrictDebugOptionsManagers[uniqueId]

val NoxesiumServerPlayer.guiConstraintsManager: GuiConstraintsManager?
    get() = NoxesiumAPI.guiConstraintsManagers[uniqueId]