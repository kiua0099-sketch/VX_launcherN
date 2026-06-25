package com.vx.launcher

import android.content.Context
import java.io.File

class LauncherCore(private val context: Context) {

    private val gameDir = File(context.getExternalFilesDir(null), "minecraft")
    private val modsDir = File(gameDir, "mods")
    private val javaDir = File(gameDir, "java")
    private val versionsDir = File(gameDir, "versions")

    init {
        gameDir.mkdirs()
        modsDir.mkdirs()
        javaDir.mkdirs()
        versionsDir.mkdirs()
    }

    fun launchGame(accountName: String, javaVersion: String, minecraftVersion: String) {
        try {
            val javaPath = "${javaDir}/java"
            val minecraftJar = "${versionsDir}/$minecraftVersion/minecraft.jar"

            val process = Runtime.getRuntime().exec(
                arrayOf(
                    javaPath,
                    "-Xmx4G",
                    "-Xms2G",
                    "-XX:+UseG1GC",
                    "-Dfml.ignoreInvalidMinecraftCertificates=true",
                    "-Dfml.ignorePatchDiscrepancies=true",
                    "-cp",
                    minecraftJar,
                    "net.minecraft.client.main.Main",
                    "--username=$accountName",
                    "--version=$minecraftVersion",
                    "--gameDir=$gameDir"
                )
            )

            process.waitFor()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getGameDirectory(): File = gameDir
    fun getModsDirectory(): File = modsDir
    fun getVersionsDirectory(): File = versionsDir
}
