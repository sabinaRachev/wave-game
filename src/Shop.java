import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shop extends MouseAdapter {
    Handler handler;
    HUD hud;
    private Game game;
    private int B1=1000;
    private int B2=1000;
    private int B3=500;
    public Shop(Handler handler,HUD hud,Game game){
        this.handler=handler;
        this.hud=hud;
       this.game=game;
    }
    public void render(Graphics g){
        g.setColor(Color.CYAN);
        g.setFont(new Font("arial",0,48));
        g.drawString("SHOP",Game.WIDTH/2-100,50);
        g.setFont(new Font("arial",0,15));
        g.setColor(Color.WHITE);
        //box 1
        g.drawString("Upgrade Health",75,200);
        g.drawString("Cost: "+B1,80,220);
        g.setColor(Color.RED);
        g.drawRect(70,150,120,120);
        //box 2
        g.setColor(Color.WHITE);
        g.drawString("Upgrade speed",240,200);
        g.drawString("Cost: "+B2,245,220);
        g.setColor(Color.GREEN);
        g.drawRect(230,150,120,120);
        //box 3
        g.setColor(Color.WHITE);
        g.drawString("Refill Health",410,200);
        g.drawString("Cost: "+B3,410,220);
        g.setColor(Color.BLUE);;
        g.drawRect(400,150,120,120);
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial",1,25));
        g.drawString("Number of coins : "+HUD.coins,Game.WIDTH/2-130,90);
        //back button
        g.drawRect(215, 360, 150, 64);
        g.drawString("Back",260,400);

    }
    public void mousePressed(MouseEvent e){
        int mx=e.getX();
        int my=e.getY();
        if (game.gameState== Game.STATE.Shop) {
            //box1
            if (mouseOver(mx, my, 70, 150, 120, 120)) {
                if (HUD.coins >= B1) {
                    HUD.coins=HUD.coins-B1;
                    B1 += 500;
                    hud.bounds += 20;
                    hud.HEALTH = (100 + (hud.bounds / 2));
                    hud.setMaxHealth(100 + (hud.bounds / 2));
                }

            }
            //box 2
            if (mouseOver(mx, my, 230, 150, 120, 120)) {
                if (HUD.coins >= B2) {
                    HUD.coins=HUD.coins-B2;
                    B2 += 500;
                    handler.speed++;
                }
            }
            //box 3
            if (mouseOver(mx, my, 400, 150, 120, 120)) {
                if (HUD.coins >= B3) {
                    HUD.coins=HUD.coins-B3;
                    hud.bounds += 20;
                    hud.HEALTH = (100 + (hud.bounds / 2));
                    hud.setMaxHealth(100 + (hud.bounds / 2));
                }
            }
            //back button
            if (mouseOver(mx, my, 215, 360, 150, 64)) {
                game.gameState = Game.STATE.Option;
                AudioPlayer.getSound("Mouse").play();
            }
        }
    }
    private boolean mouseOver(int mx,int my,int x,int y,int width,int height){
        if (mx>x&&mx<x+width){
            if (my>y&&my<y+height)
                return true;
            else return  false;
        }else return false;

    }
}
