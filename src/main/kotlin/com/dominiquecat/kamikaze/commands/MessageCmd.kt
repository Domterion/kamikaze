package com.dominiquecat.kamikaze.commands

import com.dominiquecat.kamikaze.config.Handler
import com.dominiquecat.kamikaze.utils.MessageUtils
import com.dominiquecat.kamikaze.utils.UserMessageUtils
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player


class MessageCmd : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender !is Player) return true

        if(!sender.hasPermission("kamikaze.message.send")) {
            MessageUtils.sendMessage(sender, Handler.missingPerms())
            return true
        }
        if(args.size < 2) {
            MessageUtils.sendMessage(sender, Handler.msgInvalidAmountOfArgs(2))
            return true
        }

        val receive = Bukkit.getPlayerExact(args[0])
        if(sender == receive) {
            MessageUtils.sendMessage(sender, Handler.msgMessageSelf())
            return true
        }
        if(receive == null) {
            MessageUtils.sendMessage(sender, Handler.invalidUser())
            return true
        }
        if(!receive.hasPermission("kamikaze.message.receive")) {
            MessageUtils.sendMessage(sender, Handler.msgNotOpen())
            return true
        }

        var message = ""
        for (i in 1 until args.size) {
            message += args[i] + " "
        }

        UserMessageUtils.doUserMessage(sender, receive, Handler.msgGetFormat(sender, receive, message, ">>"))
        UserMessageUtils.doUserMessage(receive, sender, Handler.msgGetFormat(receive, sender, message, "<<"))
        return true
    }
}