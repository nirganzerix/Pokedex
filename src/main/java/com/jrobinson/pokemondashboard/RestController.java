package com.jrobinson.pokemondashboard;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Controller
public class RestController {

    @RequestMapping(value = "/pokemon", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getPokemon(){
        String json = "";
        try {
            URL url = new URL("https://pokeapi.co/api/v2/pokemon?limit=1000");
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            json = reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException exception){
            exception.printStackTrace();
        }

        return json;
    }
}
