package com.maruf.movieflix.utils

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*


@SuppressLint("SimpleDateFormat")
fun Fragment.timeFormat(zoneTime: String): String {

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

        val parsed =
            ZonedDateTime.parse(zoneTime, DateTimeFormatter.ISO_DATE_TIME).withZoneSameInstant(
                ZoneId.of(TimeZone.getDefault().id)
            )
        //  val parsedDate = LocalDateTime.parse(zoneTime, DateTimeFormatter.ISO_DATE_TIME)
        //sun mar 5 2023 12:21 PM
        parsed.format(DateTimeFormatter.ofPattern("EEE MMM dd yyyy hh:mm a"))
    } else {
        val parser = SimpleDateFormat("EEE MMM dd yyyy hh:mm a")
        val formatter = SimpleDateFormat("EEE MMM dd yyyy hh:mm a")
        formatter.format(parser.parse(zoneTime))
    }


}

fun Any.dateFormatOnlyYear(date: String): String {

    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val date = dateFormat.parse(date)
    val year = SimpleDateFormat("yyyy").format(date)
    return year


}
fun Any.dateFormat(date: String): String {

    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val date = dateFormat.parse(date)
    val formatedDate = SimpleDateFormat("MMMM dd yyyy").format(date)
    return formatedDate


}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}


fun View.show() {
    visibility = View.VISIBLE
}



