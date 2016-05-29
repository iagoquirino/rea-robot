package com.rearobot.builder;

import java.util.List;

import com.google.common.collect.Lists;
import com.rearobot.robot.model.Robot;
import com.rearobot.robot.model.RobotAction;
import com.rearobot.robot.model.enuns.Action;
import com.rearobot.robot.model.enuns.Direction;

public class RobotBuilder extends Builder<Robot> {

    @Override
    protected Robot newPrototype() {
        return new Robot();
    }

    @Override
    protected void startPrototype() {
        this.prototype.setDirection(Direction.NORTH);
        this.prototype.setPositionX(0);
        this.prototype.setPositionY(0);
        this.prototype.setProgrammigActions(Lists.newArrayList(Action.LEFT));
    }

    @Override
    protected Robot clonePrototype() {
        Robot robot = new Robot();
        robot.setDirection(prototype.getDirection());
        robot.setPositionX(prototype.getPositionX());
        robot.setPositionY(prototype.getPositionY());
        robot.setProgrammigActions(prototype.getProgrammigActions());
        robot.setActions(prototype.getActions());
        return robot;
    }

    public RobotBuilder withDirection(Direction direction) {
        this.prototype.setDirection(direction);
        return this;
    }

    public RobotBuilder withPositionX(Integer positionX) {
        this.prototype.setPositionX(positionX);
        return this;
    }

    public RobotBuilder withPositionY(Integer positionY) {
        this.prototype.setPositionY(positionY);
        return this;
    }

    public RobotBuilder withProgrammingActions(List<Action> programmigActions) {
        this.prototype.setProgrammigActions(programmigActions);
        return this;
    }

    public RobotBuilder withActions(List<RobotAction> actions) {
        this.prototype.setActions(actions);
        return this;
    }

}
