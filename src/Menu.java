import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class Menu extends MouseAdapter {
   private Game game;
   private Handler handler;
   private Random r=new Random();
   private HUD hud;
   private Handler coinHandler;
   private  PlayerName playerName;
   private highscoreFile scoreFile;

    public Menu(Game game,Handler handler,HUD hud,Handler coinHandler){
        this.game=game;
        this.handler=handler;
        this.hud=hud;
        this.coinHandler=coinHandler;
        playerName=new PlayerName("playerName",game);

        scoreFile = new highscoreFile("Highscores.txt");
    }


    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (Game.gameState == Game.STATE.Menu) {//Menu buttons
            if (mouseOver(mx, my, 210, 100, 200, 64)) {//play Button
                playerName.setLocation(600,400);
                playerName.setVisible(true);
               // game.gameState = Game.STATE.Select;
                AudioPlayer.getSound("Mouse").play();
                return;
            }    //Score Table
            if (mouseOver(mx, my, 210, 185, 200, 64)) {
                Game.gameState = Game.STATE.ScoreTable;
                AudioPlayer.getSound("Mouse").play();
            }
            //help button
            if (mouseOver(mx, my, 210, 265, 200, 64)) {
                Game.gameState = Game.STATE.Help;
                AudioPlayer.getSound("Mouse").play();

            }//exit button
            if (mouseOver(mx, my, 210, 350, 200, 64))
                System.exit(1);
        }//help buttons
      if (game.gameState == Game.STATE.Help) {
            if (mouseOver(mx, my, 210, 300, 200, 64)) {//back button
                Game.gameState = Game.STATE.Menu;
                AudioPlayer.getSound("Mouse").play();
                return;
            }
      }//Score Table
      if (game.gameState== Game.STATE.ScoreTable){
          if (mouseOver(mx,my,200,350,200,64)){//back button in score table
              Game.gameState= Game.STATE.Menu;
              AudioPlayer.getSound("Mouse").play();
              return;
          }
        }

      //end game
       if (Game.gameState == Game.STATE.End) {
            if (mouseOver(mx, my, 210, 300, 200, 64)) {
                Game.gameState = Game.STATE.Menu;
                hud.setLevel(1);
                float currentScore = hud.getScore();
                scoreFile.editHighScore(playerName.getPlayerName(),(int)currentScore);
                HUD.MaxHealth=100;
                hud.setScore(0);
                HUD.coins=0;
                AudioPlayer.getSound("Mouse").play();
            }
        }
     if (Game.gameState == Game.STATE.Select) {//select difficulty
              playerName.dispose();
            if (mouseOver(mx, my, 210, 100, 200, 64)) {
                //normal button
                   handler.clearAll();
                  handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.player, handler));
                   handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
                  handler.addObject(new Coin(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Coin, handler));
                handler.addObject(new Health(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Health, handler));
                Game.gameState = Game.STATE.Game;
                   game.diff=0;
                AudioPlayer.getSound("Mouse").play();
            }
            //hard button
            if (mouseOver(mx, my, 210, 200, 200, 64)) {
                Game.gameState = Game.STATE.Game;
                game.diff=1;
                handler.clearAll();
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.player, handler));
                handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
                handler.addObject(new Coin(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Coin, handler));
                handler.addObject(new Health(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Health, handler));
                AudioPlayer.getSound("Mouse").play();
            }
            //back button
            if (mouseOver(mx, my, 210, 300, 200, 64)) {
                Game.gameState = Game.STATE.Menu;
                AudioPlayer.getSound("Mouse").play();
                return;
            }
        }
       if (Game.gameState== Game.STATE.Game){
          if (mouseOver(mx,my,500,15,115,40)){//option screen
              Game.pause =true;
             Game.gameState = Game.STATE.Option;
              AudioPlayer.getSound("Mouse").play();
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
    public void tick(){

    }
    public void render(Graphics g) {
        if (game.gameState == Game.STATE.Menu) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Wave", 250, 70);
            g.setFont(fnt2);
            g.setColor(Color.WHITE);
            g.drawString("Play", 280, 138);
            g.setColor(Color.WHITE);
            g.drawString("Score Table", 230, 220);
            g.drawString("Help", 280, 300);
            g.setColor(Color.WHITE);
            g.drawString("Quit", 280, 385);
            g.setColor(Color.WHITE);
            g.drawRect(210, 100, 200, 64);//play
            g.setColor(Color.WHITE);
            g.drawRect(210, 185, 200, 64);//score
            g.drawRect(210, 265, 200, 64);//help
            g.setColor(Color.WHITE);
            g.drawRect(210, 350, 200, 64);//quit

        }
        else if (game.gameState== Game.STATE.Help){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Help", 250, 70);
            g.drawRect(210, 300, 200, 64);
            g.setFont(fnt2);
            g.drawString("Use up, down, left and right" , 150, 150);
            g.drawString("keys to dodge enemies",150,200);
            g.drawString("press p to pause game",150,250);
            g.setFont(fnt2);
            g.setColor(Color.WHITE);
            g.drawString("back", 280, 335);
        }
        else if (game.gameState== Game.STATE.End){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Game Over", 150, 70);
            g.drawRect(210, 300, 200, 64);
            g.setFont(fnt2);
            g.drawString("You lost with a score of: "+hud.getScore() , 110, 200);
            g.drawString(" you got to level : "+hud.getLevel() , 110, 250);
            g.setFont(fnt2);
            g.setColor(Color.WHITE);
            g.drawString("Try Again", 250, 345);
        }
        else  if (game.gameState == Game.STATE.Select) {
            playerName.dispose();
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Select Difficulty", 140, 70);
            g.setFont(fnt2);
            g.setColor(Color.WHITE);
            g.drawString("Normal", 265, 138);
            g.setColor(Color.WHITE);
            g.drawString("Hard", 265, 235);
            g.setColor(Color.WHITE);
            g.drawString("Back", 265, 335);
            g.setColor(Color.WHITE);
            g.drawRect(210, 100, 200, 64);
            g.setColor(Color.WHITE);
            g.drawRect(210, 200, 200, 64);
            g.setColor(Color.WHITE);
            g.drawRect(210, 300, 200, 64);
        }
        else if (game.gameState== Game.STATE.ScoreTable){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
         scoreFile.getSortedHighscores();
           ArrayList<String> highScores=scoreFile.getScores();
            g.setColor(Color.WHITE);
            g.drawRect(100, 100, 200, 64);
            g.drawRect(100, 164, 200, 64);
            g.drawRect(100, 228, 200, 64);
            g.drawRect(300, 100, 200, 64);
            g.drawRect(300, 164, 200, 64);
            g.drawRect(300, 228, 200, 64);
            g.setFont(fnt2);
            g.setColor(Color.WHITE);
            g.drawRect(200, 350, 200, 64);
            g.drawString("Back",260,390);
            int addY=135;
            for (int i=highScores.size()-1;i>=0;i--){
                g.drawString(scoreFile.getPlayerNameByIndex(i), 150, addY);
                g.drawString(""+scoreFile.getScoreOfPlayerByIndex(i),350,addY);
                addY+=64;
            }
            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("HighScore Table", 130, 70);
        }
    }

}
