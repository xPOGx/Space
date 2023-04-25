package View;

import Controller.Controller;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Module.*;

import java.util.List;

public class MainView {
    /**
     * Height of the game board
     */
    public final static int HEIGHT = 600;
    /**
     * Width of the game board
     */
    public final static int WIDTH = 600;
    /**
     * Color of the scene
     */
    public final static String SCENE_COLOR = "grey";
    /**
     * Space object
     */
    private Space game;
    /**
     * Object to hold the actual scene
     */
    private Scene scene;
    /**
     * The stage
     */
    private Stage stage;
    /**
     * Actual state of the game
     */
    private GameState state;
    /**
     * Ship object
     */
    private SpaceShip ship;
    /**
     * Ufos list
     */
    private List<Ufo> ufos;
    /**
     * Bombs list
     */
    private List<Bomb> bombs;
    /**
     * Rockets list
     */
    private List<Rocket> rockets;
    private Group g;
    private Pane canvas;
    private GridPane grid;

    /**
     * Default constructor initializes the basic variables and objects
     */
    public MainView(Space game) {
        this.game = game;
        ship = new SpaceShip((double) game.getWidth()/2, game.getHeight() - 2);
        game.setShip(ship);
        game.createUfo();

        stage = new Stage();
        stage.setTitle("Space");

        canvas = new Pane();
        canvas.setStyle("-fx-background-color: "+SCENE_COLOR);
        canvas.setPrefSize(WIDTH,HEIGHT);

        grid = new GridPane();

        g = new Group();
        scene = new Scene(g, WIDTH, HEIGHT);
        scene.setFill(Color.web(SCENE_COLOR));

        render();
    }

    /**
     * The render method, that displays the graphics
     */
    public void render() {

        this.state = Controller.getState(); // get the actual game state
        switch (state) { // checks for actual game state
            case Started -> whenStarted();  // if game state is Started display the starting screen
            case Running -> whenRunning(); // if Running show the space, objects, etc.
//            case Paused -> whenPaused(); // if Paused show the pause screen
//            case Finished -> whenFinished(); // if Finished show the ending game screen and display the score
//            default -> {}
        }
    }

    /**
     * Method for displaying starting screen
     */
    private void whenStarted() {

        g = new Group();
        Text largeText = new Text(WIDTH/2 - 170, HEIGHT/2 - 30, "Space Game");
        largeText.setFont(Font.font("verdana", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 50));
        Text smallText = new Text(WIDTH/2 - 130, HEIGHT/2 + 20 , "Press ENTER to play");
        smallText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 25));
        smallText.setFill(Color.DARKGREEN);
        g.getChildren().addAll(smallText, largeText);
        scene.setRoot(g);
        stage.setScene(scene);
    }

    /**
     * Main render method to display the actual running game
     */
    private void whenRunning() {

        grid.getChildren().clear(); // clear grid
        canvas.getChildren().clear(); // clear canvas

        double helpX, helpY; // variables for loops

        // ship to canvas
        Circle c = new Circle(ship.getX() , ship.getY(), BaseObject.SIZE);
        c.setFill(Paint.valueOf("blue"));
        canvas.getChildren().add(c);
        // loading rockets to canvas
        if (game.getRockets() != null) {
            for (int i = 0; i < game.getRockets().size(); i++) {
                helpX = game.getRockets().get(i).getX();
                helpY = game.getRockets().get(i).getY();
                c = new Circle(helpX, helpY, BaseObject.SIZE / 3);
                c.setFill(Paint.valueOf("yellow"));
                canvas.getChildren().add(c);
            }
        }

        // loading ufos to canvas
        if (game.getUfos() != null) {
            for (int i = 0; i < game.getUfos().size(); i++) {
                helpX = game.getUfos().get(i).getX();
                helpY = game.getUfos().get(i).getY();
                c = new Circle(helpX, helpY, BaseObject.SIZE);
                c.setFill(Paint.valueOf("red"));
                canvas.getChildren().add(c);
            }
        }
        // loading bombs to canvas
        if (game.getBombs() != null) {
            for (int i = 0; i < game.getBombs().size(); i++) {
                helpX = game.getBombs().get(i).getX();
                helpY = game.getBombs().get(i).getY();
                c = new Circle(helpX, helpY, BaseObject.SIZE / 3);
                c.setFill(Paint.valueOf("green"));
                canvas.getChildren().add(c);
            }
        }
        // adding canvas that holds the game objects, and stack that holds the score display, together

        grid.add(canvas, 0, 0);

        scene.setRoot(grid);
        stage.setScene(scene);
    }

    /**
     * Method to get the scene
     * @return the actual scene
     */
    public Scene getScene() {
        return stage.getScene();
    }

    /**
     * Method to get the stage
     * @return the game stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Method to get, or rather pass the SpaceShip object
     * @return The SpaceShip object
     */
    public SpaceShip getShip() {
        return ship;
    }

    /**
     * Method to get, or rather pass the Space object
     * @return Space object
     */
    public Space getGame() {
        return game;
    }
}
