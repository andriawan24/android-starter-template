package com.andriawan.data.local.dao

import androidx.room.*
import com.andriawan.domain.models.GameModel

@Dao
interface GamesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: GameModel)

    @Query("SELECT * FROM GameModel")
    suspend fun getAllGames(): List<GameModel>

    @Query("SELECT * FROM GameModel WHERE GameModel.id = :gameId")
    suspend fun getGame(gameId: Int): GameModel?

    @Query("DELETE FROM GameModel")
    suspend fun deleteGames()

    @Delete
    suspend fun deleteGame(game: GameModel)
}