package me.iris.noxesiumapi.serverrules

import com.noxcrew.noxesium.api.protocol.NoxesiumFeature
import com.noxcrew.noxesium.api.util.DebugOption
import me.iris.noxesiumapi.NoxesiumAPI.Companion.noxesiumManager
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class RestrictDebugOptionsManager(private var player: Player) {
    private val restrictedDebugOptions: MutableList<Int> = mutableListOf()

    fun reinit() {
        player = Bukkit.getPlayer(player.uniqueId)!!
    }

    /**
     * Adds a [DebugOption] to the restricted options using its keycode.
     * @return true - if the given debug key code is valid.
     */
    fun addOption(option: Int): Boolean {
        if (DebugOption.getByKeyCode(option) == null) return false
        restrictedDebugOptions.add(option)
        return true
    }

    /**
     * Remove a [DebugOption] from the restricted options using its keycode.
     */
    fun removeOption(option: Int) {
        restrictedDebugOptions.remove(option)
    }

    /**
     * Clears the list of restricted debug options.
     */
    fun clear() {
        restrictedDebugOptions.clear()
    }

    /**
     * Returns the list of key codes for the restricted debug options.
     */
    fun list(): List<Int> {
        return restrictedDebugOptions
    }

    /**
     * Sends/updates restricted debug options.
     */
    fun update() {
        if (!noxesiumManager.isUsingNoxesium(player, NoxesiumFeature.CUSTOM_GLOW_COLOR)) return
        SetRules(player).restrictDebugOptions()
    }
}