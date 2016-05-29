package com.rearobot.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rearobot.robot.dto.enuns.Direction;
import com.rearobot.robot.dto.enuns.StepAction;

/**
 * @author iago
 */
public class DirectionalUtilsTest {

    @Test
    public void mustTurnLeftWhenNORTH() {
        assertEquals(Direction.WEST, DirectionalUtils.turnLeft(Direction.NORTH));
    }

    @Test
    public void mustTurnLeftWhenWEST() {
        assertEquals(Direction.SOUTH, DirectionalUtils.turnLeft(Direction.WEST));
    }

    @Test
    public void mustTurnLeftWhenSOUTH() {
        assertEquals(Direction.EAST, DirectionalUtils.turnLeft(Direction.SOUTH));
    }

    @Test
    public void mustTurnLeftWhenEAST() {
        assertEquals(Direction.NORTH, DirectionalUtils.turnLeft(Direction.EAST));
    }

    @Test
    public void mustTurnRightWhenNORTH() {
        assertEquals(Direction.EAST, DirectionalUtils.turnRight(Direction.NORTH));
    }

    @Test
    public void mustTurnRightWhenWEST() {
        assertEquals(Direction.NORTH, DirectionalUtils.turnRight(Direction.WEST));
    }

    @Test
    public void mustTurnRightWhenSOUTH() {
        assertEquals(Direction.WEST, DirectionalUtils.turnRight(Direction.SOUTH));
    }

    @Test
    public void mustTurnRightWhenEAST() {
        assertEquals(Direction.SOUTH, DirectionalUtils.turnRight(Direction.EAST));
    }

    @Test
    public void mustStepFowardWhenNORTH() {
        assertEquals(StepAction.STEP_FOWARD, DirectionalUtils.getStepAction(Direction.NORTH));
    }

    @Test
    public void mustStepBackWhenSOUTH() {
        assertEquals(StepAction.STEP_BACK, DirectionalUtils.getStepAction(Direction.SOUTH));
    }

    @Test
    public void mustStepFowardWhenEAST() {
        assertEquals(StepAction.STEP_FOWARD, DirectionalUtils.getStepAction(Direction.EAST));
    }

    @Test
    public void mustStepBackWhenWEST() {
        assertEquals(StepAction.STEP_BACK, DirectionalUtils.getStepAction(Direction.WEST));
    }

    @Test
    public void mustUndoStepWhenNORTH() {
        assertEquals(StepAction.STEP_BACK, DirectionalUtils.getUndoStepAction(Direction.NORTH));
    }

    @Test
    public void mustUndoStepWhenSOUTH() {
        assertEquals(StepAction.STEP_FOWARD, DirectionalUtils.getUndoStepAction(Direction.SOUTH));
    }

    @Test
    public void mustUndoStepWhenEAST() {
        assertEquals(StepAction.STEP_BACK, DirectionalUtils.getUndoStepAction(Direction.EAST));
    }

    @Test
    public void mustUndoStepWhenWEST() {
        assertEquals(StepAction.STEP_FOWARD, DirectionalUtils.getUndoStepAction(Direction.WEST));
    }

}
