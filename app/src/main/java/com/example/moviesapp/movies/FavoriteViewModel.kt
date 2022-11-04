package com.example.moviesapp.movies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.moviesapp.movies.favorite.FavoriteEntity
import com.example.moviesapp.room.FavoriteDB
import com.example.moviesapp.room.FavoriteDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private var favoriteDao: FavoriteDao?
    private var favoriteDB: FavoriteDB?

    init {
        favoriteDB = FavoriteDB.getDatabase(application)
        favoriteDao = favoriteDB?.favoriteDao()
    }

    fun addToFavorite(id: Int,
                      moviePosterPath: String,
                      movieName: String,
                       ) {
        CoroutineScope(Dispatchers.IO).launch {
            var movie = FavoriteEntity(id, movieName, moviePosterPath)
            favoriteDao?.insertFavorite(movie)
        }
    }

    suspend fun checkUser(id: Int) = favoriteDao?.checkMovie(id)

    fun removeFromFavorite(id: Int,
                           moviePosterPath: String,
                           movieName: String,
                           ) {
        CoroutineScope(Dispatchers.IO).launch {
            var movie = FavoriteEntity(id, movieName, moviePosterPath)
            favoriteDao?.deleteFavorite(movie)
        }
    }

    fun getFavoriteMovie(): LiveData<List<FavoriteEntity>>? {
        return favoriteDao?.getDataFavorite()
    }
}