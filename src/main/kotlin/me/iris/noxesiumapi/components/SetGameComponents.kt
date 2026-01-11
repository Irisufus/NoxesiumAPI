package me.iris.noxesiumapi.components

import com.noxcrew.noxesium.api.util.Unit
import com.noxcrew.noxesium.core.nms.registry.NmsGameComponentTypes
import com.noxcrew.noxesium.core.registry.CommonGameComponentTypes
import com.noxcrew.noxesium.paper.component.setNoxesiumComponent
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * Set the value of a game component for a [Player]
 */
@Suppress("unused")
object SetGameComponents {

    fun disableSpinAttackCollision(player: Player, value: Boolean = false) {
        if (value) {
            player.setNoxesiumComponent(CommonGameComponentTypes.DISABLE_SPIN_ATTACK_COLLISIONS, Unit.INSTANCE)
        } else {
            player.setNoxesiumComponent(CommonGameComponentTypes.DISABLE_SPIN_ATTACK_COLLISIONS, null)
        }
    }



    fun heldItemNameOffset(player: Player, value: Int? = null) {
        player.setNoxesiumComponent(CommonGameComponentTypes.HELD_ITEM_NAME_OFFSET, value)
    }

    fun cameraLocked(player: Player, value: Boolean = false) {
        if (value) {
            player.setNoxesiumComponent(CommonGameComponentTypes.CAMERA_LOCKED, Unit.INSTANCE)
        } else {
            player.setNoxesiumComponent(CommonGameComponentTypes.CAMERA_LOCKED, null)
        }
    }

    fun disableVanillaMusic(player: Player, value: Boolean = false) {
        if (value) {
            player.setNoxesiumComponent(CommonGameComponentTypes.DISABLE_VANILLA_MUSIC, Unit.INSTANCE)
        } else {
            player.setNoxesiumComponent(CommonGameComponentTypes.DISABLE_VANILLA_MUSIC, null)
        }
    }

    fun disableBoatCollisions(player: Player, value: Boolean = false) {
        if (value) {
            player.setNoxesiumComponent(CommonGameComponentTypes.DISABLE_BOAT_COLLISIONS, Unit.INSTANCE)
        } else {
            player.setNoxesiumComponent(CommonGameComponentTypes.DISABLE_BOAT_COLLISIONS, null)
        }
    }

    fun disableDeferredChunkUpdates(player: Player, value: Boolean = false) {
        if (value) {
            player.setNoxesiumComponent(CommonGameComponentTypes.DISABLE_DEFERRED_CHUNK_UPDATES, Unit.INSTANCE)
        } else {
            player.setNoxesiumComponent(CommonGameComponentTypes.DISABLE_DEFERRED_CHUNK_UPDATES, null)
        }
    }

    fun showMapInUi(player: Player, value: Boolean? = false) {
        player.setNoxesiumComponent(CommonGameComponentTypes.SHOW_MAP_IN_UI, value)
    }

    fun clientAuthoritativeRiptideTridents(player: Player, value: Boolean = false) {
        if (value) {
            player.setNoxesiumComponent(CommonGameComponentTypes.CLIENT_AUTHORITATIVE_RIPTIDE_TRIDENTS, Unit.INSTANCE)
        } else {
            player.setNoxesiumComponent(CommonGameComponentTypes.CLIENT_AUTHORITATIVE_RIPTIDE_TRIDENTS, null)
        }
    }

    fun riptideCoyoteTime(player: Player, value: Int? = null) {
        player.setNoxesiumComponent(CommonGameComponentTypes.RIPTIDE_COYOTE_TIME, value)
    }

    fun riptidePreCharging(player: Player, value: Boolean = false) {
        if (value) {
            player.setNoxesiumComponent(CommonGameComponentTypes.RIPTIDE_PRE_CHARGING, Unit.INSTANCE)
        } else {
            player.setNoxesiumComponent(CommonGameComponentTypes.RIPTIDE_PRE_CHARGING, null)
        }
    }

    fun serverAuthoritativeBlockUpdates(player: Player, value: Boolean = false) {
        if (value) {
            player.setNoxesiumComponent(CommonGameComponentTypes.SERVER_AUTHORITATIVE_BLOCK_UPDATES, Unit.INSTANCE)
        } else {
            player.setNoxesiumComponent(CommonGameComponentTypes.SERVER_AUTHORITATIVE_BLOCK_UPDATES, null)
        }
    }

    fun clientAuthoritativeElytra(player: Player, value: Boolean = false) {
        if (value) {
            player.setNoxesiumComponent(CommonGameComponentTypes.CLIENT_AUTHORITATIVE_ELYTRA, Unit.INSTANCE)
        } else {
            player.setNoxesiumComponent(CommonGameComponentTypes.CLIENT_AUTHORITATIVE_ELYTRA, null)
        }
    }

    fun elytraCoyoteTime(player: Player, value: Double? = null) {
        player.setNoxesiumComponent(CommonGameComponentTypes.ELYTRA_COYOTE_TIME, value)
    }

    fun handItemOverride(player: Player, value: ItemStack? = null) {
        val item = value?.let { CraftItemStack.unwrap(value) }
        player.setNoxesiumComponent(NmsGameComponentTypes.HAND_ITEM_OVERRIDE, item)
    }

}