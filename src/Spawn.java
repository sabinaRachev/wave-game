import org.lwjgl.Sys;

import java.util.Random;

public class Spawn {
    private Handler handler;
    private Game game;
    private HUD hud;
    public static float scoreKeep = 0;
    private float timer=0;
    private Random r = new Random();

    public Spawn(Handler handler, HUD hud, Game game) {
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }

    public void tick() {
        scoreKeep++;
        timer++;
        if (game.diff == 0) {
            if (timer>=500){//add bottle of health to game
                handler.addObject(new Health(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Health, handler));
                timer=0;
            }
            if (scoreKeep >= 750) {
                scoreKeep = 0;
                hud.setLevel(hud.getLevel() + 1);
                if (hud.getLevel() == 2) {
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
                } else if (hud.getLevel() == 3) {
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
                } else if (hud.getLevel() == 4) {
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                } else if (hud.getLevel() == 5) {
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
                } else if (hud.getLevel() == 6) {
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                } else if (hud.getLevel() == 7) {
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
                } else if (hud.getLevel() == 8) {
                    handler.clearAllEnemy();
                    for (int i = 0; i < 5; i++)
                        handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
                } else if (hud.getLevel() == 9) {
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
                } else if (hud.getLevel() == 10) {
                    handler.clearAllEnemy();
                    handler.addObject(new SmallBossEnemy(Game.WIDTH / 2 - 48, -120, ID.smallEnemyBoss, handler));
                } else if (hud.getLevel() == 11) {
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
                }  else if (hud.getLevel() == 12) {
                    handler.addObject(new SmallBossEnemy(Game.WIDTH / 2 - 48, -120, ID.smallEnemyBoss, handler));
                }
                else if (hud.getLevel() == 13) {
                    handler.clearAllEnemy();
                    handler.addObject(new EnemyBoss(Game.WIDTH / 2 - 48, -120, ID.EnemyBoss, handler));
                }
                else if (hud.getLevel() == 14) {
                    handler.addObject(new FastEnemy(Game.WIDTH / 2 - 48, -120, ID.smallEnemyBoss, handler));
                }
                else if (hud.getLevel() == 15) {
                    handler.addObject(new SmartEnemy(Game.WIDTH / 2 - 48, -120, ID.smallEnemyBoss, handler));
                }
                else if (hud.getLevel() == 16) {
                    handler.addObject(new HardEnemy(Game.WIDTH / 2 - 48, -120, ID.smallEnemyBoss, handler));
                }
                else if (hud.getLevel() == 17) {
                    handler.clearAllEnemy();
                    for (int i=0;i<9;i++)
                        handler.addObject(new HardEnemy(Game.WIDTH / 2 - 48, -120, ID.smallEnemyBoss, handler));
                }
            }
        } else if (game.diff == 1) {
            if (timer>=650){
                handler.addObject(new Health(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Health, handler));
                timer=0;
            }
            if (scoreKeep >= 500) {
                scoreKeep = 0;
                hud.setLevel(hud.getLevel() + 1);
                if (hud.getLevel() == 2) {
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
                } else if (hud.getLevel() == 3) {
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
                } else if (hud.getLevel() == 4) {
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                } else if (hud.getLevel() == 5) {
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
                } else if (hud.getLevel() == 6) {
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                } else if (hud.getLevel() == 7) {
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
                } else if (hud.getLevel() == 8) {
                    handler.clearAllEnemy();
                    for (int i = 0; i < 5; i++)
                        handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
                } else if (hud.getLevel() == 9) {
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
                } else if (hud.getLevel() == 10) {
                    handler.clearAllEnemy();
                    handler.addObject(new SmallBossEnemy(Game.WIDTH / 2 - 48, -120, ID.smallEnemyBoss, handler));
                } else if (hud.getLevel() == 11) {
                    handler.addObject(new SmallBossEnemy(Game.WIDTH / 2 - 48, -120, ID.smallEnemyBoss, handler));
                } else if (hud.getLevel() == 11) {
                    handler.addObject(new FastEnemy(Game.WIDTH / 2 - 48, -120, ID.smallEnemyBoss, handler));
                }
                else if (hud.getLevel() == 13) {
                    handler.clearAllEnemy();
                    handler.addObject(new EnemyBoss(Game.WIDTH / 2 - 48, -120, ID.EnemyBoss, handler));
                }
                else if (hud.getLevel() == 14) {
                    handler.addObject(new FastEnemy(Game.WIDTH / 2 - 48, -120, ID.smallEnemyBoss, handler));
                }
                else if (hud.getLevel() == 15) {
                    handler.addObject(new SmartEnemy(Game.WIDTH / 2 - 48, -120, ID.smallEnemyBoss, handler));
                }
                else if (hud.getLevel() == 16) {
                    handler.addObject(new HardEnemy(Game.WIDTH / 2 - 48, -120, ID.smallEnemyBoss, handler));
                }
                else if (hud.getLevel() == 17) {
                    handler.clearAllEnemy();
                   for (int i=0;i<9;i++)
                        handler.addObject(new HardEnemy(Game.WIDTH / 2 - 48, -120, ID.smallEnemyBoss, handler));
                }
            }
        }
    }
}
