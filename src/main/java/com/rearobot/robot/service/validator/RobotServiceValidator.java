package com.rearobot.robot.service.validator;

import org.springframework.stereotype.Component;

import com.rearobot.robot.dto.enuns.Direction;

/**
 * @author iago
 */
@Component
public class RobotServiceValidator {

    private static final String MESSAGE_ERROR_DIRECTION = "Inform a valid direction for robot.";

    private static final String MESSAGE_ERROR_POSITION = "Inform a valid position %s for robot.";

    public void validate(Integer positionX, Integer positionY, Direction direction) {
        if (validatePosition(positionX)) {
            throw new IllegalArgumentException(String.format(MESSAGE_ERROR_POSITION, "X"));
        }
        if (validatePosition(positionY)) {
            throw new IllegalArgumentException(String.format(MESSAGE_ERROR_POSITION, "Y"));
        }
        if (direction == null) {
            throw new IllegalArgumentException(MESSAGE_ERROR_DIRECTION);
        }
    }

    private boolean validatePosition(Integer position) {
        return position == null || position < 0;
    }

}
