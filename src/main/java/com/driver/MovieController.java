package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        String response = movieService.addMovie(movie);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director) {
        String response = movieService.addDirector(director);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie") String movieName,
                                                       @RequestParam("director") String directorName) {
        String response = movieService.addMovieDirectorPair(movieName, directorName);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String name) {
        Movie movie = movieService.getMovieByName(name);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String name) {
        Director director = movieService.getDirectorByName(name);
        return ResponseEntity.ok(director);
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("director") String directorName) {
        List<String> movies = movieService.getMoviesByDirectorName(directorName);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies() {
        List<String> movies = movieService.findAllMovies();
        return ResponseEntity.ok(movies);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("director") String directorName) {
        String response = movieService.deleteDirectorByName(directorName);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors() {
        String response = movieService.deleteAllDirectors();
        return ResponseEntity.ok(response);
    }
}
