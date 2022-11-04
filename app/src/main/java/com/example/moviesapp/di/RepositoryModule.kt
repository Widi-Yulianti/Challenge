package com.example.moviesapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.moviesapp.repository.MovieMapper
import com.example.moviesapp.repository.MovieMapperImpl
import com.example.moviesapp.repository.MovieRepository
import com.example.moviesapp.repository.MovieRepositoryImpl
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun provideMovieMapper(movieMapperImpl: MovieMapperImpl): MovieMapper

    @Binds
    @Singleton
    fun provideBookRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

}