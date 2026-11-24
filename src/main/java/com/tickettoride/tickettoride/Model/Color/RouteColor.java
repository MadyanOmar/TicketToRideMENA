package com.tickettoride.tickettoride.Model.Color;

public enum RouteColor {
    PURPLE, BLUE, ORANGE, WHITE, GREEN, YELLOW, BLACK, RED, GRAY;

    public boolean accepts(CardColor card) {
        return this == GRAY || card.name().equals(name());
    }
}
