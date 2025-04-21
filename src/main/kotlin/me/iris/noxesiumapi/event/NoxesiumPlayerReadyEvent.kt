package me.iris.noxesiumapi.event

import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.event.player.PlayerEvent

public class NoxesiumPlayerReadyEvent(player: Player) : PlayerEvent(player) {
    public companion object {
        public val handlers: HandlerList = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList {
            return handlers
        }
    }

    override fun getHandlers(): HandlerList {
        return Companion.handlers
    }
}