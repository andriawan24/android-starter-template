package com.andriawan.common

import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    private const val DEFAULT_FORMAT_DATE = "yyyy-mm-dd"

    fun getYearFromDateString(dateInput: String): String? {
        return try {
            val dateInputFormat = SimpleDateFormat(DEFAULT_FORMAT_DATE, Locale.getDefault())
            val date = dateInputFormat.parse(dateInput)
            val calendar = Calendar.getInstance()
            if (date != null) {
                calendar.time = date
            }
            calendar.get(Calendar.YEAR).toString()
        } catch (e: ParseException) {
            Timber.e(e)
            null
        }
    }
}
