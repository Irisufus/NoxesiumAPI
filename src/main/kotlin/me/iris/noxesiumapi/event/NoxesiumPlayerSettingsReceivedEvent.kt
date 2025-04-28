package me.iris.noxesiumapi.event

import com.noxcrew.noxesium.api.protocol.ClientSettings
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.event.player.PlayerEvent

@Suppress("unused")
class NoxesiumPlayerSettingsReceivedEvent(
    player: Player,
    val clientSettings: ClientSettings
) : PlayerEvent(player) {
    companion object {
        val handlers: HandlerList = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList {
            return handlers
        }
    }

    override fun getHandlers(): HandlerList {
        return Companion.handlers
    }
}