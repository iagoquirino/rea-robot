package com.rearobot.robot.dto;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;
import com.rearobot.board.dto.Board;
import com.rearobot.robot.dto.enuns.Action;
import com.rearobot.robot.dto.enuns.Direction;
import com.rearobot.utils.DirectionalUtils;

/**
 * @author iago
 */
public class Robot {

    private Integer positionX;

    private Integer positionY;

    private Direction direction;

    private List<Action> programmigActions;

    private List<RobotAction> actions;

    /**
     * Constructor
     *
     * @param positionX
     * @param positionY
     * @param direction
     * @param programmigActions
     */
    public Robot(Integer positionX, Integer positionY, Direction direction, List<Action> programmigActions) {
        super();
        this.positionX = positionX;
        this.positionY = positionY;
        this.direction = direction;
        this.programmigActions = programmigActions;
    }

    /**
     * @return the positionX
     */
    public Integer getPositionX() {
        return positionX;
    }

    /**
     * @return the positionY
     */
    public Integer getPositionY() {
        return positionY;
    }

    /**
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * @return the programmigActions
     */
    public List<Action> getProgrammigActions() {
        return programmigActions;
    }

    /**
     * @return the actions
     */
    public List<RobotAction> getActions() {
        return actions;
    }

    public void turnLeft() {
        this.direction = DirectionalUtils.turnLeft(direction);
        report(positionX, positionY, Action.LEFT);
    }

    public void turnRight() {
        this.direction = DirectionalUtils.turnRight(direction);
        report(positionX, positionY, Action.RIGHT);
    }

    public void report() {
        report(positionX, positionY, Action.REPORT);
    }

    public void move() {
        calculateMove();
        report(positionX, positionY, Action.MOVE);
    }

    public void place() {
        report(positionX, positionY, Action.PLACED);
    }

    public boolean isAbleToMove(Board board) {
        calculateMove();
        boolean validPosition = board.isValidPosition(positionX, positionY);
        redoMove();
        return validPosition;
    }

    public RobotAction getResult() {
        RobotAction result = null;
        if (CollectionUtils.isNotEmpty(actions)) {
            for (RobotAction robotAction : actions) {
                result = robotAction;
            }
        }
        return result;
    }

    private void report(Integer positionX, Integer positionY, Action action) {
        addAction(new RobotAction(action, positionX, positionY, this.direction));
    }

    /**
     * Add action for Robot
     *
     * @param robotAction
     */
    private void addAction(RobotAction robotAction) {
        if (actions == null) {
            actions = Lists.newArrayList();
        }
        actions.add(robotAction);
    }

    private void calculateMove() {
        if (Direction.NORTH.equals(this.direction) || Direction.SOUTH.equals(direction)) {
            positionY++;
        } else {
            positionX++;
        }
    }

    private void redoMove() {
        if (Direction.NORTH.equals(this.direction) || Direction.SOUTH.equals(direction)) {
            positionY--;
        } else {
            positionX--;
        }
    }

}
