package me.iris.noxesiumapi.serverrules

import com.noxcrew.noxesium.api.protocol.rule.ServerRuleIndices
import com.noxcrew.noxesium.paper.api.rule.GraphicsType
import com.noxcrew.noxesium.paper.api.rule.RemoteServerRule
import me.iris.noxesiumapi.NoxesiumAPI
import me.iris.noxesiumapi.NoxesiumAPI.Companion.creativeItemsManager
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * Set the value of a server rule for a [Player]
 */
public open class SetRules(private val player: Player) {

    private var manager = NoxesiumAPI.noxesiumManager

    public fun disableSpinAttackCollision(value: Boolean) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.DISABLE_SPIN_ATTACK_COLLISIONS)

        rule!!.setValue(value)
        manager.updateServerRules(player)
    }

    public fun heldItemNameOffset(value: Int) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.HELD_ITEM_NAME_OFFSET)

        rule!!.setValue(value)
        manager.updateServerRules(player)
    }

    public fun cameraLocked(value: Boolean) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.CAMERA_LOCKED)

        rule!!.setValue(value)
        manager.updateServerRules(player)
    }

    public fun disableVanillaMusic(value: Boolean) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.DISABLE_VANILLA_MUSIC)

        rule!!.setValue(value)
        manager.updateServerRules(player)
    }

    public fun disableBoatCollisions(value: Boolean) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.DISABLE_BOAT_COLLISIONS)

        rule!!.setValue(value)
        manager.updateServerRules(player)
    }

    public fun handItemOverride(value: ItemStack) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.HAND_ITEM_OVERRIDE)

        rule!!.setValue(value)
        manager.updateServerRules(player)
    }

    public fun showMapInUi(value: Boolean) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.SHOW_MAP_IN_UI)

        rule!!.setValue(value)
        manager.updateServerRules(player)
    }

    public fun disableDeferredChunkUpdates(value: Boolean) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.DISABLE_DEFERRED_CHUNK_UPDATES)

        rule!!.setValue(value)
        manager.updateServerRules(player)
    }

    public fun customCreativeItems() {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.CUSTOM_CREATIVE_ITEMS)

        // Reset rule because I have no clue how to use ClientboundChangeServerRulePacket
        rule!!.reset()
        rule.setValue(creativeItemsManager.list())
        manager.updateServerRules(player)
    }

    public fun overrideGraphicsMode(value: GraphicsType) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.OVERRIDE_GRAPHICS_MODE)

        rule!!.setValue(value)
        manager.updateServerRules(player)
    }

    public fun enableSmootherClientTrident(value: Boolean) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.ENABLE_SMOOTHER_CLIENT_TRIDENT)

        rule!!.setValue(value)
        manager.updateServerRules(player)
    }

    public fun disableMapInUi(value: Boolean) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.DISABLE_MAP_UI)

        rule!!.setValue(value)
        manager.updateServerRules(player)
    }

    public fun riptideCoyoteTime(value: Int) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.RIPTIDE_COYOTE_TIME)

        rule!!.setValue(value)
        manager.updateServerRules(player)
    }

    public fun riptidePreCharging(value: Boolean) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, ServerRuleIndices.RIPTIDE_PRE_CHARGING)

        rule!!.setValue(value)
        manager.updateServerRules(player)
    }

}