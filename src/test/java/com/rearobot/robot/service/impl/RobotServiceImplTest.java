package com.rearobot.robot.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.google.common.collect.Lists;
import com.rearobot.builder.RobotPlayActionJsonBuilder;
import com.rearobot.robot.dto.Robot;
import com.rearobot.robot.dto.RobotAction;
import com.rearobot.robot.dto.enuns.Action;
import com.rearobot.robot.dto.enuns.Direction;
import com.rearobot.robot.json.RobotPlayActionJson;
import com.rearobot.robot.service.validator.RobotServiceValidator;

/**
 * @author iago
 */
@RunWith(MockitoJUnitRunner.class)
public class RobotServiceImplTest {

    private static final int INVALID_POSITION = 5;

    @Mock
    private RobotServiceValidator validator;

    @InjectMocks
    private RobotServiceImpl service;
    
    private Integer boardWidth = 5;
    
    private Integer boardHeight = 5;
    
    @Before
    public void setUp(){
    	ReflectionTestUtils.setField(service, "boardWidth", boardWidth);
    	ReflectionTestUtils.setField(service, "boardHeight", boardHeight);
    }

    @Test
    public void validateWhenPlayOnInvalidAreaOfBoard() {
        RobotPlayActionJson playAction = new RobotPlayActionJsonBuilder().withPositionX(INVALID_POSITION).build();
        try {
            service.play(playAction);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid position to place your robot it will falls :(", e.getMessage());
        }
        verifyValidator(playAction);
    }

    @Test
    public void whenPlayMustReturnPlacedWhenHaveNoActions() {
        RobotPlayActionJson playAction = new RobotPlayActionJsonBuilder().withDirection(Direction.EAST).withNoActions()
                .build();
        Robot robot = service.play(playAction);
        assertNotNull(robot);
        assertEquals(1, robot.getActions().size());
        assertRobotAction(Action.PLACED, playAction.getPositionX(), playAction.getPositionY(), playAction
                .getDirection(), robot.getActions().get(0));
        verifyValidator(playAction);
    }

    @Test
    public void whenPlayMustReturnErrorWhenPassedInvalidAction() {
        RobotPlayActionJson playAction = new RobotPlayActionJsonBuilder()
                .withDirection(Direction.EAST)
                .withActions(Lists.newArrayList(Action.PLACED))
                .build();
        try {
            service.play(playAction);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid action your robot can`t do that.", e.getMessage());
        }
        verifyValidator(playAction);
    }

    @Test
    public void whenPlayMustMoveFoward() {
        RobotPlayActionJson playAction = new RobotPlayActionJsonBuilder()
                .withActions(Lists.newArrayList(Action.MOVE))
                .build();
        Integer assertPositionX = 0;
        Integer assertPositionY = 1;

        Robot robot = service.play(playAction);

        assertEquals(2, robot.getActions().size());
        assertRobotAction(Action.PLACED, playAction.getPositionX(), playAction.getPositionY(), playAction
                .getDirection(), robot.getActions().get(0));
        assertRobotAction(Action.MOVE, assertPositionX, assertPositionY, Direction.NORTH, robot.getActions().get(1));
        assertEquals(robot.getActions().get(1), robot.getResult());
        verifyValidator(playAction);
    }
    
    @Test
    public void whenPlayMustMoveFowardWhenRobotTurnedToEAST() {
        RobotPlayActionJson playAction = new RobotPlayActionJsonBuilder()
                .withActions(Lists.newArrayList(Action.MOVE))
                .withDirection(Direction.EAST)
                .build();
        Integer assertPositionX = 1;
        Integer assertPositionY = 0;

        Robot robot = service.play(playAction);

        assertEquals(2, robot.getActions().size());
        assertRobotAction(Action.PLACED, playAction.getPositionX(), playAction.getPositionY(), playAction
                .getDirection(), robot.getActions().get(0));
        assertRobotAction(Action.MOVE, assertPositionX, assertPositionY, Direction.EAST, robot.getActions().get(1));
        assertEquals(robot.getActions().get(1), robot.getResult());
        verifyValidator(playAction);
    }
    
    @Test
    public void whenPlayMustMoveFowardWhenRobotTurnedToWEST() {
        RobotPlayActionJson playAction = new RobotPlayActionJsonBuilder()
        		.withPositionX(1)
        		.withPositionY(1)
                .withActions(Lists.newArrayList(Action.MOVE))
                .withDirection(Direction.WEST)
                .build();
        Integer assertPositionX = 0;
        Integer assertPositionY = 1;

        Robot robot = service.play(playAction);

        assertEquals(2, robot.getActions().size());
        assertRobotAction(Action.PLACED, playAction.getPositionX(), playAction.getPositionY(), playAction
                .getDirection(), robot.getActions().get(0));
        assertRobotAction(Action.MOVE, assertPositionX, assertPositionY, Direction.WEST, robot.getActions().get(1));
        assertEquals(robot.getActions().get(1), robot.getResult());
        verifyValidator(playAction);
    }

    @Test
    public void whenPlayMustTurnLeft() {
        RobotPlayActionJson playAction = new RobotPlayActionJsonBuilder()
                .withActions(Lists.newArrayList(Action.LEFT))
                .build();
        Integer assertPositions = 0;
        Robot robot = service.play(playAction);
        assertEquals(2, robot.getActions().size());
        assertRobotAction(Action.PLACED, playAction.getPositionX(), playAction.getPositionY(), playAction
                .getDirection(), robot.getActions().get(0));
        assertRobotAction(Action.LEFT, assertPositions, assertPositions, Direction.WEST, robot.getActions().get(1));
        verifyValidator(playAction);
    }

    @Test
    public void whenPlayMustTurnRight() {
        RobotPlayActionJson playAction = new RobotPlayActionJsonBuilder()
                .withActions(Lists.newArrayList(Action.RIGHT))
                .build();
        Integer assertPositions = 0;
        Robot robot = service.play(playAction);
        assertEquals(2, robot.getActions().size());
        assertRobotAction(Action.PLACED, playAction.getPositionX(), playAction.getPositionY(), playAction
                .getDirection(), robot.getActions().get(0));
        assertRobotAction(Action.RIGHT, assertPositions, assertPositions, Direction.EAST, robot.getActions().get(1));
        verifyValidator(playAction);
    }

    @Test
    public void whenPlayMustMoveTwice() {
        RobotPlayActionJson playAction = new RobotPlayActionJsonBuilder()
                .withPositionX(1)
                .withPositionY(2)
                .withDirection(Direction.EAST)
                .withActions(Lists.newArrayList(Action.MOVE, Action.MOVE))
                .build();
        Integer assertPositionX = 3;
        Integer assertPositionY = 2;
        Robot robot = service.play(playAction);

        assertEquals(3, robot.getActions().size());
        assertRobotAction(Action.PLACED, playAction.getPositionX(), playAction.getPositionY(), Direction.EAST, robot
                .getActions().get(0));
        assertRobotAction(Action.MOVE, assertPositionX - 1, assertPositionY, Direction.EAST, robot.getActions().get(1));
        assertRobotAction(Action.MOVE, assertPositionX, assertPositionY, Direction.EAST, robot.getActions().get(2));
        verifyValidator(playAction);
    }

    @Test
    public void whenPlayMustMoveTwiceCloseEndOfTheBoardRobotTurnedToEast() {
        RobotPlayActionJson playAction = new RobotPlayActionJsonBuilder()
                .withPositionX(3)
                .withPositionY(2)
                .withDirection(Direction.EAST)
                .withActions(Lists.newArrayList(Action.MOVE, Action.MOVE))
                .build();
        Integer assertPositionX = 4;
        Integer assertPositionY = 2;
        Robot robot = service.play(playAction);

        assertEquals(2, robot.getActions().size());
        assertRobotAction(Action.PLACED, playAction.getPositionX(), playAction.getPositionY(), Direction.EAST, robot
                .getActions().get(0));
        assertEquals(robot.getActions().get(1), robot.getResult());
        assertRobotAction(Action.MOVE, assertPositionX, assertPositionY, Direction.EAST, robot.getActions().get(1));
        verifyValidator(playAction);
    }

    @Test
    public void whenPlayMustMoveTwiceCloseEndOfTheBoardRobotTurnedToNorth() {
        RobotPlayActionJson playAction = new RobotPlayActionJsonBuilder()
                .withPositionX(1)
                .withPositionY(3)
                .withDirection(Direction.NORTH)
                .withActions(Lists.newArrayList(Action.MOVE, Action.MOVE))
                .build();
        Integer assertPositionX = 1;
        Integer assertPositionY = 4;
        Robot robot = service.play(playAction);

        assertEquals(2, robot.getActions().size());
        assertRobotAction(Action.PLACED, playAction.getPositionX(), playAction.getPositionY(), Direction.NORTH, robot
                .getActions().get(0));
        assertEquals(robot.getActions().get(1), robot.getResult());
        assertRobotAction(Action.MOVE, assertPositionX, assertPositionY, Direction.NORTH, robot.getResult());
        verifyValidator(playAction);
    }

    @Test
    public void whenPlayMustMoveTwiceTurnLeftMoveAgainAndReport() {
        RobotPlayActionJson playAction = new RobotPlayActionJsonBuilder()
                .withPositionX(1)
                .withPositionY(2)
                .withDirection(Direction.EAST)
                .withActions(Lists.newArrayList(Action.MOVE, Action.MOVE, Action.LEFT, Action.MOVE, Action.REPORT))
                .build();
        Integer assertPosition = 3;
        Robot robot = service.play(playAction);
        assertEquals(6, robot.getActions().size());
        assertEquals(robot.getActions().get(5), robot.getResult());
        assertRobotAction(Action.REPORT, assertPosition, assertPosition, Direction.NORTH, robot.getResult());
        verifyValidator(playAction);
    }

    @Test
    public void whenPlayMustMoveTwiceCloseEndOfTheBoardRobotTurnedToSouth() {
        RobotPlayActionJson playAction = new RobotPlayActionJsonBuilder()
                .withPositionX(3)
                .withPositionY(2)
                .withDirection(Direction.SOUTH)
                .withActions(Lists.newArrayList(Action.MOVE, Action.MOVE))
                .build();
        Integer assertPositionX = 3;
        Integer assertPositionY = 0;
        Robot robot = service.play(playAction);

        assertEquals(3, robot.getActions().size());
        assertRobotAction(Action.PLACED, playAction.getPositionX(), playAction.getPositionY(), Direction.SOUTH, robot
                .getActions().get(0));
        assertEquals(robot.getActions().get(2), robot.getResult());
        assertRobotAction(Action.MOVE, assertPositionX, assertPositionY, Direction.SOUTH, robot.getResult());
        verifyValidator(playAction);
    }

    private void verifyValidator(RobotPlayActionJson playAction) {
        verify(validator).validate(playAction.getPositionX(), playAction.getPositionY(), playAction.getDirection());
    }

    private void assertRobotAction(Action action, int posX, int posY, Direction direction, RobotAction robotAction) {
        assertEquals(action, robotAction.getAction());
        assertEquals(direction, robotAction.getDirection());
        assertEquals(posX, robotAction.getPositionX());
        assertEquals(posY, robotAction.getPositionY());
    }

}
