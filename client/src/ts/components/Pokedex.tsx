import React, {Component} from 'react';
import '../../css/Pokedex.css';
import Pokeball from '../../resources/pokeball-icon.jpg';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import Divider from '@material-ui/core/Divider';
import TextField from '@material-ui/core/TextField';
import {PokedexState} from "../interfaces/PokedexState";

class Pokedex extends Component<{}, PokedexState> {
    constructor(props: any) {
        super(props);
        this.state = {
            error: false,
            errorMsg: '',
            isLoaded: false,
            pokemonList: [],
            filteredPokemonList: []
        };
    }

    static capitalize(str: string){
        return str.charAt(0).toUpperCase() + str.slice(1);
    }


    /**
     * Filters the Pokemon list based on user search input
     *
     * @param event - object containing text value attached to Material UI Field
     */
    filterPokemon(event: any) {
        const filteredPokemonList = this.state.pokemonList.filter((pokemon) => {
            return pokemon.indexOf(event.target.value.toLowerCase()) !== -1
        });
        this.setState({filteredPokemonList: filteredPokemonList});
    }


    /**
     * @return the filtered Pokemon list sorted alphabetically
     */
    getAlphabeticallySortedPokemonList() {
        return this.state.filteredPokemonList.sort(function(pokemon1, pokemon2) {
            if(pokemon1.toLowerCase() < pokemon2.toLowerCase()) return -1;
            if(pokemon1.toLowerCase() > pokemon2.toLowerCase()) return 1;
            return 0;
        });
    }


    /**
     * Hit Spring Boot endpoint to get list of Pokemon names
     */
    componentDidMount() {
        fetch("/pokemon")                       //proxy is localhost:8080 via package.json
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        isLoaded: true,
                        pokemonList: result,
                        filteredPokemonList: result,
                        error: false
                    });
                },
                (error) => {
                    this.setState({
                        isLoaded: true,
                        error: true,
                        errorMsg: error.message
                    });
                }
            )
      }

      render() {
        return (
            <div className="Pokedex">
              <h1>Pokemon</h1>
                <div className="searchContainer">
                    <TextField
                        label={"Search by Name"}
                        type={"search"}
                        margin={"normal"}
                        fullWidth={true}
                        onChange={this.filterPokemon.bind(this)}
                    />
                </div>
                <div className="pokemon-list">
                    <List>
                        {this.getAlphabeticallySortedPokemonList().map((pokemon) =>
                            <div>
                                <ListItem>
                                    <ListItemIcon className="pokeball-icon-container">
                                        <img className="pokeball-icon" alt="pokeball-icon" src={Pokeball}/>
                                    </ListItemIcon>
                                    <ListItemText className="pokemon-name" primary={Pokedex.capitalize(pokemon)} disableTypography={true}/>
                                </ListItem>
                                <Divider />
                            </div>
                        )}
                    </List>
                </div>
            </div>
        );
      }
}

export default Pokedex;
