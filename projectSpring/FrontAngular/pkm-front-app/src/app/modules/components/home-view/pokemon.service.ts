import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pokemon } from './pokemon';

@Injectable({
  providedIn: 'root'
})
export class PokemonService {

  //base URL
  private baseUrl = "http://localhost:8080";

  constructor( private httpClient: HttpClient ) { }

  //lista select
  obtenerListaPokemon():Observable<Pokemon[]>{
      return this.httpClient.get<Pokemon[]>(`${this.baseUrl}/listapkm`);
  }

  //buscar get
  obtenerIdPokemon(id: number): Observable<Pokemon>{
    return this.httpClient.get<Pokemon>(`${this.baseUrl}/listapkm/{id}`);
  }
  
  //metodo insreartar
  registrarPokemon(pokemon: Pokemon): Observable<Pokemon>{
    return this.httpClient.post<Pokemon>(`${this.baseUrl}/inserdata`, pokemon);
  }

  //editar update
  actualizarIdPokemon(id: number, pokemon:Pokemon): Observable<Pokemon>{
    return this.httpClient.put<Pokemon>(`${this.baseUrl}/putpkm/{id}`, pokemon);
  }

  //eliminar delete
  eliminarPokemon(id:number): Observable<Object>{
    return this.httpClient.delete(`${this.baseUrl}/delete/{id}`);
  }


}
