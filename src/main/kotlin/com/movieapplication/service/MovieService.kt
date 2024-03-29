package com.movieapplication.service

import com.movieapplication.dto.MovieDTO

interface MovieService {
    fun createMovie(movieDTO : MovieDTO): MovieDTO

    fun getMovies(): List<MovieDTO>

    fun getAMovie(id: Int) : MovieDTO

    fun updateMovie(movieDTO: MovieDTO): MovieDTO

    fun deleteMovie(id : Int)
}