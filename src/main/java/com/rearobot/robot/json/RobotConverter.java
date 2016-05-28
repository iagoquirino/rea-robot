package com.rearobot.robot.json;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.rearobot.robot.dto.Robot;
import com.rearobot.robot.dto.RobotAction;
import com.rearobot.toy.json.ToyResponse;

/**
 * @author iago
 */
@Component
public class RobotConverter {

    public ToyResponse<RobotActionJson> convert(Robot robot, Boolean withHistory) {
        List<RobotActionJson> history = convertList(robot.getActions(), withHistory);
        return new ToyResponse<RobotActionJson>(convert(robot.getResult()), history);
    }

    private RobotActionJson convert(RobotAction robotAction) {
        if (robotAction == null) {
            return null;
        }
        RobotActionJson robotActionJson = new RobotActionJson();
        BeanUtils.copyProperties(robotAction, robotActionJson);
        return robotActionJson;
    }

    private List<RobotActionJson> convertList(List<RobotAction> actions, Boolean withHistory) {
        if (withHistory == null || !withHistory) {
            return null;
        }
        List<RobotActionJson> list = new ArrayList<>();
        for (RobotAction robotAction : actions) {
            list.add(convert(robotAction));
        }
        return list;
    }

}
