package com.rearobot.robot.service;

import java.util.List;

import com.rearobot.robot.dto.Robot;
import com.rearobot.robot.dto.enuns.Action;
import com.rearobot.robot.dto.enuns.Direction;
import com.rearobot.robot.json.RobotPlayActionJson;

/**
 * @author iago
 */
public interface RobotService {

    public Robot play(Integer positionX, Integer positionY, Direction direction,
            List<Action> actions);

    public Robot play(RobotPlayActionJson playAction);

}
