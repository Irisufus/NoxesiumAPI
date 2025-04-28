package me.iris.noxesiumapi.event

import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerEvent
import com.noxcrew.noxesium.paper.api.network.serverbound.ServerboundQibTriggeredPacket.Type
import org.bukkit.event.HandlerList

@Suppress("unused")
class NoxesiumQibTriggeredEvent(
    player: Player,
    val behavior: String,
    val qibType: Type,
    val entityId: Int
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