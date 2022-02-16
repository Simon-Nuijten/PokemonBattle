package com.company;

import java.util.ArrayList;

public class Pokemon {
    private String name;
    private int level;
    private ArrayList<Move> moves = new ArrayList<>();
    private int health;

    public Pokemon(String pokemonName, int pokemonLevel) {
        this.name = pokemonName;
        this.level = pokemonLevel;

        this.health = level * 2;
    }

    public void addMove (Move move){
        this.moves.add(move);
    }

    public String growl(){
        return name + " growl...";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Move> moves) {
        this.moves = moves;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if(health < 0){
            this.health = 0;
        } else{
            this.health = health;
        }

    }

    public void setLevel(int level) {
        this.level = level;
    }
}
