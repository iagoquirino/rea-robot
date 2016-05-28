package com.rearobot.board.dto;

public class Board {

    private static final int MINIMUM = 0;

    private int width;

    private int height;

    /**
     * Constructor
     */
    public Board() {
        super();
        this.width = 5;
        this.height = 5;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Verify if positions is on Board area.
     */
    public boolean isValidPosition(int positionX, int positionY) {
        return validatePosition(positionX, width)
                && validatePosition(positionY, height);
    }

    private boolean validatePosition(int position, int boardLimit) {
        return position >= MINIMUM && position < boardLimit;
    }

}
