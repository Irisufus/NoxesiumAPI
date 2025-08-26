package me.iris.noxesiumapi.serverrules

import com.noxcrew.noxesium.api.protocol.NoxesiumFeature
import me.iris.noxesiumapi.NoxesiumAPI.Companion.noxesiumManager
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class CreativeItemsManager(private var player: Player) {

    private val customCreativeItems: MutableList<ItemStack> = mutableListOf()

    fun reinit() {
        player = Bukkit.getPlayer(player.uniqueId)!!
    }

    /**
     * Adds an [ItemStack] to the custom creative tab.
     */
    fun addItem(item: ItemStack) {
        customCreativeItems.add(item)
    }

    /**
     * Removes an [ItemStack] from the custom creative tab.
     */
    fun removeItem(item: ItemStack) {
        customCreativeItems.remove(item)
    }

    /**
     * Clears the list of items in the custom creative tab.
     */
    fun clear() {
        customCreativeItems.clear()
    }

    /**
     * Returns the list of items in the custom creative tab.
     */
    fun list(): List<ItemStack> {
        return customCreativeItems
    }

    /**
     * Sends/updates the custom creative tab.
     */
    fun update() {
        if (!noxesiumManager.isUsingNoxesium(player, NoxesiumFeature.NEW_MCC_FEATURES)) return
        SetRules(player).customCreativeItems()
    }

}