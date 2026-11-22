package com.tickettoride.tickettoride.Model.Player;
import com.tickettoride.tickettoride.Model.Board.Route;
import com.tickettoride.tickettoride.Model.Card.Train;
import com.tickettoride.tickettoride.Model.Card.Ticket;
import com.tickettoride.tickettoride.Model.Color;

import java.util.ArrayList;
import java.util.List;

public class PlayerState {
    public static final int STARTING_TRAINS = 45;

    private final int id;
    private final String username;
    private final Color color;
    private int trainsRemaining = STARTING_TRAINS;
    private int score = 0;
    private final List<Train> hand = new ArrayList<>();
    private final List<Ticket> tickets = new ArrayList<>();

    public PlayerState(int id, String username, Color color) {
        this.id = id;
        this.username = username;
        this.color = color;
    }

    public boolean canAfford(Route route) { return trainsRemaining >= route.length(); }

    public void spendTrains(int n) {
        if (n > trainsRemaining) throw new IllegalStateException("Not enough trains");
        trainsRemaining -= n;
    }

    public void addScore(int points) { score += points; }
}
