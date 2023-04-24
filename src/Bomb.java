public class Bomb extends BaseObject {

    public Bomb(double x, double y) {
        super(x, y, 1);
    }

    @Override
    public void move() {
        setY(getY() + 1);
    }

    public void draw(Canvas canvas) {
        canvas.setPoint(getX(), getY(), 'B');
    }
}
