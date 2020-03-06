package com.lme.robot.service;

import com.lme.robot.model.instructions.InvalidCoordinatesException;
import com.lme.robot.model.Position;
import com.lme.robot.model.Robot;

import java.util.HashSet;
import java.util.Set;

public class NavigationServiceImpl implements NavigationService {
    private static final Integer MAX_GRID_SIZE = 50;


    private final Integer worldX;
    private final Integer worldY;

    private Set<Position> scents = new HashSet<>();

    public NavigationServiceImpl(Integer worldX, Integer worldY) {
        validateGridSize(worldX, worldY);
        this.worldX = worldX;
        this.worldY = worldY;
    }


    @Override
    public void moveForward(Robot robot) {
        Position sourcePosition = robot.getPosition();

        if (isMovingOutOfTheWorld(robot, sourcePosition)){
            return;
        }

        robot.moveForward();

        updateScents(robot, sourcePosition);


    }

    @Override
    public void turnLeft(Robot robot) {
        if(robot.isOutOfWorld(worldX, worldY)){
            return;
        }

        robot.turnLeft();
    }

    @Override
    public void turnRight(Robot robot) {
        if(robot.isOutOfWorld(worldX, worldY)){
            return;
        }

        robot.turnRight();
    }

    private boolean isMovingOutOfTheWorld(Robot robot, Position sourcePosition) {
        if(robot.isOutOfWorld(worldX, worldY)){
            return true;
        }

        if(scents.contains(sourcePosition)){
            return true;
        }

        return false;
    }

    private void updateScents(Robot robot, Position sourcePosition) {
        if(robot.isOutOfWorld(worldX, worldY)){
            scents.add(sourcePosition);
        }
    }


    private void validateGridSize(Integer maxX, Integer maxY) {
        if(maxX > MAX_GRID_SIZE || maxY > MAX_GRID_SIZE){
            throw new InvalidCoordinatesException(MAX_GRID_SIZE);
        }
    }
}
