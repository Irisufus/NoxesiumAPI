package me.iris.noxesiumapi.components

import com.noxcrew.noxesium.api.util.Unit
import com.noxcrew.noxesium.core.registry.CommonEntityComponentTypes
import com.noxcrew.noxesium.paper.component.setNoxesiumComponent
import net.kyori.adventure.key.Key
import org.bukkit.entity.Entity
import org.joml.Vector3fc
import java.awt.Color

@Suppress("unused")
object SetEntityComponents {

    fun disableBubbles(entity: Entity, value: Boolean = false) {
        if (value) {
            entity.setNoxesiumComponent(CommonEntityComponentTypes.DISABLE_BUBBLES, Unit.INSTANCE)
        } else {
            entity.setNoxesiumComponent(CommonEntityComponentTypes.DISABLE_BUBBLES, null)
        }
    }

    fun beamColor(entity: Entity, value: Color? = null) {
        entity.setNoxesiumComponent(CommonEntityComponentTypes.BEAM_COLOR, value)
    }

    fun beamColorFade(entity: Entity, value: Color? = null) {
        entity.setNoxesiumComponent(CommonEntityComponentTypes.BEAM_COLOR_FADE, value)
    }

    fun qibBehavior(entity: Entity, value: String? = null) {
        val key = value?.let { Key.key(it) }
        entity.setNoxesiumComponent(CommonEntityComponentTypes.QIB_BEHAVIOR, key)
    }

    fun glowColor(entity: Entity, value: Color? = null) {
        entity.setNoxesiumComponent(CommonEntityComponentTypes.GLOW_COLOR, value)
    }

    fun hitboxOverride(entity: Entity, value: Vector3fc? = null) {
        entity.setNoxesiumComponent(CommonEntityComponentTypes.HITBOX_OVERRIDE, value)
    }

    fun hitboxColor(entity: Entity, value: Color? = null) {
        entity.setNoxesiumComponent(CommonEntityComponentTypes.HITBOX_COLOR, value)
    }

    fun attackable(entity: Entity, value: Boolean? = null) {
        entity.setNoxesiumComponent(CommonEntityComponentTypes.ATTACKABLE, value)
    }

}