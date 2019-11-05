import React, {Component} from 'react';
import '../../css/Pokedex.css';
import pokeball from '../../resources/pokeball-icon.jpg';
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

    filterPokemon(event: any) {
        const filteredPokemonList = this.state.pokemonList.filter((pokemon) => {
            return pokemon.indexOf(event.target.value.toLowerCase()) !== -1
        });
        this.setState({filteredPokemonList: filteredPokemonList});
    }

    componentDidMount() {
        fetch("/pokemon")
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
                        {this.state.filteredPokemonList.sort(function(a, b) {
                            if(a.toLowerCase() < b.toLowerCase()) return -1;
                            if(a.toLowerCase() > b.toLowerCase()) return 1;
                            return 0;
                        }).map((pokemon) =>
                            <div>
                                <ListItem>
                                    <ListItemIcon className="pokeball-icon-container">
                                        <img className="pokeball-icon" alt="pokeball-icon" src={pokeball}/>
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
