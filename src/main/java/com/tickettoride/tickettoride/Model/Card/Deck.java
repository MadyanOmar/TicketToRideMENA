package com.tickettoride.tickettoride.Model.Card;

import java.util.*;
import java.util.function.Predicate;

public class Deck <T>{
    private final ArrayDeque<T> drawPile;
    private final List<T> discardPile;
    private final Random rng;

    private Deck(List<T> cards, Random rng, boolean shuffle){
        this.rng = rng;
        List<T> copy = new ArrayList<>(cards);
        if(shuffle) Collections.shuffle(copy, rng);
        discardPile = new ArrayList<>();
        drawPile = new ArrayDeque<T>();
        drawPile.addAll(copy);
    }

    public static <T> Deck<T> shuffled(List<T> cards, Random rng){
        return new Deck<>(cards, rng, true);
    }

    public static <T> Deck<T> inOrder(List<T> cards, Random rng){
        return new Deck<>(cards, rng, false);
    }

    private void reshuffleDiscardPile() {
        Collections.shuffle(discardPile, rng);
        drawPile.addAll(discardPile);
        discardPile.clear();
    }

    public Optional<T> draw(){
        if(drawPile.isEmpty()) reshuffleDiscardPile();
        return Optional.ofNullable(drawPile.pollFirst());
    }

    public void discard(T card){ discardPile.add(card); }
    public void putOnBottom(T card){ drawPile.addLast(card); }

    public int drawPileSize() {return drawPile.size();}
    public int discardPileSize() {return discardPile.size();}

    public long countMatching(Predicate<T> test) {
        return drawPile.stream().filter(test).count() + discardPile.stream().filter(test).count();
    }
}
