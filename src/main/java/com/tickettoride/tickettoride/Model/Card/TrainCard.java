package com.tickettoride.tickettoride.Model.Card;

import com.tickettoride.tickettoride.Model.Color.CardColor;
import com.tickettoride.tickettoride.Model.Color.RouteColor;

public class TrainCard {
    public static int NUMBER_CARDS = 110;
    private State state;
    private final CardColor color;

    public TrainCard(State state, CardColor color){
        this.state = state;
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void modifyState(State newState){this.state = newState;}
    public CardColor getColor(){return this.color;}
    public boolean isSameColor(RouteColor routeColor){return this.color == CardColor.RAINBOW || this.color.ordinal() == routeColor.ordinal();}
    public boolean isSameColor(TrainCard otherCard){return this.color == otherCard.getColor();}
}
