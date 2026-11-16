package com.tickettoride.tickettoride.Model.Board;

import com.tickettoride.tickettoride.Model.Location;

public class City {
    private final Location name;
    private final double x;
    private final double y;

    public City(Location name, double x, double y){
        this.name = name;
        this.x = x;
        this.y = y;
    }
}
