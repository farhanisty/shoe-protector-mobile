package com.example.shoeprotector

import android.os.Build
import android.text.format.DateUtils
import androidx.annotation.RequiresApi
import java.time.Instant

@RequiresApi(Build.VERSION_CODES.O)
fun getRelativeTime(timeString: String): String {
    val instant = Instant.parse(timeString)
    val timeMillis = instant.toEpochMilli()
    val nowMillis = System.currentTimeMillis()

    return DateUtils.getRelativeTimeSpanString(
        timeMillis,
        nowMillis,
        DateUtils.MINUTE_IN_MILLIS
    ).toString()
}