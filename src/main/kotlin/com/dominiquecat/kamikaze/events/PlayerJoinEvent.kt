package com.dominiquecat.kamikaze.events

import com.dominiquecat.kamikaze.config.Handler
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.scoreboard.Scoreboard


class PlayerJoinEvent : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {

        val player = event.player

        val top = Handler().tabTop(player)
        val bottom = Handler().tabBottom(player)

        val bc = Handler().welcomerBroadcast(player)
        val toPlayer = Handler().welcomerPlayer(player)

        player.setPlayerListHeaderFooter(top, bottom)

        if(bc.isNotEmpty()) Bukkit.broadcastMessage(bc)
        if(toPlayer.isNotEmpty()) player.sendMessage(toPlayer)
        if(!Handler().welcomerEnabled()) {
            event.joinMessage = null
        }
    }
}