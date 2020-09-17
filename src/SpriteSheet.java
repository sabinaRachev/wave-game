import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage sprite;
    public SpriteSheet(BufferedImage ss){
        this.sprite=ss;
    }


    public BufferedImage grabImage(int row,int col,int width,int height){
        BufferedImage image=sprite.getSubimage((row*32)-32,(col*32)-32,width,height);
        return image;
    }
}
