package com.dominiquecat.kamikaze.config

import com.dominiquecat.kamikaze.Kamikaze
import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import java.io.File


class Handler {
    private var plugin: Kamikaze? = Bukkit.getPluginManager().getPlugin("Kamikaze") as Kamikaze?
    var configFile: File = File(plugin?.getDataFolder().toString() + "/config.yml")
    var config: FileConfiguration = YamlConfiguration.loadConfiguration(configFile)

    fun getKey(key: String): String {
        var str = config.getString(key)

        if(str == null) str = "No value in config for key: $key"

        return str
    }

    fun getPrefix(): String {
        val key = getKey("prefix")
        return key.replace("&", "§")
    }

    fun missingPerms(): String {
        val key = getKey("missing-perms")
        return key.replace("&", "§")
    }
    fun invalidUser(): String {
        val key = getKey("invalid-user")
        return key.replace("&", "§")
    }

    fun msgInvalidAmountOfArgs(count: Int): String {
        val key = getKey("message.invalid-amount-of-args")
        return key.replace("&", "§").replace("{count}", count.toString())
    }
    fun msgGetFormat(sender: Player, receive: Player, message: String, sep: String): String {
        val key = getKey("message.format")
        return key.replace("&", "§").replace("{sender}", sender.displayName).replace("{receive}", receive.displayName).replace("{message}", message).replace("{sep}", sep)
    }
    fun msgNotOpen(): String {
        val key = getKey("message.not-open")
        return key.replace("&", "§")
    }
    fun msgMessageSelf(): String {
        val key = getKey("message.cant-message-self")
        return key.replace("&", "§")
    }
    fun msgNoRecent(): String {
        val key = getKey("message.no-recent")
        return key.replace("&", "§")
    }

    // These need players to get server information
    fun tabTop(player: Player): String {
        val key = config.getString("tab.top") ?: return ""

        return key.replace("&", "§").replace("{server}", player.server.name).replace("{player}", player.name)
    }
    fun tabBottom(player: Player): String {
        val key = config.getString("tab.bottom") ?: return ""

        return key.replace("&", "§").replace("{server}", player.server.name).replace("{player}", player.name)
    }
}