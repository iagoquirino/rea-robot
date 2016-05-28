package com.rearobot.utils;

import com.google.common.collect.ImmutableMap;
import com.rearobot.robot.dto.enuns.Direction;

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

}
