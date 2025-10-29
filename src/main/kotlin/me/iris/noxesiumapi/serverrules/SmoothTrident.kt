package me.iris.noxesiumapi.serverrules

import com.noxcrew.noxesium.api.protocol.rule.ServerRuleIndices
import com.noxcrew.noxesium.paper.api.NoxesiumManager
import com.noxcrew.noxesium.paper.api.network.NoxesiumPackets
import com.noxcrew.noxesium.paper.api.rule.RemoteServerRule
import io.papermc.paper.event.entity.EntityAttemptSpinAttackEvent
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents
import net.minecraft.world.item.enchantment.EnchantmentHelper
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.craftbukkit.event.CraftEventFactory
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerRiptideEvent

/**
 * Sets up the smooth trident feature which implements trident behavior only
 * on the client, removing any server-side behavior.
 */
class SmoothTrident(
    private val noxesiumManager: NoxesiumManager
) : Listener {
    // Taken from Noxesium V3
    private var ignoreCancellation = false

    init {
        // Listen to the client performing a riptide
        NoxesiumPackets.SERVER_RIPTIDE.addListener(this) { packet, player ->
            val rule: RemoteServerRule<Any>? = noxesiumManager.getServerRule(player, ServerRuleIndices.ENABLE_SMOOTHER_CLIENT_TRIDENT)

            // Ignore non-smooth client tridents from lying about the tridents!
            if (rule!!.value == false) return@addListener

            val bukkitStack = player.inventory.getItem(packet.slot) ?: return@addListener
            val nmsStack = CraftItemStack.unwrap(bukkitStack) ?: return@addListener
            val serverPlayer = (player as? CraftPlayer)?.handle ?: return@addListener

            val holder =
                EnchantmentHelper
                    .pickHighestLevel(nmsStack, EnchantmentEffectComponents.TRIDENT_SOUND)
                    .orElse(SoundEvents.TRIDENT_THROW)

            // Call the spin attack event, ignore if cancelled!
            ignoreCancellation = true
            if (!CraftEventFactory.callPlayerRiptideEvent(serverPlayer, nmsStack, 0f, 0f, 0f)) return@addListener
            ignoreCancellation = false

            // Perform the spin attack itself
            serverPlayer.startAutoSpinAttack(20, 8.0F, nmsStack)

            // Play the spin attack noise for other players
            serverPlayer.level().playSound(
                serverPlayer,
                serverPlayer,
                holder.value() as SoundEvent,
                SoundSource.PLAYERS,
                1.0f,
                1.0f,
            )
        }
    }

    /**
     * Cancel any regular riptide usages so we can trigger it custom with the listener above.
     * This is better as vanilla does the same logic for detecting when a riptide triggers on
     * both client and server which uses the time the item is held and can easily de-sync causing
     * a riptide to only happen on one side. To fix this, we send a packet whenever a riptide
     * occurs and trigger it only based on that.
     *
     * This is also necessary to allow the custom coyote time as otherwise there would be a major
     * de-sync of when the riptide occurs.
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    fun onRiptideEvent(e: PlayerRiptideEvent) {
        if (ignoreCancellation) return
        val rule: RemoteServerRule<Any>? = noxesiumManager.getServerRule(e.player, ServerRuleIndices.ENABLE_SMOOTHER_CLIENT_TRIDENT)
        if (rule!!.value == true) {
            e.isCancelled = true
        }
    }

    /** Prevent any collisions for clients with collisions disabled. */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    fun onAttemptSpinAttack(e: EntityAttemptSpinAttackEvent) {
        val player = e.entity as? Player ?: return
        val rule: RemoteServerRule<Any>? = noxesiumManager.getServerRule(player, ServerRuleIndices.ENABLE_SMOOTHER_CLIENT_TRIDENT)
        if (rule!!.value == true) {
            e.isCancelled = true
        }
    }

}