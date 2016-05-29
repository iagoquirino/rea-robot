package com.rearobot.robot.service;

import java.util.List;

import com.rearobot.robot.json.RobotPlayActionJson;
import com.rearobot.robot.model.Robot;
import com.rearobot.robot.model.enuns.Action;
import com.rearobot.robot.model.enuns.Direction;

/**
 * @author iago
 */
public interface RobotService {

    /**
     * Plays with robot.
     *
     * @param positionX
     * @param positionY
     * @param direction
     * @param actions
     * @return Robot
     */
    public Robot play(Integer positionX, Integer positionY, Direction direction,
            List<Action> actions);

    /**
     * Plays with robot.
     *
     * @param playAction
     * @return Robot
     */
    public Robot play(RobotPlayActionJson playAction);

}
