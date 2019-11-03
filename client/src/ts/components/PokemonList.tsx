import React, {Component} from 'react';
import PokemonListItem from "./PokemonListItem";

interface Pokemon {
    name: string,
    url: string
}

interface IMyComponentProps {

}

interface IMyComponentState {
    error: boolean,
    errorMsg: string,
    isLoaded: boolean,
    pokemonList: Pokemon[]
}

class PokemonList extends Component<IMyComponentProps, IMyComponentState> {
    constructor(props: any) {
        super(props);
        this.state = {
            error: false,
            errorMsg: '',
            isLoaded: false,
            pokemonList: []
        };
    }

    componentDidMount() {
        fetch("/pokemon")
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        isLoaded: true,
                        pokemonList: result.results,
                        error: false
                    });
                },
                // Note: it's important to handle errors here
                // instead of a catch() block so that we don't swallow
                // exceptions from actual bugs in components.
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
        if(this.state.error) {
            return <div>Error: {this.state.errorMsg}</div>;
        }
        else {
            return (
                <div>
                    {this.state.pokemonList.map(pokemon => (
                        <div>{pokemon.name}</div>//<PokemonListItem />
                ))}
                </div>
            );
        }
    }
}

export default PokemonList;
