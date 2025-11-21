package com.example.gymify.settings.util

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.net.toUri

fun openTelegramProfile(context: Context, username: String = "kin_aliyev") {
    val telegramAppAndWebUri = "https://t.me/$username"
    try {

        val appIntent = Intent(Intent.ACTION_VIEW, telegramAppAndWebUri.toUri()).apply {
            setPackage("org.telegram.messenger")
        }

        if (appIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(appIntent)
        } else {
            val webIntent = Intent(Intent.ACTION_VIEW, telegramAppAndWebUri.toUri())
            context.startActivity(webIntent)
        }
    } catch (e: Exception) {
        Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}