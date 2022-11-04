package com.example.moviesapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.moviesapp.movies.favorite.FavoriteEntity

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM FavoriteEntity ORDER BY id DESC")
    fun getDataFavorite() : LiveData<List<FavoriteEntity>>

    @Insert
    fun insertFavorite(movie: FavoriteEntity)

    @Delete
    fun deleteFavorite(movie: FavoriteEntity)

    @Query("SELECT * FROM FavoriteEntity WHERE id = :id")
    fun checkMovie(id: Int) : Int
}