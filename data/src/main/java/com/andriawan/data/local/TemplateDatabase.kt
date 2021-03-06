package com.andriawan.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.andriawan.data.local.converter.TypeConverter
import com.andriawan.data.local.dao.GamesDAO
import com.andriawan.domain.models.Games

@Database(entities = [Games::class], version = 1)
@TypeConverters(TypeConverter::class)
abstract class TemplateDatabase: RoomDatabase() {
    abstract fun gamesDao(): GamesDAO
}