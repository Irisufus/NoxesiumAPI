package me.iris.noxesiumapi.components

import com.noxcrew.noxesium.api.util.Unit
import com.noxcrew.noxesium.core.feature.item.HoverSound
import com.noxcrew.noxesium.core.feature.item.Hoverable
import com.noxcrew.noxesium.core.registry.CommonItemComponentTypes
import com.noxcrew.noxesium.paper.component.setNoxesiumComponent
import net.kyori.adventure.key.Key
import org.bukkit.inventory.ItemStack

@Suppress("unused")
object SetItemComponents {

    fun immovable(itemStack: ItemStack, value: Boolean = false) {
        if (value) {
            itemStack.setNoxesiumComponent(CommonItemComponentTypes.IMMOVABLE, Unit.INSTANCE)
        } else {
            itemStack.setNoxesiumComponent(CommonItemComponentTypes.IMMOVABLE, null)
        }
    }

    fun hoverSound(itemStack: ItemStack, value: HoverSound? = null) {
        itemStack.setNoxesiumComponent(CommonItemComponentTypes.HOVER_SOUND, value)
    }

    fun hoverable(itemStack: ItemStack, value: Hoverable? = null) {
        itemStack.setNoxesiumComponent(CommonItemComponentTypes.HOVERABLE, value)
    }

    fun qibBehavior(itemStack: ItemStack, value: String? = null) {
        val key = value?.let { Key.key(it) }
        itemStack.setNoxesiumComponent(CommonItemComponentTypes.QIB_BEHAVIOR, key)
    }
}