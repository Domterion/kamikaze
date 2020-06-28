package com.dominiquecat.kamikaze.utils

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*
import kotlin.collections.HashMap


class UserMessageUtils {
    companion object {
        private val messages = HashMap<UUID, UUID>()

        fun doUserMessage(player: Player, receive: Player, message: String) {
            messages[player.uniqueId] = receive.uniqueId
            MessageUtils.sendMessage(player, message)
        }

        fun getUserMessage(player: Player): Player? {
            val lastMessage = messages[player.uniqueId]
            val receive = Bukkit.getPlayer(lastMessage!!)
            if (receive == null) {
                messages.remove(player.uniqueId)
                return null
            }
            return receive
        }
    }
}