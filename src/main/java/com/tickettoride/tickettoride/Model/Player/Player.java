package com.tickettoride.tickettoride.Model.Player;
import com.tickettoride.tickettoride.Model.Card.Card;
import com.tickettoride.tickettoride.Model.Card.Train;
import com.tickettoride.tickettoride.Model.Card.Trip;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    private String username;
    private int pointsSoFar;
    private List<Train> trainsInPossesion;
    private List<Trip> tripsInPossesion;
    private List<Card> cardsInPossession;

    public Player() {
        this(getRandomUsername());
    }

    public Player(String username) {
        this.username = username;
        pointsSoFar = 0;
        this.trainsInPossesion = new ArrayList<Train>(Train.NUMBER_TRAINS);
        this.cardsInPossession = new ArrayList<Card>(Card.NUMBER_CARDS);
        this.tripsInPossesion = new ArrayList<Trip>(Trip.NUMBER_TRIPS);
    }

    private static String getRandomUsername() {
        String[] randomUsernames = {"IbnKhaldun", "AlBiruni", "AlMasudi", "AlFarabi"};
        return randomUsernames[new Random().nextInt(randomUsernames.length)];
    }

    public void addPoints(int points){
        this.pointsSoFar += points;
    }

    public boolean hasEnoughTrains(int routeSize){
        int numCards =  cardsInPossession.size();
        return numCards >= routeSize;
    }
}
