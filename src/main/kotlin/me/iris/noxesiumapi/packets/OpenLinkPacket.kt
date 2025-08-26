package me.iris.noxesiumapi.packets

import com.noxcrew.noxesium.api.protocol.NoxesiumFeature
import com.noxcrew.noxesium.paper.api.network.clientbound.ClientboundOpenLinkPacket
import me.iris.noxesiumapi.NoxesiumAPI
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player

@Suppress("unused")
object OpenLinkPacket {

    val manager = NoxesiumAPI.noxesiumManager

    /**
     * Sends an open link prompt to a [Player].
     *
     * @param player the player to send the prompt to.
     * @param url the URL to open, must be a valid URL.
     * @param text the text to display for the link, can be null.
     */
    fun sendLink(player: Player, url: String, text: Component?) {
        if (!manager.isUsingNoxesium(player, NoxesiumFeature.OPEN_LINK_PACKET)) return
        manager.sendPacket(player, ClientboundOpenLinkPacket(text, url))
    }


}