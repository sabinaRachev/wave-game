import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;

public class Handler {
    ArrayList<GameObject> object=new ArrayList<GameObject>();
    public int speed=5;
    public void tick(){
        for (int i=0;i<object.size();i++){
            GameObject temp=object.get(i);
            if (temp!=null)
            temp.tick();
        }
    }
    public void render(Graphics g){
        for (int i=0;i<object.size();i++) {
                GameObject temp = object.get(i);
                if (temp!=null)
                temp.render(g);
        }
    }
    public void addObject(GameObject object){
        this.object.add(object);
    }
    public void removeObject(GameObject object){
        this.object.remove(object);
    }
    public void  clearAllEnemy(){
        for (int i=0;i<object.size();i++) {
            GameObject temp = object.get(i);
            if (temp.getID()==ID.player) {
                object.clear();
                if (Game.gameState!= Game.STATE.End&&Game.gameState!=Game.STATE.Option)
                addObject(new Player(temp.getX(), temp.getY(), ID.player, this));
            }
        }
    }
    public void  clearAll(){
        object.clear();
    }
    public ArrayList<GameObject> getObject(){
        return object;
    }

}
