import java.awt.*;
import java.awt.event.*;

class Missile {
        private int x=0,y=0;
        private Direction dir; 
        private int speed = 10 ;

        public Missile(int x,int y,Direction dir){
                this.x = x;
                this.y = y;
                this.dir = dir;
        }

        public void draw(Graphics g){
                g.fillOval(x,y,8,8);
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
        }
}
