import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Health extends GameObject {
    private Handler handler;
    private int timer = 0;
    private BufferedImage healthImage;

    private Random r = new Random();

    public Health(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        SpriteSheet ss=new SpriteSheet(Game.spriteSheet);
        healthImage=ss.grabImage(2,1,20,20);

    }

    @Override
    public void tick() {
        timer++;
        if (timer > 375) {
            timer = 0;
            handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
      //  g.setColor(Color.BLUE);
      //  g.fillRect((int) x, (int) y, 20, 20);
        g.drawImage(healthImage,(int)x,(int)y,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 20, 20);
    }
}
