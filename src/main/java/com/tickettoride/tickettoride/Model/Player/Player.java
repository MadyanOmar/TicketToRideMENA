package com.tickettoride.tickettoride.Model.Player;
import com.tickettoride.tickettoride.Model.Board.Board;
import com.tickettoride.tickettoride.Model.Card.Train;
import com.tickettoride.tickettoride.Model.Card.Trip;
import com.tickettoride.tickettoride.Model.Color;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String username;
    private int pointsSoFar;
    private List<com.tickettoride.tickettoride.Model.Board.Train> trainsInPossesion;
    private List<Trip> tripsInPossession;
    private List<Train> cardsInPossession;
    private Traverser traverser;

    public Player(String username, Color color) {
        this.username = username;
        pointsSoFar = 0;

        this.trainsInPossesion = new ArrayList<com.tickettoride.tickettoride.Model.Board.Train>(com.tickettoride.tickettoride.Model.Board.Train.NUMBER_TRAINS);
        for(int i = 0; i< com.tickettoride.tickettoride.Model.Board.Train.NUMBER_TRAINS; i++){trainsInPossesion.add(new com.tickettoride.tickettoride.Model.Board.Train(color));}

        this.cardsInPossession = new ArrayList<Train>(Train.NUMBER_CARDS);
        for(int i=0; i<4; i++){cardsInPossession.add(Board.getCard());}

        this.tripsInPossession = new ArrayList<Trip>(Trip.NUMBER_TRIPS);
        for(int i=0; i<3; i++){tripsInPossession.add(Board.getTrip());}

        this.traverser = new Traverser(trainsInPossesion.get(0).getColor());
    }

    public void addPoints(int points){
        this.pointsSoFar += points;
        traverser.setCurrentPoint(this.pointsSoFar);
    }

    public boolean hasEnoughTrains(int routeSize){
        int numCards =  cardsInPossession.size();
        return numCards >= routeSize;
    }
}
