package com.rearobot.utils;

import com.google.common.collect.ImmutableMap;
import com.rearobot.robot.dto.enuns.Direction;
import com.rearobot.robot.dto.enuns.StepAction;

/**
 * Directional Utils
 *
 * @author iago
 */
public class DirectionalUtils {

    /**
     * Constructor
     */
    private DirectionalUtils() {
        super();
    }

    /**
     * Return direction when turned left
     *
     * @param direction
     * @return Direction
     */
    public static Direction turnLeft(Direction direction) {
        Direction directionResult = null;
        if (Direction.NORTH.equals(direction)) {
            directionResult = Direction.WEST;
        } else if (Direction.WEST.equals(direction)) {
            directionResult = Direction.SOUTH;
        } else if (Direction.SOUTH.equals(direction)) {
            directionResult = Direction.EAST;
        } else if (Direction.EAST.equals(direction)) {
            directionResult = Direction.NORTH;
        } else {
            throw new IllegalArgumentException("Please inform a valid direction.");
        }
        return directionResult;
    }

    /**
     * Return direction when turned right
     *
     * @param direction
     * @return Direction
     */
    public static Direction turnRight(Direction direction) {
        ImmutableMap<Direction, Direction> oppositeDirection = ImmutableMap.of(
                Direction.NORTH, Direction.SOUTH,
                Direction.SOUTH, Direction.NORTH,
                Direction.EAST, Direction.WEST,
                Direction.WEST, Direction.EAST);
        return oppositeDirection.get(turnLeft(direction));
    }

    /**
     * Get Step Action
     *
     * @param direction
     * @return StepAction
     */
    public static StepAction getStepAction(Direction direction) {
        ImmutableMap<Direction, StepAction> stepAction = ImmutableMap.of(
                Direction.NORTH, StepAction.STEP_FOWARD,
                Direction.SOUTH, StepAction.STEP_BACK,
                Direction.EAST, StepAction.STEP_FOWARD,
                Direction.WEST, StepAction.STEP_BACK);
        return stepAction.get(direction);
    }

    /**
     * Get Undo Step Action
     * 
     * @param direction
     * @return StepAction
     */
    public static StepAction getUndoStepAction(Direction direction) {
        ImmutableMap<StepAction, StepAction> undoStepAction = ImmutableMap.of(
                StepAction.STEP_BACK, StepAction.STEP_FOWARD,
                StepAction.STEP_FOWARD, StepAction.STEP_BACK);
        return undoStepAction.get(getStepAction(direction));
    }

}
