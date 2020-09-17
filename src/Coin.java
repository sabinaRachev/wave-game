import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Coin extends GameObject {
     private Handler handler;
     private int timer=0;
     private BufferedImage coinImage;
     private Random r=new Random();
    public Coin(float x,float y,ID id,Handler handler){
        super(x,y,id);
        this.handler=handler;
        SpriteSheet ss=new SpriteSheet(Game.spriteSheet);
        coinImage=ss.grabImage(1,1,20,20);
    }
    @Override
    public void tick() {
        timer++;
       if (timer>300){
            timer=0;
           handler.removeObject(this);
           handler.addObject(new Coin(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Coin, handler));
        }
    }

    @Override
    public void render(Graphics g) {
       // g.setColor(Color.YELLOW);
       // g.fillRect((int)x,(int)y,20,20);
       g.drawImage(coinImage,(int)x,(int)y,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,20,20);
    }
}
