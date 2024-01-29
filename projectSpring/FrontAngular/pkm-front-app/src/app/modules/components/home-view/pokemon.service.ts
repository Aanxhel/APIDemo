import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pokemon } from './pokemon';

@Injectable({
  providedIn: 'root'
})
export class PokemonService {

  //lista de datos
  private baseUrl = "http://localhost:8080/";

  constructor( private httpClient: HttpClient ) { }

  obtenerListaPokemon():Observable<Pokemon[]>{
      return this.httpClient.get<Pokemon[]>("http://localhost:8080/listapkm");
  }
  
  //metodo para crear  datos
  registrarPokemon(pokemon:Pokemon): Observable<Object>{
    return this.httpClient.post("http://localhost:8080/inserdata",pokemon);
  }
}
