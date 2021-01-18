package com.laam.base.news_cleanarch.ext

import android.annotation.SuppressLint
import android.text.format.DateUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by luthfiarifin on 1/10/2021.
 */
@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@SuppressLint("SimpleDateFormat")
object TimeUtils {

    fun String.toTimeAgo(): CharSequence? {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

        return try {
            val time: Long = sdf.parse(this).time
            val now = System.currentTimeMillis()

            DateUtils.getRelativeTimeSpanString(time, now, DateUtils.SECOND_IN_MILLIS)
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    fun String.dateFormat(format: String = "dd MMM yyyy"): CharSequence? {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

        return try {
            sdf.parse(this)?.let { date ->
                SimpleDateFormat(format, Locale.getDefault()).format(date)
            }
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }
}