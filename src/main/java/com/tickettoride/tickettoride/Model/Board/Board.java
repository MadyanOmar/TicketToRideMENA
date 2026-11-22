package com.tickettoride.tickettoride.Model.Board;
import com.tickettoride.tickettoride.Model.Card.TrainCard;
import com.tickettoride.tickettoride.Model.Card.Ticket;
import java.util.List;
import java.util.Random;

public class Board {
    private static List<Ticket> tripsDeck;
    private static List<TrainCard> trainCardDeck;

    public static TrainCard getCard(){
        return trainCardDeck.get(new Random().nextInt(trainCardDeck.size()));
    }

    public static Ticket getTrip(){
        return tripsDeck.get(new Random().nextInt(tripsDeck.size()));
    }
}
