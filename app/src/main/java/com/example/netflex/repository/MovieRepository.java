package com.example.netflex.repository;

import com.example.netflex.R;
import com.example.netflex.model.Movie;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MovieRepository {
    private final List<Movie> movies = new ArrayList<>();
    public static MovieRepository instance;

    public static MovieRepository getInstance() {
        if (instance == null) {
            instance = new MovieRepository();
        }
        return instance;
    }

    private MovieRepository() {
        String[] movieNames = {
                "2012",
                "A Ghost Story",
                "Assassin's Creed",
                "Knowing",
                "A Quiet Place",
                "The Conjuring 2",
                "The Mummy",
                "World War Z",
        };

        String[] movieDescriptions = {
                "2012 is a 2009 American disaster film directed by Roland Emmerich and written by Harald Kloser and Emmerich. It features an ensemble cast, including John Cusack, Chiwetel Ejiofor, Amanda Peet, Oliver Platt, Thandie Newton, Danny Glover, Woody Harrelson, and Morgan Freeman.",
                "A Ghost Story is a 2017 American supernatural drama film written and directed by David Lowery, and starring Casey Affleck and Rooney Mara. The film follows a recently deceased ghost who returns to his house to try to reconnect with his wife.",
                "Assassin's Creed is a 2016 American action-adventure film directed by Justin Kurzel and written by Michael Lesslie, Adam Cooper, Bill Collage, and Michael Lesslie. It is the first film adaptation of the video game franchise of the same name.",
                "Knowing is a 2009 American science fiction thriller film directed by Alex Proyas and written by Nicholas Kazan. The film stars Nicolas Cage, Rose Byrne, Chandler Canterbury, D. J. Cotrona, Lara Robinson, and Thomas Kretschmann.",
                "A Quiet Place is a 2018 American post-apocalyptic horror film written and directed by John Krasinski, who also stars alongside Emily Blunt, Millicent Simmonds, and Noah Jupe. The film follows a family of four who must live life in silence while hiding from creatures that hunt by sound.",
                "The Conjuring 2 is a 2016 American supernatural horror film directed by James Wan and written by Chad Hayes and Carey Hayes. A sequel to the 2013 film The Conjuring, it is the second installment in The Conjuring Universe franchise.",
                "The Mummy is a 2017 American action-adventure film directed by Alex Kurtzman and written by Jon Spaihts and Christopher McQuarrie. It is a reboot of The Mummy franchise and the first installment in Universal Pictures' Dark Universe.",
                "World War Z is a 2013 American apocalyptic action horror film directed by Marc Forster and written by Matthew Michael Carnahan, Drew Goddard, and Damon Lindelof, based on the 2006 novel of the same name by Max Brooks.",
        };

        int[] moviePosters = {
                R.drawable.movie_2012,
                R.drawable.a_ghost_story,
                R.drawable.assassins_creed,
                R.drawable.knowing,
                R.drawable.a_quiet_place,
                R.drawable.the_conjuring_2,
                R.drawable.the_mummy,
                R.drawable.world_war_z,
        };

        float[] movieScores = {
                6.3f,
                7.0f,
                5.8f,
                6.5f,
                7.5f,
                7.3f,
                5.5f,
                7.0f
        };

        float[] moviePrices = {
                5.99f,
                4.99f,
                5.99f,
                4.99f,
                5.99f,
                4.99f,
                5.99f,
                4.99f
        };

        String[] movieDirectors = {
                "Roland Emmerich",
                "David Lowery",
                "Justin Kurzel",
                "Alex Proyas",
                "John Krasinski",
                "James Wan",
                "Alex Kurtzman",
                "Marc Forster"
        };

        String[] movieCountries = {
                "United States",
                "United States",
                "United States",
                "United States",
                "United States",
                "United States",
                "United States",
                "United States"
        };

        Date[] releaseDate = {
                new Date(2009, 11, 13),
                new Date(2017, 7, 7),
                new Date(2016, 12, 21),
                new Date(2009, 3, 20),
                new Date(2018, 4, 5),
                new Date(2016, 5, 10),
                new Date(2017, 6, 9),
                new Date(2013, 6, 20)
        };

        String[] movieTrailerUrls = {
                "https://www.youtube.com/watch?v=ce0N3TEcFw0",
                "https://www.youtube.com/watch?v=c_3NMtxeyfk",
                "https://www.youtube.com/watch?v=4haJD6W136c",
                "https://www.youtube.com/watch?v=xR126-rJiW8",
                "https://www.youtube.com/watch?v=WR7cc5t7tv8",
                "https://www.youtube.com/watch?v=VFsmuRPClr4",
                "https://www.youtube.com/watch?v=IjHgzkQM2Sg",
                "https://www.youtube.com/watch?v=Md6Dvxdr0AQ"
        };

        for (int i = 0; i < movieNames.length; i++) {
            movies.add(
                    new Movie.MovieBuilder()
                            .setName(movieNames[i])
                            .setDescription(movieDescriptions[i])
                            .setPoster(moviePosters[i])
                            .setScore(movieScores[i])
                            .setPrice(moviePrices[i])
                            .setDirector(movieDirectors[i])
                            .setCountry(movieCountries[i])
                            .setReleaseDate(releaseDate[i])
                            .setTrailerUrl(movieTrailerUrls[i])
                            .build()
            );
        }
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public Movie getMovieById(int id) {
        return movies.stream().filter(movie -> movie.getId() == id).findFirst().orElse(null);
    }

    public List<Movie> getFavoriteMovies() {
        return movies.stream().filter(Movie::isFavorite).collect(Collectors.toList());
    }

    public Movie addToFavorites(int id) {
        Movie movie = getMovieById(id);
        movie.setFavorite(true);
        return movie;
    }

    public Movie removeFromFavorites(int id) {
        Movie movie = getMovieById(id);
        movie.setFavorite(false);
        return movie;
    }
}
