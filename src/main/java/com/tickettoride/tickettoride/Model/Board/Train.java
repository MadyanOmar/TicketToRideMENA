package com.tickettoride.tickettoride.Model.Board;

import com.tickettoride.tickettoride.Model.Color;

public class Train {
    public static int NUMBER_TRAINS = 45;
    private final Color color;

    public Train(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}
