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
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public String listMovies(@RequestParam(required = false) String title, @RequestParam(required = false) String actor, @RequestParam(required = false) String genre, Model model,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "40") int size) {

        org.springframework.data.domain.Page<movie> moviesPage = movieService.searchMoviesPaginated(title, actor, genre, page, size);
        model.addAttribute("moviesPage", moviesPage);
        model.addAttribute("movies", moviesPage.getContent());
        model.addAttribute("title", title);
        model.addAttribute("actor", actor);
        model.addAttribute("genre", genre);
        model.addAttribute("page", page);
        model.addAttribute("size", size);

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
    public String detallesPelicula(@PathVariable Integer id, Model model,
                                   @RequestParam(defaultValue = "0") int castPage,
                                   @RequestParam(defaultValue = "40") int castSize,
                                   @RequestParam(defaultValue = "0") int crewPage,
                                   @RequestParam(defaultValue = "40") int crewSize) {
        movie m = movieService.findById(id);
        org.springframework.data.domain.Page<movie_cast> castPageObj = movieCastService.findByMovieIdPaginated(id, castPage, castSize);
        org.springframework.data.domain.Page<movie_crew> crewPageObj = movieCrewService.findByMovieIdPaginated(id, crewPage, crewSize);

        model.addAttribute("movie", m);
        model.addAttribute("castPage", castPageObj);
        model.addAttribute("cast", castPageObj.getContent());
        model.addAttribute("crewPage", crewPageObj);
        model.addAttribute("crew", crewPageObj.getContent());
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
        cast.setMovie(movieService.findById(movieId));
        cast.setPerson(personService.findById(personId));
        cast.setGender(genderService.findById(genderId));
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

    @GetMapping("/person/autocomplete")
    public ResponseEntity<List<PersonDTO>> autocompletePersona(@RequestParam String query) {
        List<person> personas = personService.findByNameContaining(query);
        List<PersonDTO> result = personas.stream()
                .map(p -> new PersonDTO(p.getPerson_id(), p.getPerson_name()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    public static class PersonDTO {
        public Integer id;
        public String name;

        public PersonDTO(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() { return id; }
        public String getName() { return name; }
    }
}
