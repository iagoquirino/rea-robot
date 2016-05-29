package com.rearobot.board.dto;

public class Board {

    private static final int MINIMUM = 0;

    private int width;

    private int height;

    /**
     * Constructor
     *
     * @param width
     * @param height
     */
    public Board(int width, int height) {
        super();
        this.width = width;
        this.height = height;
    }

    /**
     * Verify if positions is on Board area.
     */
    public boolean isValidPosition(int positionX, int positionY) {
        return validatePosition(positionX, width)
                && validatePosition(positionY, height);
    }

    /**
     * Validate Positions
     * 
     * @param position
     * @param boardLimit
     * @return Boolean
     */
    private boolean validatePosition(int position, int boardLimit) {
        return position >= MINIMUM && position < boardLimit;
    }

}
