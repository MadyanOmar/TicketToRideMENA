package com.tickettoride.tickettoride.Model.Game;

import com.tickettoride.tickettoride.Model.Card.Deck;
import com.tickettoride.tickettoride.Model.Card.Ticket;
import com.tickettoride.tickettoride.Model.Color.CardColor;
import com.tickettoride.tickettoride.Model.Player.PlayerState;

import java.util.*;

public class Dealer {
    public static final int COLORED_CARDS_PER_COLOR = 12;
    public static final int RAINBOW_CARDS = 14;
    public static final int FACE_UP_SLOTS = 5;
    public static final int STARTING_HAND = 4;
    public static final int MAX_FACE_UP_RAINBOWS = 2;
    private static final int MAX_RESETS = 100;

    private final Deck<CardColor> trainDeck;
    private final Deck<Ticket> ticketDeck;
    private final CardColor[] faceUp = new CardColor[FACE_UP_SLOTS];

    public Dealer(List<Ticket> tickets, Random rng){
        this(Deck.shuffled(fullTrainCardSet(), rng), Deck.shuffled(tickets, rng));
    }

    public Dealer(Deck<CardColor> trainDeck, Deck<Ticket> ticketDeck){
        this.trainDeck = trainDeck;
        this.ticketDeck = ticketDeck;
    }

    public List<CardColor> getFaceUp() {
        return Collections.unmodifiableList(new ArrayList<>(Arrays.asList(faceUp)));
    }
    public int getTrainDeckSize() { return trainDeck.drawPileSize(); }
    public int getDiscardPileSize() { return trainDeck.discardPileSize(); }
    public int getTicketDeckSize() { return ticketDeck.drawPileSize(); }


    public static List<CardColor> fullTrainCardSet(){
        List<CardColor> cards = new ArrayList<>();
        CardColor[] cardColors = CardColor.values();
        for(CardColor c : cardColors){
            int copies = (c == CardColor.RAINBOW) ? RAINBOW_CARDS : COLORED_CARDS_PER_COLOR;
            for(int i=0; i<copies; i++) cards.add(c);
        }
        return cards;
    }

    public Optional<CardColor> drawFromDeck() { return trainDeck.draw(); }
    public void dealStartingHand(PlayerState player){
        for(int i=0; i<STARTING_HAND; i++){
            drawFromDeck().ifPresent(player::addCard);
        }
    }

    private long countFaceUpRainbows() {
        return Arrays.stream(faceUp).filter(c -> c == CardColor.RAINBOW).count();
    }

    private boolean enoughNonRainbowsExist() {
        long nonRainbow = trainDeck.countMatching(c -> c != CardColor.RAINBOW)
                + Arrays.stream(faceUp).filter(c -> c != null && c != CardColor.RAINBOW).count();
        return nonRainbow >= FACE_UP_SLOTS - MAX_FACE_UP_RAINBOWS;
    }

    private void resetFaceUpWhileTooManyRainbows() {
        for (int attempt = 0; attempt < MAX_RESETS; attempt++) {
            if (countFaceUpRainbows() <= MAX_FACE_UP_RAINBOWS || !enoughNonRainbowsExist()) return;
        }
        for (int i = 0; i < FACE_UP_SLOTS; i++) {
            if (faceUp[i] != null) trainDeck.discard(faceUp[i]);
            faceUp[i] = null;
        }
        for (int i = 0; i < FACE_UP_SLOTS; i++) {
            faceUp[i] = trainDeck.draw().orElse(null);
        }
    }

    public Optional<CardColor> takeFaceUp(int slot){
        if(slot < 0 || slot >= FACE_UP_SLOTS) throw new IllegalArgumentException("Invalid face-up slot: " + slot);
        CardColor taken = faceUp[slot];
        if(taken == null) return Optional.empty();
        faceUp[slot] = trainDeck.draw().orElse(null);
        resetFaceUpWhileTooManyRainbows();
        return Optional.of(taken);
    }

    public void fillFaceUp(){
        for(int i=0; i<FACE_UP_SLOTS; i++){
            if(faceUp[i] == null) faceUp[i] = trainDeck.draw().orElse(null);
        }
        resetFaceUpWhileTooManyRainbows();
    }

    public void discard(CardColor color, int count){
        for(int i=0; i<count; i++) trainDeck.discard(color);
    }

    public List<Ticket> dealTickets(int count){
        List<Ticket> dealt = new ArrayList<>();
        for(int i=0; i<count; i++) ticketDeck.draw().ifPresent(dealt::add);
        return dealt;
    }

    public void returnTickets(List<Ticket> tickets){
        for (Ticket t : tickets) ticketDeck.putOnBottom(t);
    }
}
