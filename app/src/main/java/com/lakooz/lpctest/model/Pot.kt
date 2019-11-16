package com.lakooz.lpctest.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class Pot(
    @PrimaryKey
    @SerializedName("id")
    val identifier: String,
    val name: String,
    val creationDate: Date,
    val category: Int,
    val contributorsCount: Int,
    val amount: Float,
    val imageUrl: String
) {


    companion object {
        const val CATEGORY_BIRTHDAY = 0
        const val CATEGORY_FAREWELL = 1
        const val CATEGORY_SOLIDARITY = 2
    }
}