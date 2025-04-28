package me.iris.noxesiumapi.packets

import com.noxcrew.noxesium.api.protocol.NoxesiumFeature
import com.noxcrew.noxesium.paper.api.network.clientbound.ClientboundOpenLinkPacket
import me.iris.noxesiumapi.NoxesiumAPI
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player

@Suppress("unused")
class OpenLinkPacket {

    companion object {
        val manager = NoxesiumAPI.noxesiumManager
        @JvmStatic
        fun sendLink(player: Player, text: Component?, url: String) {
            if (!manager.isUsingNoxesium(player, NoxesiumFeature.OPEN_LINK_PACKET)) return
            manager.sendPacket(player, ClientboundOpenLinkPacket(text, url))
        }
    }


}