package me.iris.noxUtils.serverrules

import com.noxcrew.noxesium.paper.api.rule.RemoteServerRule
import me.iris.noxUtils.NoxUtils
import org.bukkit.entity.Player

public class SetRules {

    private var manager = NoxUtils.noxesiumManager

    public fun tridentCollision(player: Player, value: Boolean) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, 0)

        rule!!.setValue(value)
        manager.updateServerRules(player)
    }

    public fun heldItemNameOffset(player: Player, value: Int) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, 3)

        rule!!.setValue(value)
        manager.updateServerRules(player)
    }

    public fun cameraLocked(player: Player, value: Boolean) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, 4)

        rule!!.setValue(value)
        manager.updateServerRules(player)
    }

    public fun disableVanillaMusic(player: Player, value: Boolean) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, 5)

        rule!!.setValue(value)
        manager.updateServerRules(player)
    }

    public fun disableBoatCollisions(player: Player, value: Boolean) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, 6)

        rule!!.setValue(value)
        manager.updateServerRules(player)
    }

    public fun showMapInUi(player: Player, value: Boolean) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, 10)

        rule!!.setValue(value)
        manager.updateServerRules(player)
    }

    public fun disableMapInUi(player: Player, value: Boolean) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, 16)

        rule!!.setValue(value)
        manager.updateServerRules(player)
    }

    public fun riptideCoyoteTime(player: Player, value: Int) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, 17)

        rule!!.setValue(value)
        manager.updateServerRules(player)
    }

}