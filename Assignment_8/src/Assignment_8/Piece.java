package Assignment_8;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;


/**
 * The class Abstract piece
 *
 *  @author Amaane Kaur, 000930854
 */
public abstract class Piece {
    protected int row;
    protected int col;
    protected Color color;
    protected boolean isSelected = false;


    /**
     * Piece
     *
     * @param row   the row.
     * @param col   the col.
     * @param color the color.
     * @return public
     */
    public Piece(int row, int col, Color color) {

        this.row = row;
        this.col = col;
        this.color = color;
    }


    /**
     * Gets the row
     *
     * @return the row
     */
    public int getRow() {

        return row;
    }


    /**
     * Gets the col
     *
     * @return the col
     */
    public int getCol() {

        return col;
    }


    /**
     * Move
     *
     * @param newRow the new row.
     * @param newCol the new col.
     */
    public void move(int newRow, int newCol) {

        this.row = newRow;
        this.col = newCol;
    }


    /**
     * Select
     */
    public void select() {

        isSelected = true;
    }


    /**
     * Deselect
     */
    public void deselect() {

        isSelected = false;
    }

    /**
     * Draw
     *
     *  @param gridPane the GridPane where the piece will be drawn
     */
    public abstract void draw(GridPane gridPane);

}
