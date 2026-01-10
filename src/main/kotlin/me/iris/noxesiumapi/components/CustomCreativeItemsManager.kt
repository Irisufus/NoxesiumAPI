package me.iris.noxesiumapi.components

import com.noxcrew.noxesium.core.nms.registry.NmsGameComponentTypes
import com.noxcrew.noxesium.paper.component.setNoxesiumComponent
import me.iris.noxesiumapi.NoxesiumAPI
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

@Suppress("unused")
class CustomCreativeItemsManager(private var player: Player, val resetOnRejoin: Boolean = false) {

    init {
        NoxesiumAPI.creativeItemsManagers[player.uniqueId] = this
    }

    private val customCreativeItems: MutableList<net.minecraft.world.item.ItemStack> = mutableListOf()

    fun reinit() {
        player = Bukkit.getPlayer(player.uniqueId)!!
        if (resetOnRejoin) {
            clear()
            update()
        }
    }

    /**
     * Adds an [ItemStack] to the custom creative tab.
     */
    fun addItem(item: ItemStack) {
        val mcItem = CraftItemStack.unwrap(item)
        customCreativeItems.add(mcItem)
    }

    /**
     * Removes an [ItemStack] from the custom creative tab.
     */
    fun removeItem(item: ItemStack) {
        val mcItem = CraftItemStack.unwrap(item)
        customCreativeItems.remove(mcItem)
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
    fun list(): List<net.minecraft.world.item.ItemStack> {
        return customCreativeItems
    }

    /**
     * Sends/updates the custom creative tab.
     */
    fun update() {
        player.setNoxesiumComponent(NmsGameComponentTypes.CUSTOM_CREATIVE_ITEMS, customCreativeItems)
    }

}