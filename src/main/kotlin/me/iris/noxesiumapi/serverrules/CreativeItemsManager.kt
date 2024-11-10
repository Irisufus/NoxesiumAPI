package me.iris.noxesiumapi.serverrules

import com.noxcrew.noxesium.api.protocol.NoxesiumFeature
import me.iris.noxesiumapi.NoxesiumAPI.Companion.noxesiumManager
import org.bukkit.Bukkit
import org.bukkit.inventory.ItemStack

/**
 * Manager for the custom creative tab
 */
public class CreativeItemsManager {

    private val customCreativeItems: MutableList<ItemStack> = mutableListOf()

    /**
     * Add an [ItemStack] to the custom creative tab
     */
    public fun addItem(item: ItemStack) {
        customCreativeItems.add(item)
    }

    /**
     * Remove an [ItemStack] to the custom creative tab
     */
    public fun removeItem(item: ItemStack) {
        customCreativeItems.remove(item)
    }

    public fun list(): MutableList<ItemStack> {
        return customCreativeItems
    }

    /**
     * Sends/updates the custom creative tab
     */
    public fun update() {
        for (player in Bukkit.getOnlinePlayers()) {
            if (!noxesiumManager.isUsingNoxesium(player, NoxesiumFeature.API_V2)) continue
            SetRules(player).customCreativeItems()
        }
    }

}