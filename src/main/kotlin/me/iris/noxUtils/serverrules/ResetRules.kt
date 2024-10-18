package me.iris.noxUtils.rules

import com.noxcrew.noxesium.paper.api.rule.RemoteServerRule
import me.iris.noxUtils.NoxUtils
import org.bukkit.entity.Player

public class ResetRules {

    private var manager = NoxUtils.noxesiumManager

    public fun tridentCollision(player: Player) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, 0)

        rule!!.reset()
        manager.updateServerRules(player)
    }

    public fun heldItemNameOffset(player: Player) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, 3)

        rule!!.reset()
        manager.updateServerRules(player)
    }

    public fun cameraLocked(player: Player) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, 4)

        rule!!.reset()
        manager.updateServerRules(player)
    }

    public fun disableVanillaMusic(player: Player) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, 5)

        rule!!.reset()
        manager.updateServerRules(player)
    }

    public fun disableBoatCollisions(player: Player) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, 6)

        rule!!.reset()
        manager.updateServerRules(player)
    }

    public fun showMapInUi(player: Player) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, 10)

        rule!!.reset()
        manager.updateServerRules(player)
    }

    public fun disableMapInUi(player: Player) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, 16)

        rule!!.reset()
        manager.updateServerRules(player)
    }

    public fun riptideCoyoteTime(player: Player) {
        val rule: RemoteServerRule<Any>? = manager.getServerRule(player, 17)

        rule!!.reset()
        manager.updateServerRules(player)
    }

}