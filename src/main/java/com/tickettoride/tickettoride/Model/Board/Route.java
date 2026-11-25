package com.tickettoride.tickettoride.Model.Board;
import com.tickettoride.tickettoride.Model.Color.RouteColor;
import com.tickettoride.tickettoride.Model.Player.PlayerState;


public class Route {
    public static final int MIN_LENGTH = 1;
    public static final int MAX_LENGTH = 6;
    private final int id;
    private final City startCity;
    private final City endCity;
    private int numTracks;
    private RouteColor trackColor;


    public Route(int id, City startCity, City endCity, int numTracks, RouteColor trackColor){
        if (startCity == null || endCity == null || trackColor == null) {
            throw new IllegalArgumentException("Route needs two cities and a color");
        }
        if(startCity == endCity){
            throw new IllegalArgumentException("Route must connect two different cities");
        }
        if(numTracks < MIN_LENGTH || numTracks > MAX_LENGTH){
            throw new IllegalArgumentException("Route length must be 1-6, got " + numTracks);
        }
        this.id = id;
        this.startCity = startCity;
        this.endCity = endCity;
        this.numTracks = numTracks;
        this.trackColor = trackColor;
    }

    public int getPoints(){
        return switch (numTracks) {
            case 1 -> 1;
            case 2 -> 2;
            case 3 -> 4;
            case 4 -> 7;
            case 5 -> 10;
            case 6 -> 15;
            default -> throw new IllegalStateException("Unreachable: length is validated");
        };
    }

    public boolean sameCities(Route other) {
        return (startCity.getCityName().equals(other.getStartCity().getCityName()) && endCity.getCityName().equals(other.getEndCity().getCityName())) ||
                startCity.getCityName().equals(other.endCity.getCityName())  && endCity.getCityName().equals(other.getStartCity().getCityName());
    }

    private City getStartCity(){return startCity;}
    private City getEndCity(){return endCity;}
    public int length(){return numTracks;}
    public Integer getId() {return id;}
    public RouteColor getTrackColor(){return trackColor;}
}
