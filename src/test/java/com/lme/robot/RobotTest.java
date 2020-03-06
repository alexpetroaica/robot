package com.lme.robot;

import com.lme.robot.model.Position;
import com.lme.robot.model.Robot;
import org.junit.jupiter.api.Test;

import static com.lme.robot.model.Orientation.E;
import static com.lme.robot.model.Orientation.N;
import static com.lme.robot.model.Orientation.S;
import static com.lme.robot.model.Orientation.W;
import static org.junit.jupiter.api.Assertions.*;


public class RobotTest {

    @Test
    public void shouldMoveForwardCorrectly_WhenFacingEast() {
        //given
        Position initialPosition = new Position(3, 4, E);
        Position expectedTargetPosition = new Position(4, 4, E);


        Robot robot = new Robot(initialPosition);

        //when
        robot.moveForward();

        //then
        assertEquals(expectedTargetPosition, robot.getPosition());
    }

    @Test
    public void shouldMoveForwardCorrectly_WhenFacingWest() {
        //given
        Position initialPosition = new Position(3, 4, W);
        Position expectedTargetPosition = new Position(2, 4, W);


        Robot robot = new Robot(initialPosition);

        //when
        robot.moveForward();

        //then
        assertEquals(expectedTargetPosition, robot.getPosition());
    }

    @Test
    public void shouldMoveForwardCorrectly_WhenFacingNorth() {
        //given
        Position initialPosition = new Position(3, 4, N);
        Position expectedTargetPosition = new Position(3, 5, N);


        Robot robot = new Robot(initialPosition);

        //when
        robot.moveForward();

        //then
        assertEquals(expectedTargetPosition, robot.getPosition());
    }

    @Test
    public void shouldMoveForwardCorrectly_WhenFacingSout() {
        //given
        Position initialPosition = new Position(3, 4, S);
        Position expectedTargetPosition = new Position(3, 3, S);


        Robot robot = new Robot(initialPosition);

        //when
        robot.moveForward();

        //then
        assertEquals(expectedTargetPosition, robot.getPosition());
    }

    @Test
    public void shouldBeOutOfWorld(){
        //given
        Position initialPosition = new Position(3, 4, S);


        Robot robot = new Robot(initialPosition);

        //when
        boolean isOutOfWorld = robot.isOutOfWorld(3,3);

        //then
        assertTrue(isOutOfWorld);
    }

    @Test
    public void shouldNotBeOutOfWorld(){
        //given
        Position initialPosition = new Position(3, 4, S);


        Robot robot = new Robot(initialPosition);

        //when
        boolean isOutOfWorld = robot.isOutOfWorld(3,4);

        //then
        assertFalse(isOutOfWorld);
    }

    @Test
    public void shouldTurnLeftCorrectlyWhenFacingEast() {
        //given
        Position initialPosition = new Position(3, 4, E);
        Position expectedTargetPosition = new Position(3, 4, N);


        testTurningLeft(initialPosition, expectedTargetPosition);
    }

    @Test
    public void shouldTurnLeftCorrectlyWhenFacingNorth() {
        //given
        Position initialPosition = new Position(3, 4, N);
        Position expectedTargetPosition = new Position(3, 4, W);


        testTurningLeft(initialPosition, expectedTargetPosition);
    }

    @Test
    public void shouldTurnLeftCorrectlyWhenFacingWest() {
        //given
        Position initialPosition = new Position(3, 4, W);
        Position expectedTargetPosition = new Position(3, 4, S);


        testTurningLeft(initialPosition, expectedTargetPosition);
    }

    @Test
    public void shouldTurnLeftCorrectlyWhenFacingSouth() {
        //given
        Position initialPosition = new Position(3, 4, S);
        Position expectedTargetPosition = new Position(3, 4, E);

        testTurningLeft(initialPosition, expectedTargetPosition);
    }

    private void testTurningLeft(Position initialPosition, Position expectedTargetPosition) {
        Robot robot = new Robot(initialPosition);

        //when
        robot.turnLeft();

        //then
        assertEquals(expectedTargetPosition, robot.getPosition());
    }

    @Test
    public void shouldTurnRightCorrectlyWhenFacingEast() {
        //given
        Position initialPosition = new Position(3, 4, E);
        Position expectedTargetPosition = new Position(3, 4, S);

        testTurningRight(initialPosition, expectedTargetPosition);
    }

    @Test
    public void shouldTurnRightCorrectlyWhenFacingSouth() {
        //given
        Position initialPosition = new Position(3, 4, S);
        Position expectedTargetPosition = new Position(3, 4, W);

        testTurningRight(initialPosition, expectedTargetPosition);
    }

    @Test
    public void shouldTurnRightCorrectlyWhenFacingWest() {
        //given
        Position initialPosition = new Position(3, 4, W);
        Position expectedTargetPosition = new Position(3, 4, N);

        testTurningRight(initialPosition, expectedTargetPosition);
    }


    @Test
    public void shouldTurnRightCorrectlyWhenFacingNorth() {
        //given
        Position initialPosition = new Position(3, 4, N);
        Position expectedTargetPosition = new Position(3, 4, E);

        testTurningRight(initialPosition, expectedTargetPosition);
    }

    private void testTurningRight(Position initialPosition, Position expectedTargetPosition) {
        Robot robot = new Robot(initialPosition);

        //when
        robot.turnRight();

        //then
        assertEquals(expectedTargetPosition, robot.getPosition());
    }

}