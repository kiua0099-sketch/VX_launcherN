package com.vx.launcher

import android.content.Context
import android.content.SharedPreferences
import java.util.UUID

class OfflineAccount(context: Context) {

    private val prefs: SharedPreferences = 
        context.getSharedPreferences("vx_accounts", Context.MODE_PRIVATE)

    fun createAccount(username: String, uuid: String = UUID.randomUUID().toString()): Boolean {
        return try {
            prefs.edit().apply {
                putString("account_$username", uuid)
                putLong("created_$username", System.currentTimeMillis())
                apply()
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAccount(username: String): String? {
        return prefs.getString("account_$username", null)
    }

    fun getAllAccounts(): List<String> {
        return prefs.all.keys
            .filter { it.startsWith("account_") }
            .map { it.replace("account_", "") }
    }

    fun deleteAccount(username: String): Boolean {
        return try {
            prefs.edit().apply {
                remove("account_$username")
                remove("created_$username")
                apply()
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    fun accountExists(username: String): Boolean {
        return prefs.contains("account_$username")
    }
}
