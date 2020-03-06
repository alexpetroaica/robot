package com.lme.robot.integration;

import com.lme.robot.model.input.RobotInput;
import com.lme.robot.model.input.WorldDimensionsInput;
import com.lme.robot.model.instructions.NavigationInstructionFactory;
import com.lme.robot.service.ConsoleOutputServiceImpl;
import com.lme.robot.service.NavigationService;
import com.lme.robot.service.NavigationServiceImpl;
import com.lme.robot.service.OutputService;
import com.lme.robot.service.RobotInputService;
import com.lme.robot.service.RobotInstructionExecutor;
import com.lme.robot.service.StringRobotInputService;
import com.lme.robot.service.StringWorldDimensionsInputService;
import com.lme.robot.service.WorldDimensionsInputService;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IntegrationTest {
    private static final String documentationInput =
            "5 3\n" +
            "1 1 E\n" +
            "RFRFRFRF\n" +
            "\n" +
            "3 2 N\n" +
            "FRRFLLFFRRFLL\n" +
            "\n" +
            "0 3 W\n" +
            "LLFFFLFLFL";

    private static final String tryingToLeaveWorldTwiceThroughSamePlace =
            "5 3\n" +
            "1 1 E\n" +
            "RFRFRFRF\n" +
            "\n" +
            "3 2 N\n" +
            "FRRFLLFFRRFLL\n" +
            "\n" +
            "3 2 N\n" +
            "FRRFLLFFRRFLL\n" +
            "\n" +
            "0 3 W\n" +
            "LLFFFLFLFL";

    @Test
    public void shouldMoveRobotsAroundWorld(){
        //given
        WorldDimensionsInput worldDimensionsInput = getWorldDimensionsInput(documentationInput);

        RobotInputService robotInputService = getRobotInputService(worldDimensionsInput, documentationInput);

        RobotInstructionExecutor robotInstructionExecutor = getRobotInstructionExecutor();

        List<RobotInput> robotInputs = robotInputService.readRobotInputs();

        //when
        robotInputs.forEach(ri -> robotInstructionExecutor.execute(ri.getRobot(), ri.getInstructions(), worldDimensionsInput));

    }


    private RobotInputService getRobotInputService(WorldDimensionsInput worldDimensionsInput, String input) {
        NavigationService navigationService =
                new NavigationServiceImpl(worldDimensionsInput.getWorldX(), worldDimensionsInput.getWorldY());

        NavigationInstructionFactory navigationInstructionFactory = new NavigationInstructionFactory(navigationService);

        return new StringRobotInputService(input, navigationInstructionFactory);
    }

    private RobotInstructionExecutor getRobotInstructionExecutor() {
        OutputService outputService = new ConsoleOutputServiceImpl();

        return new RobotInstructionExecutor(outputService);
    }

    private WorldDimensionsInput getWorldDimensionsInput(String input) {
        WorldDimensionsInputService worldDimensionsInputService =
                new StringWorldDimensionsInputService(input);

        return worldDimensionsInputService.readWorldDimensions();
    }
}
