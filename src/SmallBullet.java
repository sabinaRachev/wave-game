import java.awt.*;
import java.util.Random;

public class SmallBullet extends GameObject {
    Handler handler;
    Random r=new Random();
    public SmallBullet(float x,float y,ID id,Handler handler){
        super(x,y,id);
        this.handler=handler;
        velX=r.nextInt((5- -5)+ -5);
        velY=5;


    }
    @Override
    public void tick() {
        x=x+velX;
        y=y+velY;
        if (y>=Game.HEIGHT)
            handler.removeObject(this);
        handler.addObject(new Trail(x,y,ID.Trail, Color.YELLOW,10,10,(float)0.1,handler));

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect((int)x,(int)y,10,10);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,10,10);
    }
}
