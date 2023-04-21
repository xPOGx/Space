public abstract class BaseObject {
    private double x;
    private double y;
    private double radius;
    private boolean isAlive;

    public BaseObject(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        isAlive = true;
    }

    public void move() {}

    public void draw() {}

    public void die() {
        isAlive = false;
    }

    public boolean isIntersect(BaseObject o) {
        double a = Math.pow(this.x - o.getX(), 2);
        double b = Math.pow(this.y - o.getY(), 2);
        double result = Math.sqrt(a + b);
        double max = Math.max(radius, o.getRadius());

        return result < max;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
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
