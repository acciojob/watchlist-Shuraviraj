package com.driver;

import com.driver.model.Director;
import com.driver.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MovieRepository {
    private List<Movie> movieDb = new ArrayList<>();
    private List<Director> directorDb = new ArrayList<>();
    private List<Pair> movie2Director = new ArrayList<>();

    public void addMovie(Movie movie) {
        movieDb.add(movie);
    }

    public void addDirector(Director director) {
        directorDb.add(director);
    }

    public void addMovieDirectorPair(Movie movie, Director director) {
        movie2Director.add(new Pair(movie, director));
    }

    public Movie getMovieByName(String name) {
        for (var movie : movieDb) {
            if (movie.getName().equals(name)) return movie;
        }
        return null;
    }

    public Director getDirectorByName(String name) {
        for (var director : directorDb) {
            if (director.getName().equals(name)) return director;
        }
        return null;
    }

    public List<Movie> getMoviesByDirectorName(String directorName) {
        return movie2Director.stream()
                .filter(i -> i.getDirector()
                        .getName()
                        .equals(directorName))
                .map(Pair::getMovie)
                .collect(Collectors.toList());
    }

    public List<Movie> findAllMovies() {
        return movieDb;
    }

    public void deleteDirectorByName(String directorName) {
        Director director = getDirectorByName(directorName);
        directorDb.remove(director);
        for (var pair : movie2Director) {
            if (pair.getDirector().equals(director)) {
                movie2Director.remove(pair);
                movieDb.remove(pair.getMovie());
            }
        }
    }

    public void deleteAllDirectors() {
        List<Movie> movieToBeDeleted = new ArrayList<>();
        for (var pair : movie2Director) {
            movieToBeDeleted.add(pair.getMovie());
        }
        movieDb.removeAll(movieToBeDeleted);
        directorDb.clear();
        movie2Director.clear();
    }

    private class Pair {
        private Movie movie;
        private Director director;

        public Pair(Movie movie, Director director) {
            this.movie = movie;
            this.director = director;
        }

        public Pair() {
        }

        public Movie getMovie() {
            return movie;
        }

        public void setMovie(Movie movie) {
            this.movie = movie;
        }

        public Director getDirector() {
            return director;
        }

        public void setDirector(Director director) {
            this.director = director;
        }
    }

}
