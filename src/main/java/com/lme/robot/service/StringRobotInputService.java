package com.lme.robot.service;

import com.lme.robot.model.Robot;
import com.lme.robot.model.input.RobotInput;
import com.lme.robot.model.instructions.InstructionCode;
import com.lme.robot.model.instructions.NavigationInstruction;
import com.lme.robot.model.instructions.NavigationInstructionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StringRobotInputService implements RobotInputService {
    private final String input;
    private final NavigationInstructionFactory navigationInstructionFactory;

    public StringRobotInputService(String input, NavigationInstructionFactory navigationInstructionFactory) {
        this.input = input;
        this.navigationInstructionFactory = navigationInstructionFactory;
    }

    @Override
    public List<RobotInput> readRobotInputs() {
        String[] lines = input.split("\\r?\\n");
        return readRobotInputs(lines);
    }

    private List<RobotInput> readRobotInputs(String[] lines) {
        List<RobotInput> robotInputs = new ArrayList<>();

        int currentLine = 1;

        while (currentLine < lines.length - 1) {
            if (currentLine % 3 == 0) {
                currentLine++;
                continue;
            }

            Robot robot = new Robot(lines[currentLine]);
            List<NavigationInstruction> instructions = readInstructions(lines[currentLine+1], robot);
            robotInputs.add(new RobotInput(robot, instructions));

            currentLine = currentLine + 2;
        }

        return robotInputs;
    }

    private List<NavigationInstruction> readInstructions(String input, Robot robot) {
        return input.codePoints()
                .mapToObj(c -> navigationInstructionFactory.getNavigationInstruction(InstructionCode.valueOf(String.valueOf((char) c)), robot))
                .collect(Collectors.toList());
    }
}
