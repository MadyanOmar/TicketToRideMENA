package com.tickettoride.tickettoride.Model.Game;


import com.tickettoride.tickettoride.Model.Board.Board;
import com.tickettoride.tickettoride.Model.Board.Route;
import com.tickettoride.tickettoride.Model.Color.CardColor;
import com.tickettoride.tickettoride.Model.Player.Action;
import com.tickettoride.tickettoride.Model.Player.PlayerState;
import java.util.Optional;

public class GameEngine {
    private final Board board;
    private final GameState state;
    private final Dealer dealer;

    public GameEngine(Board board, GameState state, Dealer dealer) {
        this.board = board;
        this.state = state;
        this.dealer = dealer;
    }

    public void start() {
        dealer.fillFaceUp();
        for (PlayerState player : state.getPlayers()) dealer.dealStartingHand(player);
    }

    public Result apply(int playerId, Action action) {
        return switch (action) {
            case Action.ClaimRoute claim -> claimRoute(playerId, claim);
        };
    }

    private Result claimRoute(int playerId, Action.ClaimRoute claim) {
        Optional<String> problem = Rules.validateClaim(state, board, playerId, claim);
        if (problem.isPresent()) return Result.fail(problem.get());

        Route route = board.find(claim.routeId()).orElseThrow();
        PlayerState player = state.getPlayer(playerId);
        int colored = route.length() - claim.rainbowCount();

        if (colored > 0) {
            player.removeCards(claim.color(), colored);
            dealer.discard(claim.color(), colored);
        }
        if (claim.rainbowCount() > 0) {
            player.removeCards(CardColor.RAINBOW, claim.rainbowCount());
            dealer.discard(CardColor.RAINBOW, claim.rainbowCount());
        }
        player.spendTrains(route.length());
        player.addScore(route.getPoints());
        state.setOwner(route.getId(), playerId);
        state.advanceTurn();
        return Result.OK;
    }
}
