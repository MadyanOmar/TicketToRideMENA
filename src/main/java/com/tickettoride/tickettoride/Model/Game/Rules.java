package com.tickettoride.tickettoride.Model.Game;
import com.tickettoride.tickettoride.Model.Board.Board;
import com.tickettoride.tickettoride.Model.Board.Route;
import com.tickettoride.tickettoride.Model.Color.CardColor;
import com.tickettoride.tickettoride.Model.Player.Action;
import com.tickettoride.tickettoride.Model.Player.PlayerState;

import java.util.Optional;

public final class Rules {
    private Rules() {}

    public static Optional<String> validateClaim(GameState state, Board board,
                                                 int playerId, Action.ClaimRoute claim) {
        if (state.getCurrentPlayerId() != playerId) return error("It is not your turn");
        if (state.getPhase() != GamePhase.AWAITING_ACTION) return error("You cannot claim a route right now");

        Optional<Route> found = board.find(claim.routeId());
        if (found.isEmpty()) return error("Unknown route");
        Route route = found.get();

        if (state.getOwnerOf(route.getId()).isPresent()) return error("That route is already claimed");

        for (Route other : board.getRoutes()) {
            boolean parallel = other.getId() != route.getId() && other.sameCities(route);
            boolean mine = state.getOwnerOf(other.getId()).map(owner -> owner == playerId).orElse(false);
            if (parallel && mine) return error("You already own the parallel route");
        }

        int rainbows = claim.rainbowCount();
        if (rainbows < 0 || rainbows > route.length()) return error("Invalid number of locomotives");
        int colored = route.length() - rainbows;
        PlayerState player = state.getPlayer(playerId);

        if (colored > 0) {
            CardColor color = claim.color();
            if (color == null || color == CardColor.RAINBOW) return error("Choose a color for the non-locomotive cards");
            if (!route.getTrackColor().accepts(color)) return error("Those cards do not match the route color");
            if (player.getCardCount(color) < colored) return error("You do not have enough " + color + " cards");
        }
        if (player.getCardCount(CardColor.RAINBOW) < rainbows) return error("You do not have enough locomotives");
        if (!player.canAfford(route)) return error("You do not have enough trains left");

        return Optional.empty();
    }

    private static Optional<String> error(String message) { return Optional.of(message); }
}