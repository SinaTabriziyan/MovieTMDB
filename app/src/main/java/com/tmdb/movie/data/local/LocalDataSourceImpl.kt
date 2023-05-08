package com.tmdb.movie.data.local

import android.util.Log
import com.tmdb.movie.data.local.db.MovieDao
import com.tmdb.movie.model.ui.MovieItem
import com.tmdb.movie.util.mapper.asMovieEntity
import com.tmdb.movie.util.mapper.asMoviesItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalDataSourceImpl(private val movieDao: MovieDao) : LocalDataSource {
    override fun getSelectedMovies(): Flow<List<MovieItem>> = flow {
        movieDao.getSelectedMovies().collect { moviesEntity ->
           emit(moviesEntity.asMoviesItem())
        }
    }

    override suspend fun insertMovie(movieItem: MovieItem) {
        movieDao.insertMovie(movieItem.asMovieEntity())
    }

    override suspend fun deleteMovie(movieItem: MovieItem) {
        movieDao.deleteMovie(movieItem.asMovieEntity())
    }
}

