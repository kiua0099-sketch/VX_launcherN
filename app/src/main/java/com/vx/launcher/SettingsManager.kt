package com.vx.launcher

import android.content.Context
import android.content.SharedPreferences

class SettingsManager(context: Context) {

    private val prefs: SharedPreferences = 
        context.getSharedPreferences("vx_settings", Context.MODE_PRIVATE)

    fun setMaxMemory(gb: Int) {
        prefs.edit().putInt("max_memory", gb).apply()
    }

    fun getMaxMemory(): Int = prefs.getInt("max_memory", 4)

    fun setNoLagMode(enabled: Boolean) {
        prefs.edit().putBoolean("no_lag_mode", enabled).apply()
    }

    fun isNoLagModeEnabled(): Boolean = prefs.getBoolean("no_lag_mode", false)

    fun setResolution(width: Int, height: Int) {
        prefs.edit().apply {
            putInt("resolution_width", width)
            putInt("resolution_height", height)
            apply()
        }
    }

    fun getResolution(): Pair<Int, Int> {
        val width = prefs.getInt("resolution_width", 1080)
        val height = prefs.getInt("resolution_height", 1920)
        return Pair(width, height)
    }

    fun setFPS(fps: Int) {
        prefs.edit().putInt("fps", fps).apply()
    }

    fun getFPS(): Int = prefs.getInt("fps", 60)

    fun setModLoader(loader: String) {
        prefs.edit().putString("mod_loader", loader).apply()
    }

    fun getModLoader(): String = prefs.getString("mod_loader", "VAR") ?: "VAR"

    fun setJavaVersion(version: String) {
        prefs.edit().putString("java_version", version).apply()
    }

    fun getJavaVersion(): String = prefs.getString("java_version", "17") ?: "17"

    fun setMinecraftVersion(version: String) {
        prefs.edit().putString("minecraft_version", version).apply()
    }

    fun getMinecraftVersion(): String = prefs.getString("minecraft_version", "1.20.1") ?: "1.20.1"
}
