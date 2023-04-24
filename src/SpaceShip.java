import java.util.Arrays;

public class SpaceShip extends BaseObject {
    private static int[][] matrix = {
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0},
            {1, 0, 1, 0, 1},
            {1, 1, 1, 1, 1},
    };
    private double dx = 0;

    public SpaceShip(double x, double y) {
        super(x, y, 3);
    }

    @Override
    public void move() {
        x += dx;
        checkBorders(radius, Space.game.getWidth() - radius + 1, 1, Space.game.getHeight() + 1);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawMatrix(x - radius + 1, y - radius + 1, matrix, 'M');
    }

    public void fire() {
        Rocket rocket1 = new Rocket(x - 2, y);
        Rocket rocket2 = new Rocket(x + 2, y);
        Space.game.getRockets().addAll(Arrays.asList(rocket1, rocket2));
    }

    public void moveLeft() {
        dx--;
    }

    public void moveRight() {
        dx++;
    }
}