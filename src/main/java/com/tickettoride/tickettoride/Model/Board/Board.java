package com.tickettoride.tickettoride.Model.Board;
import com.tickettoride.tickettoride.Model.Card.TrainCard;
import com.tickettoride.tickettoride.Model.Card.Ticket;

import java.util.*;

public class Board {
    private final List<Route> routes;
    private final Map<Integer, Route> byId;

    public Board(List<Route> routes) {
        this.routes = List.copyOf(routes);
        byId = new HashMap<>();
        for (Route r : this.routes) {
            if (byId.put(r.getId(), r) != null) {
                throw new IllegalArgumentException("Duplicate route id: " + r.getId());
            }
        }
    }

    public List<Route> getRoutes() { return routes; }
    public Optional<Route> find(int routeId) { return Optional.ofNullable(byId.get(routeId)); }
}
