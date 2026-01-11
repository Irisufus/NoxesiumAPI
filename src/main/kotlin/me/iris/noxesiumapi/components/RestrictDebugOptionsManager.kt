package me.iris.noxesiumapi.components

import com.noxcrew.noxesium.core.feature.DebugOption
import com.noxcrew.noxesium.core.registry.CommonGameComponentTypes
import com.noxcrew.noxesium.paper.component.setNoxesiumComponent
import me.iris.noxesiumapi.NoxesiumAPI
import org.bukkit.Bukkit
import org.bukkit.entity.Player

@Suppress("unused")
class RestrictDebugOptionsManager(private var player: Player, val resetOnRejoin: Boolean = false) {

    init {
        NoxesiumAPI.restrictDebugOptionsManagers[player.uniqueId] = this
    }

    private val restrictedDebugOptions: MutableList<Int> = mutableListOf()

    fun reinit() {
        player = Bukkit.getPlayer(player.uniqueId)!!
        if (resetOnRejoin) {
            clear()
            update()
        }
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
        player.setNoxesiumComponent(CommonGameComponentTypes.RESTRICT_DEBUG_OPTIONS, restrictedDebugOptions)
    }
}