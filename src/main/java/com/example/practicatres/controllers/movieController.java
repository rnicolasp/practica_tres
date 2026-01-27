package com.example.practicatres.controllers;

import com.example.practicatres.models.entities.genre;
import com.example.practicatres.models.entities.movie;
import com.example.practicatres.models.entities.movie_cast;
import com.example.practicatres.models.entities.movie_cast_id;
import com.example.practicatres.models.entities.movie_crew;
import com.example.practicatres.models.entities.movie_crew_id;
import com.example.practicatres.models.entities.person;
import com.example.practicatres.services.genderService;
import com.example.practicatres.services.genreService;
import com.example.practicatres.services.movieCastService;
import com.example.practicatres.services.movieCrewService;
import com.example.practicatres.services.movieService;
import com.example.practicatres.services.personService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class movieController {
    @Autowired
    movieService movieService;

    @Autowired
    genreService genreService;

    @Autowired
    genderService genderService;

    @Autowired
    movieCastService movieCastService;

    @Autowired
    movieCrewService movieCrewService;

    @Autowired
    personService personService;

    @GetMapping("/movies")
    public String listMovies(@RequestParam(required = false) String title, @RequestParam(required = false) String actor, @RequestParam(required = false) String genre, Model model) {

        List<movie> movies = movieService.searchMovies(title, actor, genre);
        model.addAttribute("movies", movies);

        return "movies";
    }

    @GetMapping("/movies/nuevo")
    public String nuevaPelicula(Model model) {
        model.addAttribute("movie", new movie());
        model.addAttribute("tituloPagina", "Nueva Película");
        List<genre> allGenres = genreService.findAll();
        model.addAttribute("allGenres", allGenres);
        return "movie_editar";
    }

    @GetMapping("/movies/editar/{id}")
    public String editarPelicula(@PathVariable Integer id, Model model) {
        movie m = movieService.findById(id);
        model.addAttribute("movie", m);
        model.addAttribute("tituloPagina", "Editar Película");
        List<genre> allGenres = genreService.findAll();
        model.addAttribute("allGenres", allGenres);
        return "movie_editar";
    }

    @PostMapping("/movies/guardar")
    public String guardarPelicula(@ModelAttribute movie m, @RequestParam(required = false) Integer[] selectedGenres) {
        if (m.getMovie_id() != null) {
            movie movieExistente = movieService.findById(m.getMovie_id());
            m.setPopularity(movieExistente.getPopularity());
            m.setHomepage(movieExistente.getHomepage());
            m.setRevenue(movieExistente.getRevenue());
            m.setTagline(movieExistente.getTagline());
            m.setVote_count(movieExistente.getVote_count());
            m.setVote_average(movieExistente.getVote_average());
            m.setKeywords(movieExistente.getKeywords());
            m.setLanguages(movieExistente.getLanguages());
        }

        if (selectedGenres != null) {
            Set<genre> genres = new HashSet<>();
            for (Integer genreId : selectedGenres) {
                genre g = genreService.findById(genreId);
                if (g != null) {
                    genres.add(g);
                }
            }
            m.setGenres(genres);
        } else {
            m.setGenres(new HashSet<>());
        }

        movieService.save(m);
        return "redirect:/movies";
    }

    @GetMapping("/movies/detalles/{id}")
    public String detallesPelicula(@PathVariable Integer id, Model model) {
        movie m = movieService.findById(id);
        List<movie_cast> cast = movieCastService.findByMovieId(id);
        List<movie_crew> crew = movieCrewService.findByMovieId(id);
        List<person> allPersons = personService.findAll();

        model.addAttribute("movie", m);
        model.addAttribute("cast", cast);
        model.addAttribute("crew", crew);
        model.addAttribute("allPersons", allPersons);
        return "movie_detalles";
    }

    @PostMapping("/movies/{movieId}/cast/guardar")
    public String guardarCast(@PathVariable Integer movieId, @RequestParam Integer personId,
                             @RequestParam String characterName, @RequestParam Integer genderId) {
        movie_cast cast = new movie_cast();
        movie_cast_id id = new movie_cast_id();
        id.setMovie_id(movieId);
        id.setPerson_id(personId);
        id.setGender_id(genderId);
        cast.setId(id);
        cast.setMovie_id(movieService.findById(movieId));
        cast.setPerson_id(personService.findById(personId));
        cast.setGender_id(genderService.findById(genderId));
        cast.setCharacter_name(characterName);

        movieCastService.save(cast);
        return "redirect:/movies/detalles/" + movieId;
    }

    @PostMapping("/movies/{movieId}/crew/guardar")
    public String guardarCrew(@PathVariable Integer movieId, @RequestParam Integer personId,
                             @RequestParam String job, @RequestParam String department) {
        movie_crew crew = new movie_crew();
        movie_crew_id id = new movie_crew_id();
        id.setMovie_id(movieId);
        id.setPerson_id(personId);
        crew.setId(id);
        crew.setMovie(movieService.findById(movieId));
        crew.setPerson(personService.findById(personId));
        crew.setJob(job);
        crew.setDepartment(department);

        movieCrewService.save(crew);
        return "redirect:/movies/detalles/" + movieId;
    }

    @GetMapping("/movies/{movieId}/cast/eliminar/{personId}/{genderId}")
    public String eliminarCast(@PathVariable Integer movieId, @PathVariable Integer personId, @PathVariable Integer genderId) {
        movie_cast_id id = new movie_cast_id();
        id.setMovie_id(movieId);
        id.setPerson_id(personId);
        id.setGender_id(genderId);
        movieCastService.delete(id);
        return "redirect:/movies/detalles/" + movieId;
    }

    @GetMapping("/movies/{movieId}/crew/eliminar/{personId}")
    public String eliminarCrew(@PathVariable Integer movieId, @PathVariable Integer personId) {
        movie_crew_id id = new movie_crew_id();
        id.setMovie_id(movieId);
        id.setPerson_id(personId);
        movieCrewService.delete(id);
        return "redirect:/movies/detalles/" + movieId;
    }
}
