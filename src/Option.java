import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Option extends MouseAdapter {
   private Handler handler;
    private HUD hud;
    private Game game;
    private Random r=new Random();
    public Option(Handler handler,HUD hud,Game game) {
        this.handler = handler;
        this.hud = hud;
        this.game=game;

    }
    public void render(Graphics g){
        g.setColor(Color.CYAN);
        g.setFont(new Font("arial",0,48));
        g.drawString("Options",Game.WIDTH/2-100,50);
        g.setColor(Color.GREEN);
        g.drawRect(200, 100, 200, 64);//Shop
        g.setColor(Color.yellow);
        g.drawRect(200, 200, 200, 64);//Menu
        g.setColor(Color.BLUE);
        g.drawRect(200, 300, 200, 64);//back
        g.setFont(new Font("arial",0,40));
        g.setColor(Color.WHITE);
        g.drawString("Shop",250,140);
        g.drawString("Menu",250,240);
        g.drawString("back",250,340);

    }
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (game.gameState== Game.STATE.Option) {
            if (mouseOver(mx, my, 200, 100, 200, 64)) {//shop button
                game.gameState = Game.STATE.Shop;
                AudioPlayer.getSound("Mouse").play();
                return;
            }
            if (mouseOver(mx, my, 200, 200, 200, 64)) {//menu button
                handler.clearAllEnemy();
                Game.pause=false;
                game.gameState = Game.STATE.Menu;
                hud.setLevel(1);
                hud.setScore(0);
                HUD.HEALTH = 100;
                Spawn.scoreKeep=0;
                HUD.MaxHealth=100;
                HUD.coins=0;
                for (int i = 0; i < 15; i++) {
                    handler.addObject(new MenuPartical(r.nextInt(game.WIDTH), r.nextInt(game.HEIGHT), ID.MenuPartical, handler));
                }
                AudioPlayer.getSound("Mouse").play();
                return;
            }
            if (mouseOver(mx, my, 200, 300, 200, 64)) {//back button
                Game.pause=false;
                game.gameState = Game.STATE.Game;
                AudioPlayer.getSound("Mouse").play();
                return;
            }

        }
    }
    public void mouseReleased(MouseEvent e){

    }

    private boolean mouseOver(int mx,int my,int x,int y,int width,int height){
        if (mx>x&&mx<x+width){
            if (my>y&&my<y+height)
                return true;
            else return  false;
        }else return false;

    }
}
