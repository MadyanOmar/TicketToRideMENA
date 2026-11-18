package com.tickettoride.tickettoride.Model.Board;
import com.tickettoride.tickettoride.Model.Card.Train;
import com.tickettoride.tickettoride.Model.Card.Trip;
import java.util.List;
import java.util.Random;

public class Board {
    private static List<Trip> tripsDeck;
    private static List<Train> trainDeck;

    public static Train getCard(){
        return trainDeck.get(new Random().nextInt(trainDeck.size()));
    }

    public static Trip getTrip(){
        return tripsDeck.get(new Random().nextInt(tripsDeck.size()));
    }
}
