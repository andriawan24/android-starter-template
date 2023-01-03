package com.andriawan.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.andriawan.data.local.converter.TypeConverter
import com.andriawan.data.local.dao.GamesDAO
import com.andriawan.domain.models.GameModel

@Database(entities = [GameModel::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class TemplateDatabase: RoomDatabase() {
    abstract fun gamesDao(): GamesDAO
}