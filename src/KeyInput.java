import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private Handler handler;
    private Game game;
    private boolean[] keyDown=new boolean[4];
    public KeyInput(Handler handler,Game game){
        this.handler=handler;
        this.game=game;
        for (int i=0;i<keyDown.length;i++)
            keyDown[i]=false;
    }
    public void keyPressed(KeyEvent e) {
        int Key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject temp = handler.object.get(i);

            if (temp.getID() == ID.player) {
                //key events for player
                if (Key == KeyEvent.VK_UP||Key==KeyEvent.VK_W) {
                    temp.setVelY(-handler.speed);
                    keyDown[0] = true;
                }
                if (Key == KeyEvent.VK_DOWN||Key==KeyEvent.VK_S) {
                    temp.setVelY(handler.speed);
                    keyDown[1] = true;
                }
                if (Key == KeyEvent.VK_RIGHT||Key==KeyEvent.VK_D) {
                    temp.setVelX(handler.speed);
                    keyDown[2] = true;
                }
                if (Key == KeyEvent.VK_LEFT||Key==KeyEvent.VK_A) {
                    temp.setVelX(-handler.speed);
                    keyDown[3] = true;
                }
            }
            if (Key==KeyEvent.VK_P) {
                if (Game.gameState== Game.STATE.Game) {
                    if (Game.pause)
                        game.pause = false;
                    else game.pause = true;
                }
            }if(Key==KeyEvent.VK_SPACE) {
                if (Game.gameState== Game.STATE.Game)
                    Game.gameState= Game.STATE.Shop;
                else if (Game.gameState== Game.STATE.Shop)
                    Game.gameState= Game.STATE.Game;
            }if (Key == KeyEvent.VK_ESCAPE)
                    System.exit(1);
        }
    }
    public void keyReleased(KeyEvent e){
        int Key=e.getKeyCode();
        for (int i=0;i<handler.object.size();i++) {
            GameObject temp = handler.object.get(i);
            if (temp.getID() == ID.player) {
                //key events for player 1
                if (Key == KeyEvent.VK_UP||Key==KeyEvent.VK_W)
                    keyDown[0] = false;
                if (Key == KeyEvent.VK_DOWN||Key==KeyEvent.VK_S)
                    keyDown[1] = false;
                if (Key == KeyEvent.VK_RIGHT||Key==KeyEvent.VK_D)
                    keyDown[2] = false;
                if (Key == KeyEvent.VK_LEFT||Key==KeyEvent.VK_A)
                    keyDown[3] = false;

                if (!keyDown[0] && !keyDown[1])
                    temp.setVelY(0);
                if (!keyDown[2] && !keyDown[3])
                    temp.setVelX(0);
            }
        }

    }
}
