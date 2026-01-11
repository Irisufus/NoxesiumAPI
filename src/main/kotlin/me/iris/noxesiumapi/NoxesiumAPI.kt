package me.iris.noxesiumapi

import com.noxcrew.noxesium.paper.commands.listCommand
import com.noxcrew.noxesium.paper.commands.openLinkCommand
import com.noxcrew.noxesium.paper.commands.playSoundCommand
import com.noxcrew.noxesium.paper.commands.componentCommands
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.noxcrew.noxesium.api.NoxesiumEntrypoint
import com.noxcrew.noxesium.paper.ExternalApi
import com.noxcrew.noxesium.paper.NoxesiumPaper
import com.noxcrew.noxesium.paper.entrypoint.CommonPaperNoxesiumEntrypoint
import com.noxcrew.packet.PacketApi
import io.papermc.paper.command.brigadier.CommandSourceStack
import me.iris.noxesiumapi.components.CustomCreativeItemsManager
import me.iris.noxesiumapi.components.GuiConstraintsManager
import me.iris.noxesiumapi.components.RestrictDebugOptionsManager
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

/**
 * If you depend on the NoxesiumAPI plugin use [NoxesiumAPIPlugin.getInstance]
 *
 * If you're shading NoxesiumAPI you need to initialize this class in your plugin.
 * @sample NoxesiumAPIPlugin.onEnable
 */
@Suppress("unused")
class NoxesiumAPI(val plugin: JavaPlugin) {

    companion object {
        private lateinit var externalApi: ExternalApi
        val creativeItemsManagers: MutableMap<UUID, CustomCreativeItemsManager> = mutableMapOf()
        val restrictDebugOptionsManagers: MutableMap<UUID, RestrictDebugOptionsManager> = mutableMapOf()
        val guiConstraintsManagers: MutableMap<UUID, GuiConstraintsManager> = mutableMapOf()

        fun isUsingNoxesium(player: Player) = externalApi.isUsingNoxesium(player)

        fun getInstalledMods(player: Player): Map<String, String> = externalApi.getInstalledMods(player)
    }
    private val entrypoints = mutableSetOf<() -> NoxesiumEntrypoint>()
    private val commands = mutableSetOf<() -> LiteralArgumentBuilder<CommandSourceStack>>()

    fun load() {
        NoxesiumPaper.prepare(plugin, PacketApi("noxcrew_packet_handler"))

        registerEntrypoint { CommonPaperNoxesiumEntrypoint() }
        registerNoxesiumCommand { listCommand() }
        registerNoxesiumCommand { openLinkCommand() }
        registerNoxesiumCommand { playSoundCommand() }
        registerNoxesiumCommand { componentCommands() }
    }

    fun enable() {
        NoxesiumPaper.packetApi.register(plugin)
        NoxesiumPaper.enable(entrypoints, commands)
        externalApi = ExternalApi()
    }

    fun disable() {
        NoxesiumPaper.packetApi.unregister()
    }

    fun registerEntrypoint(entrypoint: () -> NoxesiumEntrypoint) {
        entrypoints += entrypoint
    }

    fun registerNoxesiumCommand(command: () -> LiteralArgumentBuilder<CommandSourceStack>) {
        commands += command
    }
}
