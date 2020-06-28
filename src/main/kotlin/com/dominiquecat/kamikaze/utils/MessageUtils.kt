package com.dominiquecat.kamikaze.utils

import com.dominiquecat.kamikaze.config.Handler
import org.bukkit.entity.Player

class MessageUtils {
    companion object {
        fun sendMessage(player: Player, message: String) {
            player.sendMessage("${Handler.getPrefix()}$message")
        }
    }
}