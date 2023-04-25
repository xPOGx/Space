package Module;

public class Ufo extends BaseObject {
    private static final int[][] matrix = {
            {0, 0, 1, 0, 0},
            {0, 1, 1, 1, 0},
            {1, 1, 0, 1, 1},
            {1, 0, 0, 0, 1},
            {0, 0, 0, 0, 0},
    };

    public Ufo(double x, double y) {
        super(x, y, 3);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawMatrix(x - radius + 1, y - radius + 1, matrix, 'U');
    }

    @Override
    public void move() {
        x += Math.random() * 2 - 1;
        y += Math.random() * 2 - 1;
        checkBorders(radius, Space.game.getWidth() - radius + 1, radius, (double) Space.game.getHeight()/2 - 1);

        if ((int) (Math.random() * 10) == 0) {
            fire();
        }
    }

    public void fire() {
        Bomb bomb = new Bomb(x, y + 3);
        Space.game.getBombs().add(bomb);
    }
}
