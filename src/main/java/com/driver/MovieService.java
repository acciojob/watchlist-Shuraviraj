package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public String addMovie(Movie movie) {
        movieRepository.addMovie(movie);
        return "movie added successfully";
    }

    public String addDirector(Director director) {
        movieRepository.addDirector(director);
        return "director added successfully";
    }

    public String addMovieDirectorPair(String movieName, String directorName) {
        Movie movie = getMovieByName(movieName);
        Director director = getDirectorByName(directorName);
        if (movie != null && director != null) movieRepository.addMovieDirectorPair(movie, director);
        return "movie and director paired successfully";
    }

    public Movie getMovieByName(String name) {
        return movieRepository.getMovieByName(name);
    }

    public Director getDirectorByName(String name) {
        return movieRepository.getDirectorByName(name);
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        return movieRepository.getMoviesByDirectorName(directorName);
    }

    public List<String> findAllMovies() {
        return movieRepository.findAllMovies();
    }

    public String deleteDirectorByName(String directorName) {
        movieRepository.deleteDirectorByName(directorName);
        return "Director and movie Deleted successfully";
    }

    public String deleteAllDirectors() {
        movieRepository.deleteAllDirectors();
        return "all director deleted successfully";
    }
}
