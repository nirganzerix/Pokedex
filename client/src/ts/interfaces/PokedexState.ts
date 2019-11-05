export interface PokedexState {
    error: boolean,
    errorMsg: string,
    isLoaded: boolean,
    pokemonList: string[],
    filteredPokemonList: string[]
}