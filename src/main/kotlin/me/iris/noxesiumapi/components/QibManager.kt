package me.iris.noxesiumapi.components

import com.noxcrew.noxesium.api.feature.qib.QibDefinition
import com.noxcrew.noxesium.api.feature.qib.QibEffect
import com.noxcrew.noxesium.api.registry.NoxesiumRegistries
import com.noxcrew.noxesium.api.registry.NoxesiumRegistry
import net.kyori.adventure.key.Key
import org.bukkit.entity.Interaction

@Suppress("unused")
object QibManager {

    val qibRegistry: NoxesiumRegistry<QibDefinition> = NoxesiumRegistries.QIB_EFFECTS

    /**
     * Define and add a definition to a list of qib definitions.
     * @param key the key for the qib definition (example: mcc:jump_pad).
     * @param onEnter An effect triggered when the player enters a qib.
     * @param onLeave An effect triggered when the player leaves a qib.
     * @param whileInside An effect triggered each client tick while inside a qib.
     * @param onJump An effect triggered when a player jumps while inside a qib.
     * @param triggerEnterLeaveOnSwitch Whether to trigger the enter & leave effects when moving to a different instance of the same qib definition.
     */
    fun addDefinition(
        key: String,
        onEnter: QibEffect?,
        onLeave: QibEffect?,
        whileInside: QibEffect?,
        onJump: QibEffect?,
        onAttack: QibEffect?,
        onUse: QibEffect?,
        triggerEnterLeaveOnSwitch: Boolean
    ) {
        val definition =
            QibDefinition(onEnter, onLeave, whileInside, onJump, onAttack, onUse, triggerEnterLeaveOnSwitch)
        qibRegistry.register(Key.key(key), definition)
    }

    /**
     * Remove a definition from the list of qib definitions.
     */
    fun removeDefinition(key: String) {
        qibRegistry.remove(Key.key(key))
    }

    /**
     * Allows defining the qib behavior for an [org.bukkit.entity.Interaction] entity.
     */
    fun setBehavior(entity: Interaction, key: String) {
        SetEntityComponents.qibBehavior(entity, key)
    }

    /**
     * Resets the qib behavior of an [Interaction]
     */
    fun resetBehavior(entity: Interaction) {
        SetEntityComponents.qibBehavior(entity)
    }

}