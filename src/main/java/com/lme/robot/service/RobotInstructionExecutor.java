package com.lme.robot.service;

import com.lme.robot.model.Position;
import com.lme.robot.model.Robot;
import com.lme.robot.model.input.WorldDimensionsInput;
import com.lme.robot.model.instructions.NavigationInstruction;

import java.util.List;

public class RobotInstructionExecutor {
    private final OutputService outputService;

    public RobotInstructionExecutor(OutputService outputService) {
        this.outputService = outputService;
    }

    public void execute(Robot robot, List<NavigationInstruction> instructions, WorldDimensionsInput worldDimensions){
        Position lastPositionInsideWorld = robot.getPosition();

        for (NavigationInstruction ni : instructions) {
            ni.execute();

            if(!isOutOfWorld(robot, worldDimensions)){
                lastPositionInsideWorld = robot.getPosition();
            }

        }

        outputService.printRobotPosition(lastPositionInsideWorld, isOutOfWorld(robot, worldDimensions));

    }

    private boolean isOutOfWorld(Robot robot, WorldDimensionsInput worldDimensions) {
        return robot.isOutOfWorld(worldDimensions.getWorldX(), worldDimensions.getWorldY());
    }
}
