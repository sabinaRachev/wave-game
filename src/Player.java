import java.awt.*;
import java.util.Random;

public class Player extends GameObject {
    Random r=new Random();
    Handler handler;
    public Player(float x,float y,ID id,Handler handler){
        super(x,y,id);
        this.handler=handler;

    }

    @Override
    public void tick() {
    x+=velX;
    y+=velY;
    x=Game.clamp(x,0,Game.WIDTH-37);
        y=Game.clamp(y,0,Game.HEIGHT-60);
        collision();
        handler.addObject(new Trail(x,y,ID.Trail,Color.WHITE,32,32,(float)0.09,handler));

    }
    public void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject temp = handler.object.get(i);
            if (temp.getID() == ID.Coin) {
                if (getBounds().intersects(temp.getBounds())) {//coin collision
                    HUD.coins = HUD.coins + 100;
                    handler.removeObject(temp);
                    handler.addObject(new Coin(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Coin, handler));

                }
            } else if (temp.getID() == ID.Health) {
                if (getBounds().intersects(temp.getBounds())) {//coin collision
                    if (HUD.HEALTH < HUD.MaxHealth)
                        HUD.HEALTH += 2;
                    handler.removeObject(temp);
                }
            }else if (temp.getID() != ID.player && temp.getID() != ID.Trail) {
                    //collision code
                    if (getBounds().intersects(temp.getBounds())) {
                        HUD.HEALTH -= 2;

                    }
                }

        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect((int)x,(int)y,32,32);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }

}
