import java.awt.*;
import java.awt.event.*;

enum Direction {U,D,L,R,LU,RU,LD,RD,STOP}

class Tank {
    private int x,y;
    private int speed = 3 ;
    private boolean bU=false,bD=false,bL=false,bR=false;
    private Direction dir = Direction.STOP;
    private Missile m;

    //TankWar tw = null;

    public Tank(int x,int y){
            this.x = x;
            this.y = y;
    }
    //public Tank(int x,int y,TankWar tw){
    //        this(x,y);
    //        this.tw = tw;
    //}


    public void draw(Graphics g){
            Color c = g.getColor();
            g.setColor(Color.RED);
            g.fillOval(x,y,30,30);
            g.setColor(c);
    }

    //使用move方法解决，坦克可以朝八个方向走。同时解决按键移动不均匀的问题,speed由重绘的线程决定。
    //使用locateDirection方法来确认坦克的方向。
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
                case STOP:
                   break;
            }
            locateDirection();
    }
    public void locateDirection(){
            if(!bU && !bD && !bL && !bR){ dir = Direction.STOP;}
            else if(bU && !bD && !bL && !bR){ dir = Direction.U;}
            else if(!bU && bD && !bL && !bR){ dir = Direction.D;}
            else if(!bU && !bD && bL && !bR){ dir = Direction.L;}
            else if(!bU && !bD && !bL && bR){ dir = Direction.R;}
            else if(bU && !bD && bL && !bR){ dir = Direction.LU;}
            else if(bU && !bD && !bL && bR){ dir = Direction.RU;}
            else if(!bU && bD && bL && !bR){ dir = Direction.LD;}
            else if(!bU && bD && !bL && bR){ dir = Direction.RD;}
    }

    public Missile fire(){
            return new Missile(x,y,dir);
    }

    public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_CONTROL:
                        m = fire();
                        break;
                case KeyEvent.VK_UP:
                        bU=true;
                        break;
                case KeyEvent.VK_DOWN:
                        bD=true;
                        break;
                case KeyEvent.VK_LEFT:
                        bL=true;
                        break;
                case KeyEvent.VK_RIGHT:
                        bR=true;
                        break;
            }
    }
    public void keyReleased(KeyEvent e){
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_UP:
                        bU=false;
                        break;
                case KeyEvent.VK_DOWN:
                        bD=false;
                        break;
                case KeyEvent.VK_LEFT:
                        bL=false;
                        break;
                case KeyEvent.VK_RIGHT:
                        bR=false;
                        break;
            }
    }
    public Missile getMissile(){
            return m;
    }
}
