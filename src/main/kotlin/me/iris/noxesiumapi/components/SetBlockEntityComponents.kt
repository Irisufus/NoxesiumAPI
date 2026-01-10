package me.iris.noxesiumapi.components

import com.noxcrew.noxesium.core.registry.CommonBlockEntityComponentTypes
import com.noxcrew.noxesium.paper.component.setNoxesiumComponent
import org.bukkit.block.Block
import org.bukkit.block.TileState

@Suppress("unused")
object SetBlockEntityComponents {

    fun beaconBeamHeight(block: Block, value: Int? = null) {
        val blockEntity = block.state as? TileState ?: return

        blockEntity.setNoxesiumComponent(CommonBlockEntityComponentTypes.BEACON_BEAM_HEIGHT, value)
    }
}