package com.lme.robot.model.instructions;

import com.lme.robot.model.Robot;
import com.lme.robot.service.NavigationService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ForwardNavigationInstruction implements NavigationInstruction {
    private final Robot robot;
    private final NavigationService navigationService;

    @Override
    public void execute() {
        navigationService.moveForward(robot);
    }

}
