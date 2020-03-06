package com.lme.robot.model.instructions;

import com.lme.robot.model.Robot;
import com.lme.robot.service.NavigationService;

public class NavigationInstructionFactory {
    private final NavigationService navigationService;

    public NavigationInstructionFactory(NavigationService navigationService) {
        this.navigationService = navigationService;
    }


    public NavigationInstruction getNavigationInstruction(InstructionCode instructionCode, Robot robot) {

        switch (instructionCode) {
            case F:
                return new ForwardNavigationInstruction(robot, navigationService);
            case L:
                return new TurnLelftNavigationInstruction(robot, navigationService);
            case R:
                return new TurnRightNavigationInstruction(robot, navigationService);
        }

        throw new RuntimeException("Instruction not supported");
    }
}