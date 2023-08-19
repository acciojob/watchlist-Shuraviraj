package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepository {
    private List<Movie> movieDb = new ArrayList<>();
    private List<Director> directorDb = new ArrayList<>();
    private List<Pair> movie2Director = new ArrayList<>();

    public void addMovie(Movie movie) {
        try {
            movieDb.add(movie);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addDirector(Director director) {
        try {
            directorDb.add(director);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addMovieDirectorPair(Movie movie, Director director) {
        try {
            movie2Director.add(new Pair(movie, director));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Movie getMovieByName(String name) {
        try {
            for (var movie : movieDb) {
                if (movie.getName().equals(name)) return movie;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Director getDirectorByName(String name) {
        try {
            for (var director : directorDb) {
                if (director.getName().equals(name)) return director;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        List<String> res = new ArrayList<>();
        try {
            for (var pair : movie2Director) {
                if (pair.getDirector().getName().equals(directorName)) {
                    res.add(pair.getMovie().getName());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return res;
    }

    public List<String> findAllMovies() {
        List<String> res = new ArrayList<>();
        try {
            for (var movie : movieDb) {
                res.add(movie.getName());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    public void deleteDirectorByName(String directorName) {
        Director director = getDirectorByName(directorName);
        directorDb.remove(director);
        try {
            for (var pair : movie2Director) {
                if (pair.getDirector().equals(director)) {
                    movie2Director.remove(pair);
                    movieDb.remove(pair.getMovie());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteAllDirectors() {
        List<Movie> movieToBeDeleted = new ArrayList<>();
        try {
            for (var pair : movie2Director) {
                movieToBeDeleted.add(pair.getMovie());
            }
            movieDb.removeAll(movieToBeDeleted);
            directorDb.clear();
            movie2Director.clear();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

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
