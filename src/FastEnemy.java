import java.awt.*;

public class FastEnemy extends GameObject {
    Handler handler;

    public FastEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velY = 2;
        velX = 9;


    }

    @Override
    public void tick() {
        x = x + velX;
        y = y + velY;
        if (y <= 0 || y >= Game.HEIGHT - 32)
            velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 16)
            velX *= -1;
        handler.addObject(new Trail(x, y, ID.Trail, Color.CYAN, 16, 16, (float) 0.04, handler));

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect((int)x,(int) y, 16, 16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int) y, 16, 16);
    }
}
