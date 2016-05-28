package com.rearobot.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rearobot.robot.dto.enuns.Direction;

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
    public void mustTurnLeftWhenPassedNull() {
        try {
            DirectionalUtils.turnLeft(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Please inform a valid direction.", e.getMessage());
        }
    }

}
