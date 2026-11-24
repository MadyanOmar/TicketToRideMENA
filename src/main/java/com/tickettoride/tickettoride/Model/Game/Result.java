package com.tickettoride.tickettoride.Model.Game;

public record Result(boolean ok, String error) {
    public static final Result OK = new Result(true, null);
    public static Result fail(String message) { return new Result(false, message); }
}
