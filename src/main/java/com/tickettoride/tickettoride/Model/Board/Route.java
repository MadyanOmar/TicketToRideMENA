package com.tickettoride.tickettoride.Model.Board;

import com.tickettoride.tickettoride.Model.Card.Train;
import com.tickettoride.tickettoride.Model.Color;
import com.tickettoride.tickettoride.Model.Player.PlayerState;

import java.util.List;

public class Route {
    private final City startCity;
    private final City endCity;
    private boolean occupied;
    private int numTracks;
    private Color trackColor;
    private PlayerState owner;


    public Route(City startCity, City endCity, int numTracks, Color trackColor){
        this.startCity = startCity;
        this.endCity = endCity;
        this.numTracks = numTracks;
        this.trackColor = trackColor;
        this.occupied = false;
        owner = null;
    }

    private int countPoints(){
        return switch (numTracks) {
            case 1 -> 1;
            case 2 -> 2;
            case 3 -> 4;
            case 4 -> 7;
            case 5 -> 10;
            case 6 -> 15;
            default -> 0;
        };
    }

    public boolean isClaimed(){return occupied;}
    public boolean isSameColor(Train train){return trackColor == Color.GRAY || train.isSameColor(trackColor);}

    public boolean validCards(List<Train> submittedCards){
        int numCards = submittedCards.size();
        if(numCards != numTracks) return false;
        Train firstCard = submittedCards.get(0);
        for(int i=1; i<numCards; i++){
            Train currentCard = submittedCards.get(i);
            if(!firstCard.isSameColor(currentCard)) return false;
            if(!isSameColor(currentCard)) return false;
        }
        return true;
    }

    public int length(){return numTracks;}

    public boolean claim(PlayerState player, List<Train> submittedCards){
        if(isClaimed() || !validCards(submittedCards) || !player.canAfford(this)) return false;
        this.occupied = true;
        owner = player;
        owner.spendTrains(numTracks);
        owner.addScore(countPoints());
        return true;
    }
}
