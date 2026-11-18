package com.tickettoride.tickettoride.Model.Player;

import com.tickettoride.tickettoride.Model.Color;

public class Traverser {
    private final Color color;
    private int currentPoint;

    public Traverser(Color color){
        this.color = color;
        this.currentPoint = 0;
    }

    public void setCurrentPoint(int currentPoint) {
        this.currentPoint = currentPoint;
    }
}
