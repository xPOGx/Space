package Module;

import java.util.Arrays;

public class SpaceShip extends BaseObject {
    private static int[][] matrix = {
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0},
            {1, 0, 1, 0, 1},
            {1, 1, 1, 1, 1}
    };
    public SpaceShip(double x, double y) {
        super(x, y, 3);
    }

    @Override
    public void move() {
        checkBorders(radius, game.getWidth() - radius + 1, 1, game.getHeight() + 1);
    }

    @Override
    public void draw(Canvas canvas) {
        char c;
        if (isAlive()) c = 'M';
        else {
            destroy();
            c = '*';
        }
        canvas.drawMatrix(x - radius + 1, y - radius + 1, matrix, c);
    }

    public void fire() {
        Rocket rocket1 = new Rocket(x - 2, y);
        Rocket rocket2 = new Rocket(x + 2, y);
        game.getRockets().addAll(Arrays.asList(rocket1, rocket2));
    }

    public void destroy() {
        matrix = new int[][]{
                {0, 0, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {1, 0, 1, 0, 1},
                {0, 1, 0, 1, 0},
                {0, 0, 1, 0, 0}
        };
    }

    public void moveLeft() {
        x--;
    }

    public void moveRight() {
        x++;
    }
}