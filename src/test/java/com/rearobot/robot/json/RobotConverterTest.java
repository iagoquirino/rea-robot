package com.rearobot.robot.json;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.rearobot.builder.RobotBuilder;
import com.rearobot.robot.dto.Robot;
import com.rearobot.robot.dto.RobotAction;
import com.rearobot.robot.dto.enuns.Action;
import com.rearobot.robot.dto.enuns.Direction;
import com.rearobot.toy.json.ToyResponse;

/**
 * @author iago
 */
public class RobotConverterTest {

    private RobotConverter converter = new RobotConverter();

    @Test
    public void convertWithoutHistory() {
        Robot robot = new RobotBuilder().withActions(getActions()).build();
        ToyResponse<RobotActionJson> response = converter.convert(robot, false);
        assertNotNull(response.getResult());
        assertNull(response.getHistory());
        assertAction(robot.getResult(), response.getResult());
    }

    @Test
    public void convertWithHistory() {
        Robot robot = new RobotBuilder().withActions(getActions()).build();
        ToyResponse<RobotActionJson> response = converter.convert(robot, true);
        assertNotNull(response.getResult());
        assertNotNull(response.getHistory());
        assertAction(robot.getResult(), response.getResult());
        assertHistory(robot.getActions(), response.getHistory());
    }

    private void assertHistory(List<RobotAction> actions, List<RobotActionJson> history) {
        assertEquals(actions.size(), history.size());
        assertAction(actions.get(0), history.get(0));
        assertAction(actions.get(1), history.get(1));
    }

    private void assertAction(RobotAction robotAction, RobotActionJson actionJson) {
        assertEquals(robotAction.getAction(), actionJson.getAction());
        assertEquals(robotAction.getDirection(), actionJson.getDirection());
        assertEquals(robotAction.getPositionX(), actionJson.getPositionX());
        assertEquals(robotAction.getPositionY(), actionJson.getPositionY());
    }

    private List<RobotAction> getActions() {
        return Lists.newArrayList(new RobotAction(Action.PLACED, 0, 0, Direction.NORTH),
                new RobotAction(Action.LEFT, 0, 0, Direction.WEST));
    }

}
