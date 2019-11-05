package com.jrobinson.pokemondashboard.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrobinson.pokemondashboard.model.Pokemon;
import com.jrobinson.pokemondashboard.model.PokemonSummary;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonListService {

    /**
     * Gets a payload from PokeAPI massages the data
     *
     * @return a list of pokemon names
     */
    public List<String> getPokemonNameList(){
        List<String> pokemonNameList = new ArrayList<>();

        try {
            List<Pokemon> pokemonList = getPokemonSummary().getResults();
            pokemonList.forEach(pokemon -> pokemonNameList.add(pokemon.getName()));
        } catch (IOException exception){
            exception.printStackTrace();
        }

        return pokemonNameList;
    }

    /**
     * Hits the API URL to get a payload with Pokemon information
     *
     * The API only returns 20 results by default. Use the limit parameter to obtain the full list.
     *
     * @return the base payload from PokeAPI
     * @throws IOException
     */
    private PokemonSummary getPokemonSummary() throws IOException {
        String pokemonSummaryJson = getJsonFromUrl(new URL("https://pokeapi.co/api/v2/pokemon?limit=1000"));
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(pokemonSummaryJson, PokemonSummary.class);
    }


    /**
     * Generic method to obtain a JSON payload from a URL connection
     *
     * @param url - the url connection to open
     * @return the json payload to return
     * @throws IOException
     */
    private String getJsonFromUrl(URL url) throws IOException {
        URLConnection conn = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        String json = reader.lines().collect(Collectors.joining("\n"));
        reader.close();
        return json;
    }
}
