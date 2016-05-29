package com.rearobot.robot.model;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.rearobot.board.model.Board;
import com.rearobot.robot.model.enuns.Action;
import com.rearobot.robot.model.enuns.Direction;
import com.rearobot.robot.model.enuns.StepAction;
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
     */
    public Robot() {
        super();
    }

    /**
     * Constructor
     *
     * @param positionX
     * @param positionY
     * @param direction
     * @param programmigActions
     */
    public Robot(Integer positionX, Integer positionY, Direction direction, List<Action> programmigActions) {
        this();
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

    /**
     * @param positionX the positionX to set
     */
    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    /**
     * @param positionY the positionY to set
     */
    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * @param programmigActions the programmigActions to set
     */
    public void setProgrammigActions(List<Action> programmigActions) {
        this.programmigActions = programmigActions;
    }

    /**
     * @param actions the actions to set
     */
    public void setActions(List<RobotAction> actions) {
        this.actions = actions;
    }

    /**
     * Make Robot Turn Left.
     */
    public void turnLeft() {
        this.direction = DirectionalUtils.turnLeft(direction);
        report(positionX, positionY, Action.LEFT);
    }

    /**
     * Make Robot Turn Right.
     */
    public void turnRight() {
        this.direction = DirectionalUtils.turnRight(direction);
        report(positionX, positionY, Action.RIGHT);
    }

    /**
     * Make Robot Report Current Location.
     */
    public void report() {
        report(positionX, positionY, Action.REPORT);
    }

    /**
     * Make Robot Move.
     */
    public void move() {
        doTheMove();
        report(positionX, positionY, Action.MOVE);
    }

    /**
     * Place Robot on position.
     */
    public void place() {
        report(positionX, positionY, Action.PLACED);
    }

    /**
     * Verify if robot is able to make a move.
     *
     * @param board
     * @return boolean
     */
    public boolean isAbleToMove(Board board) {
        doTheMove();
        boolean validPosition = board.isValidPosition(positionX, positionY);
        undoMove();
        return validPosition;
    }

    /**
     * Get Result of Robot.
     *
     * @return RobotAction
     */
    public RobotAction getResult() {
        if (CollectionUtils.isNotEmpty(actions)) {
            return Iterables.getLast(actions);
        }
        return null;
    }

    /**
     * Report actions.
     *
     * @param positionX
     * @param positionY
     * @param action
     */
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

    /**
     * Do the move.
     */
    private void doTheMove() {
        StepAction stepAction = DirectionalUtils.getStepAction(direction);
        calculatePosition(stepAction);
    }

    /**
     * Calculate Positions.
     *
     * @param stepAction
     * @param position
     * @return Integer
     */
    private Integer calculate(StepAction stepAction, Integer position) {
        if (StepAction.STEP_FOWARD.equals(stepAction)) {
            position++;
        } else {
            position--;
        }
        return position;
    }

    /**
     * Undo Move.
     */
    private void undoMove() {
        StepAction stepAction = DirectionalUtils.getUndoStepAction(direction);
        calculatePosition(stepAction);
    }

    /**
     * Calculate position for robot.
     *
     * @param stepAction
     */
    private void calculatePosition(StepAction stepAction) {
        if (Direction.NORTH.equals(this.direction) || Direction.SOUTH.equals(direction)) {
            positionY = calculate(stepAction, positionY);
        } else {
            positionX = calculate(stepAction, positionX);
        }
    }

}
