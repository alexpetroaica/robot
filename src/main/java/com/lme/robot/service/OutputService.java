package com.lme.robot.service;

import com.lme.robot.model.Position;

public interface OutputService {

    void printRobotPosition(Position position, boolean isLost);
}
