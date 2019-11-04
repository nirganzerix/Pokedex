package com.jrobinson.pokemondashboard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PokemonDetail {
    Integer id;
    String name;
    List<String> abilities;
    List<String> moves;
    String spriteUrl;
    List<String> types;
}
