package Controller;

import Module.*;
import View.MainView;
import javafx.stage.Stage;

public class Controller {
    /**
     * Actual state of the game
     */
    protected static GameState state;
    private MainView view;

    public Controller() {
        state = GameState.Started;
        view = new MainView();
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
}
