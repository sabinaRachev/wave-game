import java.awt.*;

public class SmartEnemy extends GameObject {
    Handler handler;
    private GameObject player;
    public SmartEnemy(float x,float y,ID id,Handler handler){
        super(x,y,id);
        this.handler=handler;
        for (int i=0;i<handler.object.size();i++)
            if (handler.object.get(i).getID()==ID.player)
                player=handler.object.get(i);

    }
    @Override
    public void tick() {
        x+=velX;
        y+=velY;
        float diffX=x-player.getX()-16;
        float diffY=y-player.getY()-16;
        float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()));
        velX = (float)((-1.0/distance) * diffX);
        velY = (float) ((-1.0/distance) * diffY);
     //   if (y<=0||y>=Game.HEIGHT-32)
     //       velY*=-1;
      //  if (x<=0||x>=Game.WIDTH-16)
      //     velX*=-1;
        handler.addObject(new Trail(x,y,ID.Trail, Color.GREEN,16,16,(float)0.04,handler));

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect((int)x,(int)y,16,16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,16,16);
    }
}
