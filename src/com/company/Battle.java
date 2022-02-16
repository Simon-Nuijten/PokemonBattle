package com.company;

public class Battle {
    private Pokemon pokemonOwner;
    private Pokemon pokemonTrainer;

    public Battle(Pokemon owner, Pokemon trainer){
        this.pokemonOwner = owner;
        this.pokemonTrainer = trainer;
    }

    public Pokemon getPokemonOwner() {
        return pokemonOwner;
    }

    public Pokemon getPokemonTrainer() {
        return pokemonTrainer;
    }
}
