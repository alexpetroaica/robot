package com.lme.robot.model.input;

import com.lme.robot.model.Robot;
import com.lme.robot.model.instructions.NavigationInstruction;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RobotInput {
    private Robot robot;
    private final List<NavigationInstruction> instructions;
}
