package com.jrobinson.pokemondashboard.controller;

import com.jrobinson.pokemondashboard.service.PokemonListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
public class PokemonRestController {

    @Autowired
    PokemonListService pokemonListService;

    @RequestMapping(value = "/pokemon", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Cacheable
    public List<String> getPokemonNameList(){
        return pokemonListService.getPokemonNameList();
    }

//    public PokemonDetail getPokemonDetailByName(String name) {
//        return pokemonListService.getPokemonDetailByName(name);
//    }
}
