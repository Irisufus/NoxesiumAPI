package me.iris.noxesiumapi.util

import com.noxcrew.noxesium.api.protocol.rule.ServerRuleIndices

object ServerRules {
    var booleanServerRules: MutableMap<String, Int> = mutableMapOf(
        "disableSpinAttackCollision" to ServerRuleIndices.DISABLE_SPIN_ATTACK_COLLISIONS,
        "cameraLocked" to ServerRuleIndices.CAMERA_LOCKED,
        "disableVanillaMusic" to ServerRuleIndices.DISABLE_VANILLA_MUSIC,
        "disableBoatCollisions" to ServerRuleIndices.DISABLE_BOAT_COLLISIONS,
        "showMapInUi" to ServerRuleIndices.SHOW_MAP_IN_UI,
        "disableDeferredChunkUpdates" to ServerRuleIndices.DISABLE_DEFERRED_CHUNK_UPDATES,
        "enableSmootherClientTrident" to ServerRuleIndices.ENABLE_SMOOTHER_CLIENT_TRIDENT,
        "disableMapUi" to ServerRuleIndices.DISABLE_MAP_UI,
        "riptidePreCharging" to ServerRuleIndices.RIPTIDE_PRE_CHARGING,
    )

    var integerServerRules: MutableMap<String, Int> = mutableMapOf(
        "heldItemNameOffset" to ServerRuleIndices.HELD_ITEM_NAME_OFFSET,
        "riptideCoyoteTime" to ServerRuleIndices.RIPTIDE_COYOTE_TIME
    )

    var itemStackServerRules: MutableMap<String, Int> = mutableMapOf(
        "handItemOverride" to ServerRuleIndices.HAND_ITEM_OVERRIDE,
    )

    var optionalEnumServerRules: MutableMap<String, Int> = mutableMapOf(
        "overrideGraphicsMode" to ServerRuleIndices.OVERRIDE_GRAPHICS_MODE
    )

    var intListServerRule: MutableMap<String, Int> = mutableMapOf(
        "restrictDebugOptions" to ServerRuleIndices.RESTRICT_DEBUG_OPTIONS
    )

    var allRules: MutableMap<String, Int> = mutableMapOf()

    fun createAllRules() {
        allRules.putAll(booleanServerRules)
        allRules.putAll(integerServerRules)
        allRules.putAll(itemStackServerRules)
        allRules.putAll(optionalEnumServerRules)
        allRules.putAll(intListServerRule)
        allRules.put("customCreativeItems", ServerRuleIndices.CUSTOM_CREATIVE_ITEMS)
        allRules.put("all", -1)
    }

}