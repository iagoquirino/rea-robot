package com.rearobot.utils;

import com.google.common.collect.ImmutableMap;
import com.rearobot.robot.model.enuns.Direction;
import com.rearobot.robot.model.enuns.StepAction;

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
        ImmutableMap<Direction, Direction> directions = ImmutableMap.of(
                Direction.NORTH, Direction.WEST,
                Direction.SOUTH, Direction.EAST,
                Direction.EAST, Direction.NORTH,
                Direction.WEST, Direction.SOUTH);
        return directions.get(direction);
    }

    /**
     * Return direction when turned right
     *
     * @param direction
     * @return Direction
     */
    public static Direction turnRight(Direction direction) {
        ImmutableMap<Direction, Direction> oppositeDirection = ImmutableMap.of(
                Direction.NORTH, Direction.EAST,
                Direction.SOUTH, Direction.WEST,
                Direction.EAST, Direction.SOUTH,
                Direction.WEST, Direction.NORTH);
        return oppositeDirection.get(direction);
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
