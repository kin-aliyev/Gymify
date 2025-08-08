package com.example.gymify.settings.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.net.toUri

private fun isInstagramInstalled(context: Context): Boolean {
    return try {
        context.packageManager.getPackageInfo("com.instagram.android", 0)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}
// https://www.instagram.com/kin.d1darov/
fun openInstagramProfile(context: Context, username: String = "kin.d1darov") {
    try {
        if (isInstagramInstalled(context)) {
            val instagramIntent = Intent(Intent.ACTION_VIEW).apply {
                data = "http://instagram.com/_u/$username".toUri()
                setPackage("com.instagram.android")
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(instagramIntent)
        } else openInstagramInBrowser(context, username)
    } catch (e: ActivityNotFoundException) {
        openInstagramInBrowser(context, username)
    } catch (e: Exception) {
        Toast.makeText(
            context,
            "Error opening Instagram: ${e.message}",
            Toast.LENGTH_LONG
        ).show()
    }
}

private fun openInstagramInBrowser(context: Context, username: String = "kin.d1darov") {
    try {
        val browserIntent = Intent(Intent.ACTION_VIEW).apply {
            data = "https://instagram.com/$username/".toUri()
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(browserIntent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(
            context,
            "The link cannot be opened. Please check your browser.",
            Toast.LENGTH_LONG
        ).show()
    }
}