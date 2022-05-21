package com.andriawan.common

import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun getYear(dateInput: String) : String {
        return try {
            val dateInputFormat = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault())
            val date = dateInputFormat.parse(dateInput)
            val calendar = Calendar.getInstance()
            calendar.time = date!!
            calendar.get(Calendar.YEAR).toString()
        } catch (e: ParseException) {
            Timber.e(e)
            "Invalid date format!"
        }
    }
}
