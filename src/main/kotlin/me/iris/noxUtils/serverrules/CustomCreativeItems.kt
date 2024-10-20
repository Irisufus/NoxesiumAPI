package me.iris.noxUtils.serverrules

import me.iris.noxUtils.NoxUtils.Companion.customCreativeItems
import org.bukkit.Bukkit
import org.bukkit.inventory.ItemStack

public class CustomCreativeItems {

    public fun addItem(item: ItemStack) {
        customCreativeItems.add(item)
    }

    public fun removeItem(item: ItemStack) {
        customCreativeItems.remove(item)
    }

    public fun update() {
        for (player in Bukkit.getOnlinePlayers()) {
            SetRules(player).customCreativeItems()
        }
    }

}