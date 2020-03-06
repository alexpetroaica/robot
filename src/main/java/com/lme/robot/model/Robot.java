package com.lme.robot.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static com.lme.robot.model.Orientation.E;
import static com.lme.robot.model.Orientation.N;
import static com.lme.robot.model.Orientation.S;
import static com.lme.robot.model.Orientation.W;

@AllArgsConstructor
@EqualsAndHashCode
public class Robot {
    public Robot(String robot){
        String[] bits = robot.split("\\s+");
        this.position = new Position(Integer.valueOf(bits[0]), Integer.valueOf(bits[1]), Orientation.valueOf(bits[2]));
    }

    @Getter
    private Position position;

    public void moveForward(){
        switch(position.getOrientation()) {
            case E:
                moveEast();
                break;
            case W:
                moveWest();
                break;
            case N:
                moveNorth();
                break;
            case S:
                moveSouth();
                break;
        }
    }

    public void turnLeft(){
        Orientation targetOrientation = null;
        switch(position.getOrientation()){
            case N:
                targetOrientation = W;
                break;

            case E:
                targetOrientation = N;
                break;

            case S:
                targetOrientation = E;
                break;

            case W:
                targetOrientation = S;
                break;

        }

        this.position = new Position(position.getXCoordinate(), position.getYCoordinate(), targetOrientation);

    }

    public void turnRight(){
        Orientation targetOrientation = null;
        switch(position.getOrientation()){
            case N:
                targetOrientation = E;
                break;

            case E:
                targetOrientation = S;
                break;

            case S:
                targetOrientation = W;
                break;

            case W:
                targetOrientation = N;
                break;

        }

        this.position = new Position(position.getXCoordinate(), position.getYCoordinate(), targetOrientation);

    }

    public boolean isOutOfWorld(Integer worldMaxX, Integer worldMaxY){
        return position.getXCoordinate() > worldMaxX
                || position.getYCoordinate() > worldMaxY;
    }

    private void moveEast() {
        this.position = new Position(position.getXCoordinate() + 1, position.getYCoordinate(), position.getOrientation());
    }

    private void moveWest() {
        this.position = new Position(position.getXCoordinate() - 1, position.getYCoordinate(), position.getOrientation());
    }

    private void moveNorth() {
        this.position = new Position(position.getXCoordinate(), position.getYCoordinate() + 1, position.getOrientation());
    }

    private void moveSouth() {
        this.position = new Position(position.getXCoordinate(), position.getYCoordinate() - 1, position.getOrientation());
    }
}
