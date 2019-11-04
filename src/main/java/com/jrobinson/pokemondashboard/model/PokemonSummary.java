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
public class PokemonSummary {
    Integer count;
    String next;
    String previous;
    List<Pokemon> results;
}
