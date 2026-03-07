package org.lldProblems.trafficsignalcontrolsystem;

import org.lldProblems.trafficsignalcontrolsystem.enums.Direction;
import org.lldProblems.trafficsignalcontrolsystem.observer.TrafficObserver;
import org.lldProblems.trafficsignalcontrolsystem.states.intersection.IntersectionState;
import org.lldProblems.trafficsignalcontrolsystem.states.intersection.NorthSouthGreenState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InterSectionController implements Runnable{

    private final int id;
    private final Map<Direction, TrafficLight> trafficLights;
    private IntersectionState currentState;
    private final long greenDuration;
    private final long yellowDuration;
    private volatile boolean running = true;


    public InterSectionController(int id, Map<Direction, TrafficLight> trafficLights, long greenDuration, long yellowDuration) {
        this.id = id;
        this.trafficLights = trafficLights;
        this.greenDuration = greenDuration;
        this.yellowDuration = yellowDuration;
        this.currentState = new NorthSouthGreenState();


    }

    public int getId() {
        return id;
    }

    public TrafficLight getLight(Direction direction) {
        return trafficLights.get(direction);
    }

    public IntersectionState getCurrentState() {
        return currentState;
    }

    public long getGreenDuration() {
        return greenDuration;
    }

    public long getYellowDuration() {
        return yellowDuration;
    }

    public boolean isRunning() {
        return running;
    }

    public void setState(IntersectionState state){
        this.currentState = state;
    }

    public void start(){
            new Thread(this).start();
    }

    public void stop(){
        this.running=false;
    }

    @Override
    public void run() {
        while (running){
            try{
                currentState.handle(this);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println("Intersection "+id+" was interrupted.");
                running=false;
            }
        }
    }

    public static class Builder{
        private final int id;
        private long greenDuration = 5000;
        private long yellowDuration =2000;
        private final List<TrafficObserver> observers = new ArrayList<>();

        public Builder(int id) {
            this.id = id;
        }

        public Builder withDuration(long green, long yellow){
            this.greenDuration = green;
            this.yellowDuration = yellow;
            return this;
        }

        public Builder addObserver(TrafficObserver observer){
            this.observers.add(observer);
            return this;
        }

        public InterSectionController build(){
            Map<Direction,TrafficLight> lights = new HashMap<>();
            for (Direction dir: Direction.values()){
                TrafficLight light = new TrafficLight(dir,id);

                observers.forEach(light::addObserver);
                lights.put(dir,light);
            }

            return new InterSectionController(id, lights,greenDuration,yellowDuration);
        }
    }
}
