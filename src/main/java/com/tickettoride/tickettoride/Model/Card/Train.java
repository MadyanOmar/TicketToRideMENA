package com.tickettoride.tickettoride.Model.Card;

class Train {
    private State state;
    private final Color color;

    public Train(State state, Color color){
        this.state = state;
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void modifyState(State newState){this.state = newState;}
    public boolean isSameColor(Color otherColor){return this.color == otherColor;}
}
