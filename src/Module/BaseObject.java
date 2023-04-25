package Module;

import Controller.Controller;

public abstract class BaseObject {
    public static final Space game = Controller.getGame();
    public static final int SIZE = 30;
    protected double x;
    protected double y;
    protected double radius;
    private boolean isAlive;

    public BaseObject(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        isAlive = true;
    }

    public void move() {}

    public void draw(Canvas canvas) {}

    public void checkBorders(double minX, double maxX, double minY, double maxY) {
        if (x < minX) x = minX;
        if (x > maxX) x = maxX;
        if (y < minY) y = minY;
        if (y > maxY) y = maxY;
    }

    public void die() {
        isAlive = false;
    }

    public boolean isIntersect(BaseObject o) {
        double a = Math.pow(x - o.x, 2);
        double b = Math.pow(y - o.y, 2);
        double result = Math.sqrt(a + b);
        double max = Math.max(radius, o.radius);

        return result < max;
    }

    public double getX() {
        return x * 20;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y * 20;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
