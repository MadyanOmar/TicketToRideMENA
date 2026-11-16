package com.tickettoride.tickettoride.Model.Card;

import com.tickettoride.tickettoride.Model.Location;

public class Trip {
    private final Location origin;
    private final Location destination;
    private final int points;
    private State state;

    public Trip(Location start, Location end, int score){
        this.origin = start;
        this.destination = end;
        this.points = score;
        this.state = State.INCOMPLETE;
    }

    public void missionCompleted(){this.state = State.COMPLETE;}
    public int getPoints(){return this.state==State.INCOMPLETE ? -points : points;}
}
