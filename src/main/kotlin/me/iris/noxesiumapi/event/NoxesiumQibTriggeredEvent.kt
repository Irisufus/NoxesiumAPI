package me.iris.noxesiumapi.event

import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerEvent
import com.noxcrew.noxesium.paper.api.network.serverbound.ServerboundQibTriggeredPacket.Type
import me.iris.noxesiumapi.event.NoxesiumPlayerReadyEvent
import org.bukkit.event.HandlerList

public class NoxesiumQibTriggeredEvent(
    player: Player,
    public val behavior: String,
    public val qibType: Type,
    public val entityId: Int
) : PlayerEvent(player) {
    public companion object {
        public val handlers: HandlerList = HandlerList()

        @JvmStatic
        public fun getHandlerList(): HandlerList {
            return handlers
        }
    }

    override fun getHandlers(): HandlerList {
        return Companion.handlers
    }
}