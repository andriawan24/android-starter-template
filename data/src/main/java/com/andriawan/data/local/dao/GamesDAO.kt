package com.andriawan.data.local.dao

import androidx.room.*
import com.andriawan.domain.models.Games

@Dao
interface GamesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: Games)

    @Query("SELECT * FROM games")
    suspend fun getAllGames(): List<Games>

    @Query("SELECT * FROM games WHERE games.id = :gameId")
    suspend fun getGame(gameId: Int): Games?

    @Query("DELETE FROM Games")
    suspend fun deleteGames()

    @Delete
    suspend fun deleteGame(game: Games)
}