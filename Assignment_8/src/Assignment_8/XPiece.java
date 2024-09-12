package Assignment_8;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


/**
 * The class  X piece extends piece
 *
 *  @author Amaane Kaur, 000930854
 */
public class XPiece extends Piece {


    /**
     *
     *  X piece
     *
     * @param row  the row.
     * @param col  the col.
     * @return public
     */
    public XPiece(int row, int col) {

        super(row, col, Color.TRANSPARENT);
    }


/**
 *
 * Draw
 *
 * @param grid  the grid.
 */
    public void draw(GridPane grid) {

        Circle circle = new Circle(20, color);
        circle.setStroke(Color.BLACK);
        if(isSelected) {
            circle.setStroke(Color.BLUE);
            circle.setStrokeWidth(5);
        } else { circle.setStrokeWidth(2); }
        grid.add(circle, col, row);
    }
}

