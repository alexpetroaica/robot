package com.lme.robot.service;

import com.lme.robot.model.Position;

import java.util.logging.Logger;

public class ConsoleOutputServiceImpl implements OutputService {
    private static final Logger LOG = Logger.getAnonymousLogger();

    @Override
    public void printRobotPosition(Position position, boolean isLost) {
        LOG.info(String.format("%s %s %s %s",
                position.getXCoordinate(),
                position.getYCoordinate(),
                position.getOrientation().toString(),
                isLost ? "LOST" : "")
        );
    }
}
