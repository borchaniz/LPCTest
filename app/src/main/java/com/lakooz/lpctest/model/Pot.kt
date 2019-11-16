package com.lakooz.lpctest.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pot(
    @PrimaryKey
    val identifier: String,
    val name: String,
    val category: Int
    // TODO : add missing fields
) {


    companion object {
        const val CATEGORY_BIRTHDAY = 0
        const val CATEGORY_FAREWELL = 1
        const val CATEGORY_SOLIDARITY = 2
    }
}