package com.lme.robot.model.instructions;

import com.lme.robot.model.Position;
import com.lme.robot.model.Robot;
import com.lme.robot.service.NavigationService;
import com.lme.robot.service.NavigationServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.lme.robot.model.Orientation.N;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class NavigationServiceImplTest {

    @Test
    public void shouldThrowExceptionWhenXAxisBiggerThan50() {
        InvalidCoordinatesException thrown = assertThrows(InvalidCoordinatesException.class, () -> new NavigationServiceImpl(51, 50));
        assertEquals("The maximum grid size is 50x50.", thrown.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenYAxisBiggerThan50() {
        InvalidCoordinatesException thrown = assertThrows(InvalidCoordinatesException.class, () -> new NavigationServiceImpl(50, 51));
        assertEquals("The maximum grid size is 50x50.", thrown.getMessage());
    }

    @Test
    public void shouldCallRobotToMoveWhenMovingForward() {
        //given
        Robot robot = Mockito.mock(Robot.class);

        NavigationService navigationService = new NavigationServiceImpl(30, 30);

        //when
        navigationService.moveForward(robot);

        //then
        verify(robot).moveForward();
    }

    @Test
    public void shouldIgnoreMovingForwardWhenAlreadyOutOfWorld() {
        //given
        Robot robot = Mockito.mock(Robot.class);
        NavigationService navigationService = new NavigationServiceImpl(30, 30);

        given(robot.isOutOfWorld(30, 30))
                .willReturn(true);

        //when
        navigationService.moveForward(robot);

        //then
        verify(robot, never()).moveForward();
    }

    @Test
    public void shouldNotFallOffFromSamePositionTwice() {
        //given
        Robot robot1 = Mockito.mock(Robot.class);
        given(robot1.getPosition()).willReturn(new Position(3, 4, N));
        given(robot1.isOutOfWorld(4, 4))
                .willReturn(false)
                .willReturn(true);

        Robot robot2 = Mockito.mock(Robot.class);
        given(robot2.getPosition()).willReturn(new Position(3, 4, N));
        given(robot2.isOutOfWorld(4, 4)).willReturn(false);

        NavigationService navigationService = new NavigationServiceImpl(4, 4);

        //when
        navigationService.moveForward(robot1);
        navigationService.moveForward(robot2);


        //then
        verify(robot1).moveForward();
        verify(robot2, never()).moveForward();
    }

    @Test
    public void shouldNotTurnLeftWhenOutOfWorld() {
        //given
        Robot robot = Mockito.mock(Robot.class);
        NavigationService navigationService = new NavigationServiceImpl( 30, 30);

        given(robot.isOutOfWorld(30, 30))
                .willReturn(true);

        //when
        navigationService.turnLeft(robot);

        //then
        verify(robot, never()).turnLeft();
    }

    @Test
    public void shouldTurnLeftWhenInsideWorld() {
        //given
        Robot robot = Mockito.mock(Robot.class);
        NavigationService navigationService = new NavigationServiceImpl( 30, 30);

        given(robot.isOutOfWorld(30, 30))
                .willReturn(false);

        //when
        navigationService.turnLeft(robot);

        //then
        verify(robot).turnLeft();
    }

    @Test
    public void shouldTurnRightWhenInsideWorld() {
        //given
        Robot robot = Mockito.mock(Robot.class);
        NavigationService navigationService = new NavigationServiceImpl( 30, 30);

        given(robot.isOutOfWorld(30, 30))
                .willReturn(false);

        //when
        navigationService.turnRight(robot);

        //then
        verify(robot).turnRight();
    }

    @Test
    public void shouldNotTurnRightWhenOutOfWorld() {
        //given
        Robot robot = Mockito.mock(Robot.class);
        NavigationService navigationService = new NavigationServiceImpl(30, 30);

        given(robot.isOutOfWorld(30, 30))
                .willReturn(true);

        //when
        navigationService.turnRight(robot);

        //then
        verify(robot, never()).turnRight();
    }


}