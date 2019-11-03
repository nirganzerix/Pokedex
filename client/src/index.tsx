import React from 'react';
import {render} from 'react-dom';
import './css/index.css';
import Pokedex from './ts/components/Pokedex';

render(
    <Pokedex/>,
    document.getElementById("root")
);
