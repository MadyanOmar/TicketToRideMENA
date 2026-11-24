package com.tickettoride.tickettoride.Model.Player;

import com.tickettoride.tickettoride.Model.Color.CardColor;

public sealed interface Action {
    record ClaimRoute(int routeId, CardColor color, int rainbowCount) implements Action {}
}
