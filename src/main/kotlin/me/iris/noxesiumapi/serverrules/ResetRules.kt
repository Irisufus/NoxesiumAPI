package me.iris.noxesiumapi.serverrules

import com.noxcrew.noxesium.api.protocol.rule.ServerRuleIndices
import com.noxcrew.noxesium.paper.api.rule.RemoteServerRule
import me.iris.noxesiumapi.NoxesiumAPI
import org.bukkit.entity.Player

/**
 * Reset the value of a server rule for a [Player]
 */
@Suppress("unused")
class ResetRules(private val player: Player) {

    private var manager = NoxesiumAPI.noxesiumManager

    fun disableSpinAttackCollision() {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.DISABLE_SPIN_ATTACK_COLLISIONS)

        rule!!.reset()
        manager.updateServerRules(player)
    }

    fun heldItemNameOffset() {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.HELD_ITEM_NAME_OFFSET)

        rule!!.reset()
        manager.updateServerRules(player)
    }

    fun cameraLocked() {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.CAMERA_LOCKED)

        rule!!.reset()
        manager.updateServerRules(player)
    }

    fun disableVanillaMusic() {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.DISABLE_VANILLA_MUSIC)

        rule!!.reset()
        manager.updateServerRules(player)
    }

    fun disableBoatCollisions() {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.DISABLE_BOAT_COLLISIONS)

        rule!!.reset()
        manager.updateServerRules(player)
    }

    fun handItemOverride() {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.HAND_ITEM_OVERRIDE)

        rule!!.reset()
        manager.updateServerRules(player)
    }

    fun showMapInUi() {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.SHOW_MAP_IN_UI)

        rule!!.reset()
        manager.updateServerRules(player)
    }

    fun disableDeferredChunkUpdates() {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.DISABLE_DEFERRED_CHUNK_UPDATES)

        rule!!.reset()
        manager.updateServerRules(player)
    }

    fun customCreativeItems() {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.CUSTOM_CREATIVE_ITEMS)

        rule!!.reset()
        manager.updateServerRules(player)
    }

    fun overrideGraphicsMode() {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.OVERRIDE_GRAPHICS_MODE)

        rule!!.reset()
        manager.updateServerRules(player)
    }

    fun enableSmootherClientTrident() {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.ENABLE_SMOOTHER_CLIENT_TRIDENT)

        rule!!.reset()
        manager.updateServerRules(player)
    }

    fun disableMapInUi() {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.DISABLE_MAP_UI)

        rule!!.reset()
        manager.updateServerRules(player)
    }

    fun riptideCoyoteTime() {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.RIPTIDE_COYOTE_TIME)

        rule!!.reset()
        manager.updateServerRules(player)
    }

    fun riptidePreCharging() {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.RIPTIDE_PRE_CHARGING)

        rule!!.reset()
        manager.updateServerRules(player)
    }

}