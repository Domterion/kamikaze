package com.dominiquecat.kamikaze

import com.dominiquecat.kamikaze.commands.MeowCmd
import com.dominiquecat.kamikaze.commands.MessageCmd
import com.dominiquecat.kamikaze.events.PlayerJoinEvent
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption


class Kamikaze : JavaPlugin() {
    override fun onEnable() {
        val config = File("$dataFolder/config.yml")

        if (!dataFolder.exists()) {
            dataFolder.mkdir()
        }
        if (!config.exists()) {
            val inputStream = getResource("config.yml")
            copy(inputStream!!, config.path)
        }

        getCommand("meow")?.setExecutor(MeowCmd())
        getCommand("message")?.setExecutor(MessageCmd())

        Bukkit.getPluginManager().registerEvents(PlayerJoinEvent(), this)

        println("Kamikaze enabled!")
    }

    fun copy(source: InputStream, destination: String): Boolean {
        var success = true
        try {
            Files.copy(source, Paths.get(destination), StandardCopyOption.REPLACE_EXISTING)
        } catch (ex: IOException) {
            ex.printStackTrace()
            success = false
        }
        return success
    }
}