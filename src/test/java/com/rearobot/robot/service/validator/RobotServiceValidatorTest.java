package com.rearobot.robot.service.validator;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.rearobot.robot.dto.enuns.Direction;

/**
 * @author iago
 */
@RunWith(MockitoJUnitRunner.class)
public class RobotServiceValidatorTest {

    private static final Integer POS_Y = 2;
    private static final Integer POS_X = 1;
    @InjectMocks
    private RobotServiceValidator validator;

    @Test
    public void validateWhenPositionXIsNull() {
        try {
            validator.validate(null, POS_Y, Direction.NORTH);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Inform a valid position X for robot.", e.getMessage());
        }
    }

    @Test
    public void validateWhenPositionXIsInvalid() {
        try {
            validator.validate(-1, POS_Y, Direction.NORTH);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Inform a valid position X for robot.", e.getMessage());
        }
    }

    @Test
    public void validateWhenPositionYIsNull() {
        try {
            validator.validate(POS_X, null, Direction.NORTH);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Inform a valid position Y for robot.", e.getMessage());
        }
    }

    @Test
    public void validateWhenPositionYIsInvalid() {
        try {
            validator.validate(POS_X, -1, Direction.NORTH);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Inform a valid position Y for robot.", e.getMessage());
        }
    }

    @Test
    public void validateWhenDirectionIsNull() {
        try {
            validator.validate(POS_X, POS_Y, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Inform a valid direction for robot.", e.getMessage());
        }
    }

    @Test
    public void mustNotValidate() {
        validator.validate(POS_X, POS_Y, Direction.EAST);
    }

}
