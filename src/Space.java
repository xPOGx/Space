import java.util.ArrayList;
import java.util.List;

public class Space {
    private final int width;
    private final int height;
    private SpaceShip ship;
    private final List<Ufo> ufos = new ArrayList<>();
    private final List<Rocket> rockets = new ArrayList<>();
    private final List<Bomb> bombs = new ArrayList<>();
    public static Space game;
    private boolean isGameOver;

    public Space(int width, int height) {
        this.width = width;
        this.height = height;
        isGameOver = false;
    }

    public static void main(String[] args) {
        game = new Space(30, 30);
        game.setShip(new SpaceShip((double) game.width /2, game.getHeight() - 2));
        game.run();
    }

    public void run() {
        Canvas canvas = new Canvas(width, height);
        createUfo();
        ship.fire();
        for (int i = 0; i < 100; i++) {
            moveAllItems();
            checkBombs();
            checkRockets();
            removeDead();
            createUfo();

            canvas.clear();
            draw(canvas);
            canvas.print();
            if (isGameOver) {
                System.out.println("GAME OVER");
                break;
            }
        }

    }

    public void draw(Canvas canvas) {
        //draw game
        for (int i = 0; i < width + 2; i++) {
            for (int j = 0; j < height + 2; j++) {
                canvas.setPoint(i, j, '.');
            }
        }

        for (int i = 0; i < width + 2; i++) {
            canvas.setPoint(i, 0, '-');
            canvas.setPoint(i, height + 1, '-');
        }

        for (int i = 0; i < height + 2; i++) {
            canvas.setPoint(0, i, '|');
            canvas.setPoint(width + 1, i, '|');
        }

        for (BaseObject object : getAllItems()) {
            object.draw(canvas);
        }
    }

    public List<BaseObject> getAllItems() {
        List<BaseObject> list = new ArrayList<>();
        list.add(ship);
        list.addAll(ufos);
        list.addAll(rockets);
        list.addAll(bombs);

        return list;
    }

    public void moveAllItems() {
        getAllItems().forEach(BaseObject::move);
    }

    public void createUfo() {
        if (ufos.isEmpty()) {
            double dx = Math.random() * (width - 3) + 3;
            Ufo ufo = new Ufo(dx, 3);
            ufos.add(ufo);
        }
    }

    public void checkBombs() {
        for (Bomb bomb : bombs) {
            if (bomb.isIntersect(ship)) {
                bomb.die();
                ship.die();
                isGameOver = true;
            }
            if (bomb.y >= height) {
                bomb.die();
            }
        }
    }
    
    public void checkRockets() {
        for (Rocket rocket : rockets) {
            for (Ufo ufo : ufos) {
                if (rocket.isIntersect(ufo)) {
                    ufo.die();
                    rocket.die();
                }
            }
            if (rocket.y <= 0) {
                rocket.die();
            }
        }
    }

    public void removeDead() {
        List<Ufo> copyU = new ArrayList<>(ufos);
        List<Bomb> copyB = new ArrayList<>(bombs);
        List<Rocket> copyR = new ArrayList<>(rockets);
        for (Ufo o : copyU) {
            if (!o.isAlive()) {
                ufos.remove(o);
            }
        }
        for (Bomb o : copyB) {
            if (!o.isAlive()) {
                bombs.remove(o);
            }
        }
        for (Rocket o : copyR) {
            if (!o.isAlive()) {
                rockets.remove(o);
            }
        }
        if (isGameOver) {
            ship.die();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setShip(SpaceShip ship) {
        this.ship = ship;
    }

    public List<Rocket> getRockets() {
        return rockets;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }
}
