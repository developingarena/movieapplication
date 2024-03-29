package com.movieapplication.service

import com.movieapplication.dto.MovieDTO
import com.movieapplication.mapper.MovieMapper
import com.movieapplication.repository.MovieRepository
import org.springframework.stereotype.Service

@Service
class MovieServiceImpl(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper
) :
    MovieService {

    override fun createMovie(movieDTO: MovieDTO): MovieDTO {
        println("Service Layer..")
        if (movieDTO.id != -1) {
            throw IllegalArgumentException("ILLEGAL ARGUMENTS")
        }


        val movie = movieRepository.save(movieMapper.toEntity(movieDTO))
        println("Movie added in DB: $movie")
        return movieMapper.fromEntity(movie);


    }

    override fun getMovies(): List<MovieDTO> {

        val movieItr = movieRepository.findAll()
        println("movie from DB $movieItr")

        if (movieItr.none()) {
            throw Exception("Movies not available in DB")
        }

        val movies = mutableListOf<MovieDTO>()
        movieItr.forEach {
            (movies.add(movieMapper.fromEntity(it)))
        }
        return movies
    }

    override fun getAMovie(id: Int): MovieDTO {
        var optional = movieRepository.findById(id)
        var movie = optional.orElseThrow { Exception("Movie ID $id not present") }
        return movieMapper.fromEntity(movie)
    }

    override fun updateMovie(movieDTO: MovieDTO): MovieDTO {

        getAMovie(movieDTO.id)

        if(movieDTO.rating == 0.0 || movieDTO.name =="Default Movie"){
            throw Exception("Input Movie Object Error")
        }
        movieRepository.save(movieMapper.toEntity(movieDTO))
        return movieDTO
    }

    override fun deleteMovie(id: Int) {
        getAMovie(id)
        movieRepository.deleteById(id)
    }

}