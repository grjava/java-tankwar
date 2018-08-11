import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

class Missile {

        public static final int WIDTH = 8 ;
        public static final int HEIGHT = 8 ;

        public static final List<Missile> MS = new ArrayList<Missile>();


        private int x=0,y=0;
        private Direction dir; 
        private int speed = 10 ;
        private boolean live = true;

        public Missile(int x,int y,Direction dir){
                this.x = x;
                this.y = y;
                this.dir = dir;
        }

        public void draw(Graphics g){
                if (!live) return ;
                g.fillOval(x,y,Missile.WIDTH,Missile.HEIGHT);
        }
        public void move(){
            int speed1 = (int)(0.707f*speed);
            switch (dir) {
                case U: 
                   y -= speed;
                   break;
                case D: 
                   y += speed;
                   break;
                case L: 
                   x -= speed;
                   break;
                case R: 
                   x += speed;
                   break;
                case LU: 
                   x -= speed1;
                   y -= speed1;
                   break;
                case LD: 
                   x -= speed1;
                   y += speed1;
                   break;
                case RU: 
                   x += speed1;
                   y -= speed1;
                   break;
                case RD: 
                   x += speed1;
                   y += speed1;
                   break;
            }

            if (this.x<0 || this.y<0 || this.x>TankWar.GAME_WIDTH || this.y>TankWar.GAME_HEIGHT){ 
                    this.live = false;
            }
        }
        public void setLive(boolean live){this.live = live;}
        public boolean isLive(){ return live;}

        public Rectangle getRect(){
                return new Rectangle(x,y,WIDTH,HEIGHT);
        }

        public boolean hit(Tank t){
                if (this.getRect().intersects(t.getRect()) && t.isLive()){ 
                        t.setLive(false);
                        Explode e = new Explode(x,y);
                        Explode.es.add(e);
                        return true;
                }
                return false;
        }
}



