package Assignment_8;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * The class Game extends Application
 *
 *  @author Amaane Kaur, 000930854
 *  Non-functioning Tic Tac Toe game with selecting, moving and removing pieces.
 */
public class Game extends Application {
    private GridPane gridPane;
    private ArrayList<Piece> pieces = new ArrayList<>();
    private Piece selectedPiece = null;
    private boolean isPlayerOneTurn = true;
    private int numRows;
    private int numCols;

    /**
     * Main method
     *
     * @param args the arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Start method to initialize the game UI
     *
     * @param primaryStage the primary stage.
     */
    @Override
    public void start(Stage primaryStage) {
        numRows = 3;
        numCols = 3;
        gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        createGrid();

        TextField rowField = new TextField();
        TextField colField = new TextField();
        rowField.setPromptText("Row");
        colField.setPromptText("Column");
        Button removeButton = new Button("Remove");

        Label rulesLabel = new Label("X = empty circles. \nO = filled circle. \nSelection: Selected pieces are highlighted with Blue. \nMove/Remove: Pieces can be moved by selecting them first and then clicking on an empty cell or \nremoved by entering coordinates (start from (0,0)).");

        HBox controls = new HBox(10, rowField, colField, removeButton);

        VBox root = new VBox(10, gridPane, controls, rulesLabel);

        removeButton.setOnAction(this::handleRemovePiece);

        root.setPadding(new Insets(20));
        Scene scene = new Scene(root, 560, 420);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.show();
    }

    /**
     * Creates the grid for the game.
     */
    private void createGrid() {
        //gridPane.setGridLinesVisible(true);
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Pane cell = new Pane();
                cell.setMinSize(80, 80);
                cell.setOnMouseClicked(this::handleCellClick);
                gridPane.add(cell, col, row);
            }
        }
    }

    /**
     * Handles the cell click event.
     *
     * @param event the mouse event.
     */
    public void handleCellClick(MouseEvent event) {
        Pane source = (Pane) event.getSource();
        int row = GridPane.getRowIndex(source);
        int col = GridPane.getColumnIndex(source);

        if (selectedPiece == null) {
            if (!isCellOccupied(row, col)) {
                Piece piece;
                if (isPlayerOneTurn) {
                    piece = new OPiece(row, col);
                } else {
                    piece = new XPiece(row, col);
                }
                pieces.add(piece);
                redrawGrid();
                switchTurn();
            } else {
                selectPiece(row, col);
            }
        } else {
            if (!isCellOccupied(row, col)) {
                selectedPiece.move(row, col);
                selectedPiece.deselect();
                selectedPiece = null;
                redrawGrid();
            } else {
                new Alert(Alert.AlertType.WARNING, "Cell is already occupied!").showAndWait();
            }
        }
    }

    /**
     * Handles the removal of a piece.
     *
     * @param event the action event from the remove button.
     */
    public void handleRemovePiece(javafx.event.ActionEvent event) {
        HBox controls = (HBox) ((Button) event.getSource()).getParent();
        TextField rowField = (TextField) controls.getChildren().get(0);
        TextField colField = (TextField) controls.getChildren().get(1);

        try {
            int row = Integer.parseInt(rowField.getText());
            int col = Integer.parseInt(colField.getText());
            Piece pieceToRemove = getPieceAt(row, col);

            if (pieceToRemove != null) {
                pieces.remove(pieceToRemove);
                redrawGrid();
            } else {
                new Alert(Alert.AlertType.WARNING, "No piece at the specified location!").showAndWait();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Please enter valid numbers for row and column.").showAndWait();
        }
    }

    /**
     * Checks if a cell is occupied.
     *
     * @param row the row.
     * @param col the column.
     * @return true if the cell is occupied, false otherwise.
     */
    private boolean isCellOccupied(int row, int col) {
        return getPieceAt(row, col) != null;
    }

    /**
     * Gets the piece at the specified location.
     *
     * @param row the row.
     * @param col the column.
     * @return the piece at the specified location, or null if no piece is present.
     */
    private Piece getPieceAt(int row, int col) {
        for (Piece piece : pieces) {
            if (piece.getRow() == row && piece.getCol() == col) {
                return piece;
            }
        }
        return null;
    }

    /**
     * Selects a piece.
     *
     * @param row the row.
     * @param col the column.
     */
    private void selectPiece(int row, int col) {
        Piece piece = getPieceAt(row, col);
        if (piece != null) {
            piece.select();
            selectedPiece = piece;
            redrawGrid();
        }
    }

    /**
     * Redraws the grid, ensuring all pieces are displayed correctly.
     */
    private void redrawGrid() {
        gridPane.getChildren().clear();

        createGrid();

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Pane cell = new Pane();
                cell.setMinSize(80, 80);
                cell.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
                cell.setOnMouseClicked(this::handleCellClick);
                gridPane.add(cell, col, row);
            }
        }

        for (Piece piece : pieces) {
           piece.draw(gridPane);
        }
    }

    /**
     * Switches the turn between players.
     */
    public void switchTurn() {
        isPlayerOneTurn = !isPlayerOneTurn;
    }
}