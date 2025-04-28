package me.iris.noxesiumapi.event

import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.event.player.PlayerEvent

@Suppress("unused")
class NoxesiumPlayerVersionReceivedEvent(
    player: Player,
    val version: String,
    val protocolVersion: Int
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