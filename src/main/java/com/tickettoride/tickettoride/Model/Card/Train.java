package com.tickettoride.tickettoride.Model.Card;

import com.tickettoride.tickettoride.Model.Color;

public class Train {
    public static int NUMBER_CARDS = 110;
    private State state;
    private final Color color;

    public Train(State state, Color color){
        this.state = state;
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void modifyState(State newState){this.state = newState;}
    private Color getColor(){return  this.color;}
    public boolean isSameColor(Color otherColor){return this.color == otherColor || this.color == Color.RAINBOW;}
    public boolean isSameColor(Train otherTrain){return this.isSameColor(otherTrain.getColor());}
}
