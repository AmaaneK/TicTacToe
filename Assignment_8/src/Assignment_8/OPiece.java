package Assignment_8;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


/**
 * The class  O piece extends piece
 *
 *  @author Amaane Kaur, 000930854
 */
public class OPiece extends Piece {


    /**
     *
     *  O piece
     *
     * @param row  the row.
     * @param col  the col.
     * @return public
     */
    public OPiece(int row, int col) {

        super(row, col, Color.BLACK);
    }

    @Override

/**
 *
 * Draw
 *
 * @param grid  the grid.
 */
    public void draw(GridPane grid) {

        Circle circle = new Circle(20, color);
        circle.setFill(Color.BLACK);
        circle.setStroke(Color.BLACK);
        if(isSelected) {
            circle.setStroke(Color.BLUE);
            circle.setStrokeWidth(5);
        } else { circle.setStrokeWidth(2); }
        grid.add(circle, col, row);
    }
}
