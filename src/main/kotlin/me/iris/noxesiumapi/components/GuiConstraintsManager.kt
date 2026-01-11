package me.iris.noxesiumapi.components

import com.noxcrew.noxesium.core.feature.GuiConstraints
import com.noxcrew.noxesium.core.feature.GuiElement
import com.noxcrew.noxesium.core.registry.CommonGameComponentTypes
import com.noxcrew.noxesium.paper.component.setNoxesiumComponent
import me.iris.noxesiumapi.NoxesiumAPI
import org.bukkit.Bukkit
import org.bukkit.entity.Player

@Suppress("unused")
class GuiConstraintsManager(private var player: Player, val resetOnRejoin: Boolean = false) {

    init {
        NoxesiumAPI.guiConstraintsManagers[player.uniqueId] = this
    }

    private val guiConstraints: MutableMap<GuiElement, GuiConstraints> = mutableMapOf()

    fun reinit() {
        player = Bukkit.getPlayer(player.uniqueId)!!
        if (resetOnRejoin) {
            clear()
            update()
        }
    }

    /**
     * Adds a [GuiConstraints] for a [GuiElement]
     */
    fun addOption(guiElement: GuiElement, guiConstraint: GuiConstraints) {
        guiConstraints[guiElement] = guiConstraint
    }

    /**
     * Removes a [GuiConstraints] for a [GuiElement]
     */
    fun removeOption(guiElement: GuiElement) {
        guiConstraints.remove(guiElement)
    }

    /**
     * Clears gui constraints.
     */
    fun clear() {
        guiConstraints.clear()
    }

    /**
     * Returns the map of GUI constraints.
     */
    fun list(): Map<GuiElement, GuiConstraints> {
        return guiConstraints
    }

    /**
     * Sends/updates GUI constrains.
     */
    fun update() {
        player.setNoxesiumComponent(CommonGameComponentTypes.GUI_CONSTRAINTS, guiConstraints)
    }
}