package com.lme.robot.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public final class Position {
    private final Integer xCoordinate;
    private final Integer yCoordinate;
    private final Orientation orientation;
}
