package com.tickettoride.tickettoride.Model.Player;
import com.tickettoride.tickettoride.Model.Board.Route;
import com.tickettoride.tickettoride.Model.Card.Ticket;
import com.tickettoride.tickettoride.Model.Color.CardColor;
import com.tickettoride.tickettoride.Model.Color.PlayerColor;

import java.util.*;

public class PlayerState {
    public static final int STARTING_TRAINS = 45;

    private final int id;
    private final String username;
    private final PlayerColor color;
    private int trainsRemaining = STARTING_TRAINS;
    private int score = 0;
    private final Map<CardColor, Integer> hand = new EnumMap<>(CardColor.class);
    private final List<Ticket> tickets = new ArrayList<>();

    public PlayerState(int id, String username, PlayerColor color) {
        this.id = id;
        this.username = username;
        this.color = color;
        for (CardColor c : CardColor.values()) hand.put(c, 0);
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public PlayerColor getColor() { return color; }
    public int getTrainsRemaining() { return trainsRemaining; }
    public int getScore() { return score; }

    public List<Ticket> getTickets() { return Collections.unmodifiableList(tickets); }
    public Map<CardColor, Integer> getHand() { return Collections.unmodifiableMap(hand); }

    public int getCardCount(CardColor c) { return hand.get(c); }
    public int getTotalCards() {
        int total = 0;
        for (int n : hand.values()) total += n;
        return total;
    }
    public void addCard(CardColor c) { hand.merge(c, 1, Integer::sum); }
    public void removeCards(CardColor c, int n) {
        if (n < 0) throw new IllegalArgumentException("Cannot remove a negative number of cards");
        if (hand.get(c) < n) throw new IllegalStateException("Not enough " + c + " cards");
        hand.put(c, hand.get(c) - n);
    }
    public void addTickets(Collection<Ticket> newTickets) { tickets.addAll(newTickets); }

    public boolean canAfford(Route route) { return trainsRemaining >= route.length(); }

    public void spendTrains(int n) {
        if (n > trainsRemaining) throw new IllegalStateException("Not enough trains");
        trainsRemaining -= n;
    }
    public void addScore(int points) { score += points; }
}
