package com.jrobinson.pokemondashboard.service;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.jrobinson.pokemondashboard.CustomPokemonDetailDeserializer;
import com.jrobinson.pokemondashboard.model.Pokemon;
import com.jrobinson.pokemondashboard.model.PokemonDetail;
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

    private PokemonDetail getPokemonDetail(String url) throws IOException {
        String pokemonDetialJson = getJsonFromUrl(new URL(url));
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("CustomPokemonDetailDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(PokemonDetail.class, new CustomPokemonDetailDeserializer());
        mapper.registerModule(module);
        return mapper.readValue(pokemonDetialJson, PokemonDetail.class);
    }

    private PokemonSummary getPokemonSummary() throws IOException {
        String pokemonSummaryJson = getJsonFromUrl(new URL("https://pokeapi.co/api/v2/pokemon?limit=1000"));
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(pokemonSummaryJson, PokemonSummary.class);
    }

    private String getJsonFromUrl(URL url) throws IOException {
        URLConnection conn = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        String json = reader.lines().collect(Collectors.joining("\n"));
        reader.close();
        return json;
    }
}
