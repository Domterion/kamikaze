package com.dominiquecat.kamikaze.commands

import com.dominiquecat.kamikaze.config.Handler
import com.dominiquecat.kamikaze.utils.MessageUtils
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*


class MeowCmd : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender !is Player) return true

        if(!sender.hasPermission("kamikaze.meow.use")) MessageUtils.sendMessage(sender, Handler.missingPerms())

        MessageUtils.sendMessage(sender, "Meow!")
        return true
    }
}