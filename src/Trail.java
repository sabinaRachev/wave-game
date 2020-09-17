import java.awt.*;

public class Trail extends GameObject {
    private float alpha=1;
    private Handler handler;
    private Color color;
    private float life;
    private float width,height;
    public Trail(float x,float y,ID id,Color color,float width,float height,float life,Handler handler){
        super(x,y,id);
        this.handler=handler;
       this.width=width;
       this.height=height;
       this.life=life;
       this.color=color;
    }
    @Override
    public void tick() {
        if (alpha>life)
            alpha-=life-0.001;
        else handler.removeObject(this);
    }
    private AlphaComposite makeTransparent(float alpha){
        int type=AlphaComposite.SRC_OVER;
        return AlphaComposite.getInstance(type,alpha);
    }

    @Override
    public void render(Graphics g) {
      Graphics2D g2d=(Graphics2D)g;
      g2d.setComposite(makeTransparent(alpha));
      g2d.setColor(color);
      g2d.fillRect((int)x,(int)y,(int)width,(int)height);
        g2d.setComposite(makeTransparent(1));
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
