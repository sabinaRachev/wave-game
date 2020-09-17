import java.awt.*;
import java.util.Random;

public class MenuPartical extends GameObject {
    Handler handler;
    Random r=new Random();
    private Color col;
    private int dir=0;

    public MenuPartical(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        dir=r.nextInt(2);
        if (dir==0) {
            velY = 7;
            velX = 2;
        }else if (dir==1){
            velY = 2;
            velX = 7;
        }
        col=new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));

    }

    @Override
    public void tick() {
        x = x + velX;
        y = y + velY;
        if (y <= 0 || y >= Game.HEIGHT - 32)
            velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 16)
            velX *= -1;
        handler.addObject(new Trail(x, y, ID.Trail, col, 16, 16, (float) 0.04, handler));
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

