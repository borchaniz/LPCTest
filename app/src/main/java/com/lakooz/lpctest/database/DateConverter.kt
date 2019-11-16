package com.lakooz.lpctest.database

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {

    @TypeConverter
    fun fromTimestamp(mills: Long?): Date? = if (mills != null) Date(mills) else null

    @TypeConverter
    fun fromDate(date: Date?): Long? = date?.time
}