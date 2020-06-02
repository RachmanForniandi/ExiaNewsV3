package com.example.exianewsv3.helpers

import java.text.ParseException
import java.text.SimpleDateFormat



    fun formNewsApiDate(inputDate: String?): String? {
        if (inputDate == null) return null
        try {
            val inputDateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
            val outputDateFormat = "EEE, d MMM yyyy KK:mm"
            val inputFormat =
                SimpleDateFormat(inputDateFormat)
            val outputFormat =
                SimpleDateFormat(outputDateFormat)
            val date = inputFormat.parse(inputDate)
            return outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return inputDate
    }
