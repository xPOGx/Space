package Controller;

import Module.*;
import View.MainView;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Controller {
    /**
     * Actual state of the game
     */
    protected static GameState state;
    /**
     * Boolean variables describing user input
     */
    private boolean up, right, left, start;
    /**
     *  Boolean block to prevent pressing keys too fast.
     */
    private boolean keyActive;
    private SpaceShip ship;
    private static Space game;
    private MainView view;

    public Controller() {
        state = GameState.Started;
        up = right = left = start = false;
        game = new Space(30, 30);
        view = new MainView(game);
        ship = view.getShip();
        keyActive = true;

        resume();
    }

    /**
     * The gameloop, handles user input, updates and renders the game
     */
    private void resume(){

        new AnimationTimer(){

            @Override
            public void handle(long now) {

                // when moving right
                if(right && !left) {
                    ship.moveRight();
                }
                // when moving left
                if(left && !right) {
                    ship.moveLeft();
                }
                // when ship fire
                if(up) {
                    ship.fire();
                }
                // when game started or restarted
                if(start && (state == GameState.Finished || state == GameState.Started)) {
                    restart();
                    start = false;
                }
                // when game is running, make movement
                if(state == GameState.Running) {
                    left = right = up = false;
                    keyActive = true; // unlock possibility to press another key after snake made it's move
                }

                update(); // updating the game parameters, positions, etc.
                view.render(); // rendering the scene
                movement(view.getScene()); // handling user key input on actual scene
            }
        }.start(); // starting the timer

    }

    /**
     * The update method
     */
    private void update() {

        game.moveAllItems();
        game.checkBombs();
        game.checkRockets();
        game.removeDead();
        game.createUfo();
        game.getCanvas().clear();
        game.draw(game.getCanvas());

        if(!ship.isAlive()) { // check if ship destroyed
            state = GameState.Finished; //
        }
    }

    /**
     * Method to handle pressed keys on scene given as argument
     * @param scene on which events are performed
     */
    private void movement(Scene scene) {

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            public void	handle(KeyEvent e){

                switch (e.getCode()) {
                    case UP -> {
                        if (keyActive && state == GameState.Running) {
                            up = true;
                            left = false;
                            right = false;
                            keyActive = false;
                        }
                    }
                    case LEFT -> {
                        if (!right && keyActive && state == GameState.Running) {
                            left = true;
                            up = false;
                            keyActive = false;
                        }
                    }
                    case RIGHT -> {
                        if (!left && keyActive && state == GameState.Running) {
                            right = true;
                            up = false;
                            keyActive = false;
                        }
                    }
                    case ENTER -> { // start or restart the game
                        start = true;
                        if (state == GameState.Finished) {
                            resume();
                        }
                    }
                    case ESCAPE -> // exit program
                            System.exit(0);
                    default -> {
                    }
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            }
        });
    }

    /**
     * Restarting the game by setting basic parameters to their primary values
     */
    private void restart() {
        state = GameState.Running;
        up = left = right = false;
    }

    /**
     * Static method for returning the actual state of game for the Model and View classes
     * @return Actual state of the game
     */
    public static GameState getState() {
        return state;
    }

    /**
     * Returns the stage, pass it to Main class
     * @return The game stage
     */
    public Stage getStage() {
        return view.getStage();
    }

    /**
     * Returns the view, pass it to Main class
     * @return The game view
     */
    public MainView getView() {
        return view;
    }

    /**
     * Returns Space object.
     * @return The Space game
     */
    public static Space getGame() {
        return game;
    }
}
