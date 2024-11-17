package me.iris.noxesiumapi.event

import com.noxcrew.noxesium.api.protocol.ClientSettings
import me.iris.noxesiumapi.event.NoxesiumPlayerReadyEvent
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.event.player.PlayerEvent

public class NoxesiumPlayerSettingsReceivedEvent(
    player: Player,
    public val clientSettings: ClientSettings
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