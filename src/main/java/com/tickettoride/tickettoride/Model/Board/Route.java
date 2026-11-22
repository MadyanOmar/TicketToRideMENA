package com.tickettoride.tickettoride.Model.Board;

import com.tickettoride.tickettoride.Model.Card.TrainCard;
import com.tickettoride.tickettoride.Model.Color.RouteColor;
import com.tickettoride.tickettoride.Model.Player.PlayerState;

import java.util.List;

public class Route {
    private final City startCity;
    private final City endCity;
    private boolean occupied;
    private int numTracks;
    private RouteColor trackColor;
    private PlayerState owner;


    public Route(City startCity, City endCity, int numTracks, RouteColor trackColor){
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
    public boolean isSameColor(TrainCard trainCard){return trackColor == RouteColor.GRAY || trainCard.isSameColor(trackColor);}

    public boolean validCards(List<TrainCard> submittedCards){
        int numCards = submittedCards.size();
        if(numCards != numTracks) return false;
        TrainCard firstCard = submittedCards.get(0);
        for(int i=1; i<numCards; i++){
            TrainCard currentCard = submittedCards.get(i);
            if(!firstCard.isSameColor(currentCard)) return false;
            if(!isSameColor(currentCard)) return false;
        }
        return true;
    }

    public int length(){return numTracks;}

    public boolean claim(PlayerState player, List<TrainCard> submittedCards){
        if(isClaimed() || !validCards(submittedCards) || !player.canAfford(this)) return false;
        this.occupied = true;
        owner = player;
        owner.spendTrains(numTracks);
        owner.addScore(countPoints());
        return true;
    }
}
