package me.iris.noxesiumapi.packets

import com.noxcrew.noxesium.paper.api.NoxesiumManager
import com.noxcrew.noxesium.paper.api.network.clientbound.ClientboundCustomSoundModifyPacket
import com.noxcrew.noxesium.paper.api.network.clientbound.ClientboundCustomSoundStartPacket
import com.noxcrew.noxesium.paper.api.network.clientbound.ClientboundCustomSoundStopPacket
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundSource
import org.bukkit.entity.Player
import org.joml.Vector3f


class SoundManager(private val manager: NoxesiumManager) {

    private val sounds: MutableMap<Int, ResourceLocation> = mutableMapOf()

    /**
     * Adds a sound to the sound manager.
     * @return The id of the added sound.
     */
    fun addSound(sound: ResourceLocation): Int {
        val size = sounds.size.inc()
        sounds[size] = sound
        return size

    }

    /**
     * Removes a sound from the sound manager.
     */
    fun removeSound(id: Int) {
        sounds.remove(id)
    }

    /**
     * Returns a sound from an id. (can be null)
     */
    fun getSound(id: Int): ResourceLocation? {
        return sounds[id]
    }

    /**
     * Returns all registered sounds in the sound manager.
     */
    fun getAllSounds(): Map<Int, ResourceLocation> {
        return sounds
    }

    /**
     * Starts to play a Noxesium custom sound to a player. If a sound with the same id
     * is already playing, that sound will be stopped.
     */
    fun playSound(
        player: Player,
        id: Int,
        category: SoundSource,
        looping: Boolean,
        attenuation: Boolean,
        ignoreIfPlaying: Boolean,
        volume: Float,
        pitch: Float,
        position: Vector3f,
        entityId: Int? = null,
        unix: Long? = null,
        offset: Float? = null
    ) {
        val sound = sounds[id] as ResourceLocation
        manager.sendPacket(player, ClientboundCustomSoundStartPacket(
            id,
            sound,
            category,
            looping,
            attenuation,
            ignoreIfPlaying,
            volume,
            pitch,
            position,
            entityId,
            unix,
            offset
        )
        )
    }

    /**
     * Stops playing a Noxesium custom sound by its id for a player.
     */
    fun stopSound(player: Player, id: Int) {
        manager.sendPacket(player, ClientboundCustomSoundStopPacket(id))
    }

    /**
     * Modifies the volume of a playing sound for a player. The interpolation time can be
     * used to fade the sound up or down over an amount of ticks
     */
    fun modifySound(
        player: Player,
        id: Int,
        volume: Float,
        interpolationTicks: Int,
        /** An optional volume to start the interpolation from. If absent the current volume of the sound is used instead. */
        startVolume: Float? = null
    ) {
        manager.sendPacket(player, ClientboundCustomSoundModifyPacket(id, volume, interpolationTicks, startVolume))
    }

}