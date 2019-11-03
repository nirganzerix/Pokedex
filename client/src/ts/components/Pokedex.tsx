import React, {Component} from 'react';
import '../../css/Pokedex.css';
import PokemonList from "./PokemonList";

class Pokedex extends Component {
  render(){
    return (
        <div className="Pokedex">
          <h2>Pokedex</h2>
            <PokemonList />
        </div>
    );
  }
}

export default Pokedex;
