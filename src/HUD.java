import java.awt.*;

public class HUD {
    public static float HEALTH=100;
    private  float greenValue=255;
    private  float score=0;
    private float level=1;
    public int bounds=0;
    public static float coins=0;
    public static float MaxHealth=100;
    public void tick(){
        HEALTH=Game.clamp(HEALTH,0,100+(bounds/2));
        greenValue=HEALTH*2;
        greenValue=Game.clamp(greenValue,0,255);
        score++;
    }
    public void  render(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(15,15,200+bounds,32);
        g.setColor(new Color(75,(int)greenValue,0));
        g.fillRect(15,15, (int)(HEALTH*2),32);
        g.setColor(Color.WHITE);
        g.drawRect(15,15,200+bounds,32);
        g.drawString("Score: "+score,15,64);
        g.drawString("level: "+level,15,80);
        g.drawString("Coins: "+coins,15,100);
        g.drawRect(500,15,115,40);
        g.setFont(new Font("arial",1,15));
        g.drawString("Options",535,40);
    }
    public void setScore(float score){
        this.score=score;
    }
    public float getScore(){
        return score;
    }
    public float getLevel(){
        return level;
    }
    public void setLevel(float level){
        this.level=level;
    }
    public float getMaxHealth(){
        return MaxHealth;
    }
    public void setMaxHealth(float maxHealth){
        MaxHealth=maxHealth;
    }

}
