package com.rearobot.builder;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import com.rearobot.robot.dto.enuns.Action;
import com.rearobot.robot.dto.enuns.Direction;
import com.rearobot.robot.json.RobotPlayActionJson;

/**
 * @author iago
 */
public class RobotPlayActionJsonBuilder extends Builder<RobotPlayActionJson> {

    private static final int ZERO = 0;

    @Override
    protected RobotPlayActionJson newPrototype() {
        return new RobotPlayActionJson();
    }

    @Override
    protected void startPrototype() {
        prototype.setActions(Lists.newArrayList(Action.LEFT));
        prototype.setDirection(Direction.NORTH);
        prototype.setPositionX(ZERO);
        prototype.setPositionY(ZERO);
    }

    @Override
    protected RobotPlayActionJson clonePrototype() {
        RobotPlayActionJson dto = new RobotPlayActionJson();
        dto.setActions(prototype.getActions());
        dto.setDirection(prototype.getDirection());
        dto.setPositionX(prototype.getPositionX());
        dto.setPositionY(prototype.getPositionY());
        return dto;
    }

    public RobotPlayActionJsonBuilder withActions(List<Action> actions) {
        this.prototype.setActions(actions);
        return this;
    }

    public RobotPlayActionJsonBuilder withDirection(Direction direction) {
        this.prototype.setDirection(direction);
        return this;
    }

    public RobotPlayActionJsonBuilder withPositionX(Integer positionX) {
        this.prototype.setPositionX(positionX);
        return this;
    }

    public RobotPlayActionJsonBuilder withPositionY(Integer positionY) {
        this.prototype.setPositionY(positionY);
        return this;
    }

    public RobotPlayActionJsonBuilder withNoActions() {
        this.prototype.setActions(new ArrayList<Action>());
        return this;
    }
}
