package com.lakooz.lpctest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lakooz.lpctest.model.Pot


@Dao
abstract class PotDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun createOrUpdate(pot: Pot)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAllAndSynchronize(pots: List<Pot>)

    @Query("select * from pot where category = :category")
    abstract fun getPots(category: Int): List<Pot>

    @Query("select * from pot")
    abstract fun getPots(): List<Pot>
}