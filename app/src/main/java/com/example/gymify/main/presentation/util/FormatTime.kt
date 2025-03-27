package com.example.gymify.main.presentation.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun formatWorkoutTime(timestamp: Instant): String {
    val zonedDateTime = timestamp.atZone(ZoneId.systemDefault())
    return DateTimeFormatter.ofPattern("MMM d, yyyy h:mm a").format(zonedDateTime)
}