package com.vx.launcher

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File

class ModManager(private val context: Context) {

    private val modsDir = File(context.getExternalFilesDir(null), "minecraft/mods")

    init {
        modsDir.mkdirs()
    }

    fun downloadMod(modUrl: String, modName: String, onProgress: (Int) -> Unit) {
        try {
            val modFile = File(modsDir, "$modName.jar")

            val client = OkHttpClient()
            val request = Request.Builder().url(modUrl).build()
            val response = client.newCall(request).execute()

            response.body?.byteStream()?.use { input ->
                modFile.outputStream().use { output ->
                    input.copyTo(output)
                }
            }

            onProgress(100)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun installMod(modFile: File) {
        val destination = File(modsDir, modFile.name)
        modFile.copyTo(destination, overwrite = true)
    }

    fun removeMod(modName: String) {
        val modFile = File(modsDir, "$modName.jar")
        if (modFile.exists()) {
            modFile.delete()
        }
    }

    fun getInstalledMods(): List<String> {
        return modsDir.listFiles()?.map { it.nameWithoutExtension } ?: emptyList()
    }
}
