package org.lldProblems.trafficsignalcontrolsystem;

import org.lldProblems.trafficsignalcontrolsystem.enums.Direction;
import org.lldProblems.trafficsignalcontrolsystem.enums.LightColor;
import org.lldProblems.trafficsignalcontrolsystem.observer.TrafficObserver;
import org.lldProblems.trafficsignalcontrolsystem.states.light.GreenState;
import org.lldProblems.trafficsignalcontrolsystem.states.light.RedState;
import org.lldProblems.trafficsignalcontrolsystem.states.light.SignalState;
import org.lldProblems.trafficsignalcontrolsystem.states.light.YellowState;

import java.util.ArrayList;
import java.util.List;

public class TrafficLight {
    private final Direction direction;
    private LightColor currentColor;
    private SignalState currentState;
    private SignalState nextState;
    private final List<TrafficObserver> observers = new ArrayList<>();
    private final int intersectionId;


    public TrafficLight(Direction direction, int intersectionId) {
        this.direction = direction;
        this.intersectionId = intersectionId;
        this.currentState=new RedState();
        this.currentState.handle(this);
    }

    public void startGreen(){
        this.currentState = new GreenState();
        this.currentState.handle(this);
    }

    public void transition() {
        this.currentState = this.nextState;
        this.currentState.handle(this);
    }

    public void setColor(LightColor lightColor) {
        if(this.currentColor != lightColor){
            this.currentColor= lightColor;
            notifyObservers();
        }
    }

    public void setNextState(SignalState state){
        this.nextState = state ;
    }

    public LightColor getCurrentColor(){
        return currentColor;
    }

    public Direction getDirection(){
        return direction;
    }

    public void addObserver(TrafficObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TrafficObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (TrafficObserver observer : observers) {
            observer.update(intersectionId, direction, currentColor);
        }
    }
}
