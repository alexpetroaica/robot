package com.lme.robot.model.instructions;

public class InvalidCoordinatesException extends RuntimeException {
    public InvalidCoordinatesException(Integer maxGridSize){
        super(String.format("The maximum grid size is %sx%s.", maxGridSize, maxGridSize));
    }
}
