import React, {Component} from 'react';
import pokeball from '../../resources/pokeball-icon.jpg'
import {PokemonListItemProps} from "../interfaces/PokemonListItemProps";

class PokemonListItem extends Component<PokemonListItemProps, {}> {
  render(){
    return (
        <div>
          <span>{this.props.name.slice(0,1).toUpperCase() + this.props.name.slice(1, this.props.name.length)}</span>
          <img className="pokeball-icon" src={pokeball} alt={"pokeball"}></img>
        </div>
    );
  }
}

export default PokemonListItem;
