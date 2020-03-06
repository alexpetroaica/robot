package com.lme.robot.service;

import com.lme.robot.model.input.WorldDimensionsInput;

public class StringWorldDimensionsInputService implements WorldDimensionsInputService {
    private final String input;

    public StringWorldDimensionsInputService(String input) {
        this.input = input;
    }

    @Override
    public WorldDimensionsInput readWorldDimensions() {
        String[] lines = input.split("\\r?\\n");
        String[] worldDimensions = lines[0].split("\\s+");

        Integer worldX = Integer.valueOf(worldDimensions[0]);
        Integer worldY = Integer.valueOf(worldDimensions[1]);

        return new WorldDimensionsInput(worldX, worldY);
    }
}
