import java.awt.*;
import java.util.Random;

public class SmallBossEnemy  extends GameObject {
    Handler handler;
    private int timer=80;
    private int timer2=50;
    Random r=new Random();
    public SmallBossEnemy(float x,float y,ID id,Handler handler){
        super(x,y,id);
        this.handler=handler;
        velY=2;
        velX=0;
    }
    @Override
    public void tick() {
        x=x+velX;
        y=y+velY;
        // if (y<=0||y>=Game.HEIGHT-32)
        //      velY*=-1;
        if (x<=0||x>=Game.WIDTH-96)
            velX*=-1;
        if (timer<=0) velY=0;
        else timer--;
        if (timer<=0)
            timer2--;
        if (timer2<=0) {
            if (velX == 0)
                velX = 2;
            if (velX>0)
                velX+=0.005;
            else if (velX<0)
                velY-=0.005;
            velX=Game.clamp(velX,-7,7);
            int spawn=r.nextInt(10);
            if (spawn==0)
                handler.addObject(new SmallBullet(x+48,y+48,ID.BasicEnemy,handler));
        }
        //   handler.addObject(new Trail(x,y,ID.Trail, Color.RED,92,92,(float)0.04,handler));

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect((int)x,(int)y,50,50);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,50,50);
    }
}
