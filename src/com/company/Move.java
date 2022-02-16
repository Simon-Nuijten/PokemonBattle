package com.company;

public class Move {
    private String name;
    private int power;
    private String type;
    private String moveUrl;

    public Move(String n, int p, String t) {
        name = n;
        power = p;
        type = t;

    }
    public void setUrl(String url){
        this.moveUrl = url;
    }

    public String getMoveUrl() {
        return moveUrl;
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public String getType() {
        return type;
    }
}
