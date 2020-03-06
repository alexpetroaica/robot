package com.lme.robot.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorldDimensionsInput {
    private final Integer worldX;
    private final Integer worldY;
}
