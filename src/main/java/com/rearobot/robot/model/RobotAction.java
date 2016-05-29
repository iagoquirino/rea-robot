package com.rearobot.robot.model;

import com.rearobot.robot.model.enuns.Action;
import com.rearobot.robot.model.enuns.Direction;

/**
 * @author iago
 */
public class RobotAction {

    private Action action;

    private int positionX;

    private int positionY;

    private Direction direction;

    /**
     * Robot Actions
     *
     * @param action
     * @param positionX
     * @param positionY
     * @param direction
     */
    public RobotAction(Action action, int positionX, int positionY, Direction direction) {
        super();
        this.action = action;
        this.positionX = positionX;
        this.positionY = positionY;
        this.direction = direction;
    }

    /**
     * @return the action
     */
    public Action getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(Action action) {
        this.action = action;
    }

    /**
     * @return the positionX
     */
    public int getPositionX() {
        return positionX;
    }

    /**
     * @param positionX the positionX to set
     */
    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    /**
     * @return the positionY
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     * @param positionY the positionY to set
     */
    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }

    /**
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
