package com.dominiquecat.kamikaze.config

import com.dominiquecat.kamikaze.Kamikaze
import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import java.io.File


class Handler {
    companion object {
        var plugin: Kamikaze? = Bukkit.getPluginManager().getPlugin("Kamikaze") as Kamikaze?
        var configFile: File = File(plugin?.getDataFolder().toString() + "/config.yml")
        var config: FileConfiguration = YamlConfiguration.loadConfiguration(configFile)

        fun getPrefix(): String {
            var prefix = config.getString("prefix")

            if(prefix.isNullOrEmpty()) prefix = "&f&l[&r&4Kamikaze&r&f&l] "

            return prefix.replace("&", "§")
        }

        fun missingPerms(): String {
            var msg = config.getString("missing-perms")

            if(msg.isNullOrEmpty()) msg = "You dont have permissions to use this command."

            return msg.replace("&", "§")
        }
        fun invalidUser(): String {
            var msg = config.getString("invalid-user")

            if(msg.isNullOrEmpty()) msg = "That user was not found."

            return msg.replace("&", "§")
        }

        fun msgInvalidAmountOfArgs(count: Int): String {
            var msg = config.getString("message.invalid-amount-of-args")

            if(msg.isNullOrEmpty()) msg = "This command can only have {count} args."

            return msg.replace("&", "§").replace("{count}", count.toString())
        }
        fun msgGetFormat(sender: Player, receive: Player, message: String, sep: String): String {
            var msg = config.getString("message.format")

            if(msg.isNullOrEmpty()) msg = "[{sender}] {sep} [{receive}]: {message}"

            return msg.replace("&", "§").replace("{sender}", sender.displayName).replace("{receive}", receive.displayName).replace("{message}", message).replace("{sep}", sep)
        }
        fun msgNotOpen(): String {
            var msg = config.getString("message.not-open")

            if(msg.isNullOrEmpty()) msg = "You cant message this user."

            return msg.replace("&", "§")
        }
        fun msgMessageSelf(): String {
            var msg = config.getString("message.cant-message-self")

            if(msg.isNullOrEmpty()) msg = "You cant message yourself"

            return msg.replace("&", "§")
        }

    }
}