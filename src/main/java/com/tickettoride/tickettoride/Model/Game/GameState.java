package com.tickettoride.tickettoride.Model.Game;

import com.tickettoride.tickettoride.Model.Board.Board;
import com.tickettoride.tickettoride.Model.Player.PlayerState;

import java.util.*;

public class GameState {
    private final List<PlayerState> players;
    private final Map<Integer, Integer> routeOwners;
    private int currentPlayerId;
    private GamePhase phase;

    public GameState(List<PlayerState> players){
        if(players.isEmpty()) throw new IllegalArgumentException("Need at least one player to start game");

        routeOwners = new HashMap<>();
        currentPlayerId = 0;
        phase = GamePhase.AWAITING_ACTION;

        int numPlayers = players.size();
        for(int i=0; i<numPlayers; i++){
            if(players.get(i).getId()!=i) throw new IllegalArgumentException("Player id must equal it's position in the list");
        }

        this.players = List.copyOf(players);
    }

    public List<PlayerState> getPlayers() { return players; }
    public PlayerState getPlayer(int id) { return players.get(id); }
    public int getCurrentPlayerId() { return currentPlayerId; }
    public GamePhase getPhase() { return phase; }
    public Map<Integer, Integer> getRouteOwners() { return Collections.unmodifiableMap(routeOwners); }

    public Optional<Integer> getOwnerOf(int routeId) { return Optional.ofNullable(routeOwners.get(routeId)); }
    public void setOwner(int routeId, int playerId){routeOwners.put(routeId, playerId);}
    public void advanceTurn() { currentPlayerId = (currentPlayerId + 1) % players.size(); }
}
