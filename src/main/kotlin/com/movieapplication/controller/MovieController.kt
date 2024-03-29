package com.movieapplication.controller

import com.movieapplication.dto.MovieDTO
import com.movieapplication.service.MovieService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("movieapi")
class MovieController(private val movieService: MovieService) {


    @PostMapping("/addmovie/")
    fun createMovie(@RequestBody movieDTO: MovieDTO): ResponseEntity<MovieDTO> {

        println("Post Request")
        return ResponseEntity(movieService.createMovie(movieDTO), HttpStatus.CREATED)

    }

    @GetMapping("getallmovies")
    fun getMovies(): ResponseEntity<List<MovieDTO>> =
        ResponseEntity.ok(movieService.getMovies())


    @GetMapping("/findamovie/{id}")
    fun getMovie (@PathVariable id:Int) =
        ResponseEntity.ok(movieService.getAMovie(id))

    @PutMapping("/updateamovie/")
    fun updateMovie (@RequestBody movieDTO: MovieDTO) : ResponseEntity<MovieDTO> {

        return ResponseEntity.ok(movieService.updateMovie(movieDTO))
    }
    @DeleteMapping("/deleteamovie/{id}")
    fun deleteMovie (@PathVariable id:Int) : ResponseEntity<Unit> =
        ResponseEntity(movieService.deleteMovie(id), HttpStatus.NO_CONTENT)


}