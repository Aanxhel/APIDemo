import { Component, OnInit } from '@angular/core';
import { Pokemon } from '../pokemon';
import { PokemonService } from '../pokemon.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-insertar-dato',
  templateUrl: './insertar-dato.component.html'
})
export class InsertarDatoComponent implements OnInit {

  pokemon: Pokemon = new Pokemon();
  constructor(private pokemonService: PokemonService, private router: Router) { }

  ngOnInit(): void {
  }

  insertarPokemon() {
    this.pokemonService.registrarPokemon(this.pokemon).subscribe(
      {
        //se agrega next debido a la libreria rxjs
        next: (dato) => {
           console.log(dato);
        },
        error: (error) => {
          console.log(error);
        }
      } );
  }

  onSubmit() {
    console.log(this.pokemon);
    this.insertarPokemon();
  }
}
