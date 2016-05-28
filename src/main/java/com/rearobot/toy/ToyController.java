package com.rearobot.toy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rearobot.robot.dto.Robot;
import com.rearobot.robot.dto.enuns.Direction;
import com.rearobot.robot.json.RobotActionJson;
import com.rearobot.robot.json.RobotConverter;
import com.rearobot.robot.json.RobotPlayActionJson;
import com.rearobot.robot.service.RobotService;
import com.rearobot.toy.json.ToyResponse;

/**
 * @author iago
 */
@RestController
@RequestMapping(value = "/toys",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ToyController {

    @Autowired
    private RobotService service;

    @Autowired
    private RobotConverter converter;

    @RequestMapping(value = "/robots", method = RequestMethod.POST)
    public ToyResponse<RobotActionJson> playWithRobot(@RequestBody RobotPlayActionJson playAction,
            @RequestParam(required = false) Boolean withHistory) {
        Robot robot = service.play(playAction);
        return converter.convert(robot, withHistory);
    }

    @RequestMapping(value = "/robots/{positionX},{positionY},{direction}/play", method = RequestMethod.POST)
    public ToyResponse<RobotActionJson> playWithRobotWithPositions(Integer positionX, Integer positionY,
            Direction direction,
            @RequestBody RobotPlayActionJson playAction, @RequestParam(required = false) Boolean withHistory) {
        Robot robot = service.play(positionX, positionY, direction,
                playAction.getActions());
        return converter.convert(robot, withHistory);
    }

}
