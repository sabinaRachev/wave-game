import org.ietf.jgss.GSSManager;
import org.newdawn.slick.openal.Audio;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.Random;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID= 70871151959889524L;
    public static final  int WIDTH=640,HEIGHT=WIDTH/12*9;
    private Thread thread;
    private boolean running=false;
    private Handler handler;
    private Handler coinHandler;
    private  Spawn spawn;
    private Random r;private HUD hud;
    private Menu menu;
    private Shop shop;
    public static BufferedImage spriteSheet;
    private Option option;
    public  static boolean pause=false;
    public  int diff=0;
    //0=normal
    //1=hard
    public enum STATE{
        Menu,
        Select,
        Shop,
        ScoreTable,
        Option,
        Help,
        Game,
        End
    };
    public static STATE gameState=STATE.Menu;
    public Game(){
        handler=new Handler();
        coinHandler=new Handler();
        hud=new HUD();
        shop=new Shop(handler,hud,this);
        menu=new Menu(this,handler,hud,coinHandler);
        option=new Option(handler,hud,this);
        this.addKeyListener(new KeyInput(handler,this));
        this.addMouseListener(menu);
        this.addMouseListener(shop);
        this.addMouseListener(option);
        AudioPlayer.load();
        AudioPlayer.getMusic("music").loop();
        BufferedImageLoader loader=new BufferedImageLoader();
        spriteSheet=loader.loadImage("/sheet.png");
        new Window(WIDTH,HEIGHT,"Game!",this);
        spawn=new Spawn(handler,hud,this);
        r=new Random();
        if (gameState==STATE.Game) {
            handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.player, handler));
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
        }
        else {
            for (int i=0;i<15;i++){
                handler.addObject(new MenuPartical(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuPartical, handler));
            }
        }
    }
    public synchronized void start(){
        thread=new Thread(this);
        thread.start();
        running=true;
    }
    public synchronized void stop(){
        try {
            thread.join();
            running=false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

  public void run(){
        this.requestFocus();
        long lastTime=System.nanoTime();
        double amountOfTicks=60.0;
        double ns=1000000000/amountOfTicks;
        double delta=0;
        long timer=System.currentTimeMillis();
        int frames=0;
        while (running){
            long now=System.nanoTime();
            delta+=(now-lastTime)/ns;
            lastTime=now;
            while (delta>=1){
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;
            if (System.currentTimeMillis()-timer>1000){
                timer+=1000;
            //    System.out.println("FPS: "+frames);
                frames=0;
            }
        }

  }
  private void tick(){
  if (gameState==STATE.Game) {
      if (!pause){
          hud.tick();
           spawn.tick();
          handler.tick();
          coinHandler.tick();
      if (hud.HEALTH <= 0) {//the player "died"
          HUD.HEALTH = 100;
          HUD.MaxHealth=100;
          spawn.scoreKeep=0;
          gameState = STATE.End;
          handler.clearAllEnemy();
          for (int i = 0; i < 15; i++) {
              handler.addObject(new MenuPartical(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuPartical, handler));
          }
      }
  }
  }
  else if (gameState==STATE.Menu||gameState==STATE.End||gameState==STATE.Select||gameState==STATE.Help||gameState==STATE.ScoreTable){
      handler.tick();
  }
  }
  public void render() {
      BufferStrategy bs = this.getBufferStrategy();
      if (bs == null) {
          this.createBufferStrategy(3);
          return;
      }
      Graphics g = bs.getDrawGraphics();
      g.setColor(Color.black);
      g.fillRect(0, 0, WIDTH, HEIGHT);
      if (gameState == STATE.Game) {
          hud.render(g);
          handler.render(g);
          coinHandler.render(g);
      }
      else if (gameState==STATE.Shop)
          shop.render(g);
      else if (gameState==STATE.Option)
          option.render(g);
      else if (gameState==STATE.Menu||gameState==STATE.Help||gameState==STATE.End||gameState==STATE.Select||gameState==STATE.ScoreTable){
          handler.render(g);
          menu.render(g);
      }
      g.dispose();
          bs.show();

  }
  public static float clamp(float var,float min,float max){
        if (var>=max)
            return var=max;
        else if (var<=min)
            return var=min;
        else return var;
  }

    public static void main(String[] args) {
        new Game();

    }
}
