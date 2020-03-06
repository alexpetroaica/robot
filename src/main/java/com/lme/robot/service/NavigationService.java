package com.lme.robot.service;

import com.lme.robot.model.Robot;

public interface NavigationService {
    void moveForward(Robot robot);

    void turnLeft(Robot robot);

    void turnRight(Robot robot);
}
