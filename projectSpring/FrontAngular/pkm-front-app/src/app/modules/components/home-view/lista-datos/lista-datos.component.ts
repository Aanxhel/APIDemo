import { Component, OnInit } from '@angular/core';
import { Pokemon } from "../pokemon";
import { PokemonService } from "../pokemon.service";

@Component({
  selector: 'app-lista-datos',
  templateUrl: './lista-datos.component.html'
})
export class ListaDatosComponent implements OnInit{

  pokemons:Pokemon[];

  constructor( private pokemonService:PokemonService ){}

  ngOnInit(): void {
    this.obtenerPokemon();
    
  }

  private obtenerPokemon(){
    this.pokemonService.obtenerListaPokemon().subscribe(dato => {
      this.pokemons = dato;
    });
  }

}
