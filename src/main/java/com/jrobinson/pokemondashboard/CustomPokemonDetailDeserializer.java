package com.jrobinson.pokemondashboard;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.jrobinson.pokemondashboard.model.PokemonDetail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomPokemonDetailDeserializer extends StdDeserializer<PokemonDetail> {

    public CustomPokemonDetailDeserializer(){
        this(null);
    }

    public CustomPokemonDetailDeserializer(Class<?> vc){
        super(vc);
    }

    @Override
    public PokemonDetail deserialize(JsonParser parser, DeserializationContext deserializer){
        try {
            ObjectCodec codec = parser.getCodec();
            JsonNode node = codec.readTree(parser);


            Integer id = node.get("id").asInt();
            String name = node.get("name").asText();
            String spriteUrl = node.get("sprites").get("front_default").asText();
            List<String> movesList = new ArrayList<>();
            List<String> abilitiesList = new ArrayList<>();
            List<String> typesList = new ArrayList<>();

/*            Iterator<JsonNode> abilities = node.get("abilities").elements();
            while (abilities.hasNext()) {
                abilitiesList.add(abilities.next().get("ability").get("name").asText());
            }
            Iterator<JsonNode> moves = node.get("moves").elements();
            while (moves.hasNext()) {
                movesList.add(moves.next().get("move").get("name").asText());
            }*/
            Iterator<JsonNode> types = node.get("types").elements();
            while (types.hasNext()) {
                movesList.add(types.next().get("type").get("name").asText());
            }

            return PokemonDetail.builder()
                    //.abilities(abilitiesList)
                    //.moves(movesList)
                    .types(typesList)
                    .id(id)
                    .name(name)
                    .spriteUrl(spriteUrl)
                    .build();
        } catch (IOException exception){
            exception.printStackTrace();
            return null;
        }
    }
}
